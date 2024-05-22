package com.develhope.spring.features.rent.services;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import com.develhope.spring.features.rent.repositories.LinkUserVehicleRepository;
import com.develhope.spring.features.rent.entities.RentEntity;
import com.develhope.spring.features.rent.repositories.RentRepository;
import com.develhope.spring.features.rent.model.RentModel;

import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import io.vavr.control.Either;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    LinkUserVehicleRepository linkUserVehicleRepository;
    @Autowired
    UsersRepository usersRepository;
    Logger logger = LoggerFactory.getLogger(RentService.class);
    private BaseEntityData baseEntityData;

    public Either<GenericErrors,RentalResponseDTO> createRental(UserModel user, RentalRequestDTO request, Long vehicleId, @Nullable Long costumerId) {
        if (user == null) return Either.left(new UserError.UserNotFound());
        if (vehicleId == null) return Either.left(new GenericErrors(433,"No vehicles found"));
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return Either.left(new GenericErrors(434, "This vehicle is empty"));
        if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER);
//            logger.info("Creation of new rental started");
        RentModel model = RentModel.dtoToModel(request);
        RentEntity entity = RentModel.modelToEntity(model);
        RentEntity savedEntity = rentRepository.saveAndFlush(entity);
        linkUserVehicleRepository.save(new LinkRentUserVehicleEntity(UserModel.modelToEntity(user), vehicle.get(), savedEntity));
        RentModel savedModel = RentModel.entityToModel(savedEntity);
//        logger.info("Creation of new rental finished{}", baseEntityData.getCreatedAt());
        return Either.right(RentModel.modelToDTO(savedModel));
    }

    public Either<GenericErrors, Boolean> deleteRentalById(UserModel user, Long rentId) {
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<RentEntity> rentToDelete = rentRepository.findById(rentId);
                    if (rentToDelete.isPresent()) {
                        logger.info("Rental infos deleting started");
                        rentRepository.delete(rentToDelete.get());
                        Optional<LinkRentUserVehicleEntity> link = linkUserVehicleRepository.findByRent_Id(rentId);
                        link.ifPresent(linkRentUserVehicleEntity -> linkUserVehicleRepository.delete(linkRentUserVehicleEntity));
                    }
                }
            } catch (Exception e) {
                return Either.left(new UserError.UserIdNotFoundExc(user.getId(), e));
            }
        }
        logger.info("Deleting process completed at:{}", baseEntityData.getDeletedAt());
        return Either.right(true);
    }

    //this method updates rental's infos by checking first if the user has access to this function
    public RentalResponseDTO updateLinkRentById(UserModel user, Long rentId, RentalRequestDTO request) {
        RentModel model = null;
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<RentEntity> rent = rentRepository.findById(rentId);
                    if (rent.isPresent()) {
//                        logger.info("The rental updating process started at:{}", baseEntityData.getUpdatedAt());
                        rent.get().setRentalDeposit(request.getRentalDeposit() == null ? rent.get().getRentalDeposit() : request.getRentalDeposit());
                        rent.get().setDailyRental(request.getDailyRental() == null ? rent.get().getDailyRental() : request.getDailyRental());
                        rent.get().setTotalRent(request.getTotalRent() == null ? rent.get().getTotalRent() : request.getTotalRent());
                        rent.get().setRentalStart(request.getRentalStart() == null ? rent.get().getRentalStart() : request.getRentalStart());
                        rent.get().setRentalEnd(request.getRentalEnd() == null ? rent.get().getRentalEnd() : request.getRentalEnd());
                        rent.get().setIsPayed(request.getIsPayed() == null ? rent.get().getIsPayed() : request.getIsPayed());
                        RentEntity entity = rentRepository.saveAndFlush(rent.get());
                        model = RentModel.entityToModel(entity);
//                        logger.info("Rental updating process finished at:{}", baseEntityData.getUpdatedAt());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
//                logger.error("Exception in RENT_SERVICE thrown");
            }
        }
        assert model != null;
        return RentModel.modelToDTO(model);
    }

    public LinkRentUserVehicleEntity updateLinkVehicleById(Long rentId, Long vehicleId) {
        if (rentId == null || vehicleId == null) return null;
        Optional<LinkRentUserVehicleEntity> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty()) return null;
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(vehicleId);
        if (vehicleEntity.isEmpty()) return null;

        LinkRentUserVehicleEntity newLink = rentLink.get();
        newLink.setVehicleEntity(vehicleEntity.get());
        return linkUserVehicleRepository.saveAndFlush(newLink);
    }

    public LinkRentUserVehicleEntity updateLinkUserById(Long rentId, Long userId) {
        if (rentId == null || userId == null) return null;
        Optional<LinkRentUserVehicleEntity> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty()) return null;
        Optional<UserEntity> userEntity = usersRepository.findById(userId);
        if (userEntity.isEmpty()) return null;

        LinkRentUserVehicleEntity newLink = rentLink.get();
        newLink.setUserEntity(userEntity.get());
        return linkUserVehicleRepository.saveAndFlush(newLink);
    }

    public RentalResponseDTO getSingleRental(UserEntity userEntity, Long rentId) {

        Optional<RentEntity> rent = rentRepository.findById(rentId);
        if (rent.isPresent()) {
            RentModel model = RentModel.entityToModel(rent.get());
            return RentModel.modelToDTO(model);
        } else {
            return null;
        }
    }

    //TODO: non per Id ma per entity
    public List<RentalResponseDTO> getAllByUserRole(UserEntity user) {
        if (user != null) {
            try {
                if (user.getRole() == Role.ADMIN || user.getRole() == Role.SALESMAN) {
                    List<RentEntity> rentals = rentRepository.findAll();
                    if (rentals.isEmpty()) {
                        return null;
                    }
                    return rentals.stream()
                            .map(RentModel::entityToModel)
                            .map(RentModel::modelToDTO)
                            .toList();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return List.of();
    }


}
//TODO:
// 1_add a method to retrieve all rentals for one user that works for only two roles
// 2_ add a method for all rentals by costumer accessible from all roles
// 3_add a method for most rented vehicle/most active profile of sellers
// 4_add query in repo
// 5_change boolean in payed with enum?
// 6_either
package com.develhope.spring.features.rent.services;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.errors.VehicleError;
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
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import io.vavr.control.Either;
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

    public Either<GenericErrors, RentalResponseDTO> createRental(UserModel user, RentalRequestDTO request, Long vehicleId) {
        if (user == null) return Either.left(new UserError.UserNotFound());
        if (vehicleId == null) return Either.left(new VehicleError.VehicleNotFound());
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return Either.left(new GenericErrors(434, "This vehicle is empty"));
        if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) ;
        try {
            logger.info("Creation of new rental started");
            RentModel model = RentModel.dtoToModel(request);
            RentEntity entity = RentModel.modelToEntity(model);
            RentEntity savedEntity = rentRepository.saveAndFlush(entity);
            linkUserVehicleRepository.save(new LinkRentUserVehicleEntity(UserModel.modelToEntity(user), vehicle.get(), savedEntity));
            RentModel savedModel = RentModel.entityToModel(savedEntity);
            logger.info("Creation of new rental finished{}", baseEntityData.getCreatedAt());
            return Either.right(RentModel.modelToDTO(savedModel));
        } catch (Exception e) {
            return Either.left(new GenericErrors(435, "Impossible to save " + e.getMessage()));
        }
    }

    public Either<GenericErrors, Boolean> deleteRentalById(UserModel user, Long rentId) {
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<RentEntity> rentToDelete = rentRepository.findById(rentId);
                    if (rentToDelete.isPresent()) {
                        logger.info("Rental deleting started at: {}", baseEntityData.getDeletedAt());
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
    public Either<GenericErrors, RentalResponseDTO> updateLinkRentById(UserModel user, Long rentId, RentalRequestDTO request) {
        RentModel model = null;
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<RentEntity> rent = rentRepository.findById(rentId);
                    if (rent.isPresent()) {
                        logger.info("The rental updating process started at:{}", baseEntityData.getUpdatedAt());
                        rent.get().setRentalDeposit(request.getRentalDeposit() == null ? rent.get().getRentalDeposit() : request.getRentalDeposit());
                        rent.get().setDailyRental(request.getDailyRental() == null ? rent.get().getDailyRental() : request.getDailyRental());
                        rent.get().setTotalRent(request.getTotalRent() == null ? rent.get().getTotalRent() : request.getTotalRent());
                        rent.get().setRentalStart(request.getRentalStart() == null ? rent.get().getRentalStart() : request.getRentalStart());
                        rent.get().setRentalEnd(request.getRentalEnd() == null ? rent.get().getRentalEnd() : request.getRentalEnd());
                        rent.get().setIsPayed(request.getIsPayed() == null ? rent.get().getIsPayed() : request.getIsPayed());
                        RentEntity entity = rentRepository.saveAndFlush(rent.get());
                        model = RentModel.entityToModel(entity);
                        logger.info("Rental updating process finished at:{}", baseEntityData.getUpdatedAt());
                    }
                }
            } catch (Exception e) {
                return Either.left(new VehicleError.VehicleIdNotFoundExc(user.getId(), e));
            }
        }
        assert model != null;
        return Either.right(RentModel.modelToDTO(model));
    }

    public Either<GenericErrors, LinkRentUserVehicleEntity> updateLinkVehicleById(UserModel user, Long rentId, VehicleRequest vehicleRequest) {
        if (rentId == null || vehicleRequest == null) return Either.left(new GenericErrors());
        Optional<LinkRentUserVehicleEntity> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty()) return null;
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(user.getId());
        if (vehicleEntity.isEmpty()) return null;

        LinkRentUserVehicleEntity newLink = rentLink.get();
        newLink.setVehicleEntity(vehicleEntity.get());
        return Either.right(linkUserVehicleRepository.saveAndFlush(newLink));
    }

    public Either<GenericErrors, LinkRentUserVehicleEntity> updateLinkUserById(UserModel user, Long rentId) {
        if (rentId == null || user == null) return Either.left(new GenericErrors(432, "Not found"));
        Optional<LinkRentUserVehicleEntity> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty())
            return Either.left(new GenericErrors(433, "Impossible to retrieve rent for the id: " + rentId));
        Optional<UserEntity> userEntity = usersRepository.findById(user.getId());
        if (userEntity.isEmpty()) return Either.left(new UserError.UserIsEmpty());

        LinkRentUserVehicleEntity newLink = rentLink.get();
        newLink.setUserEntity(userEntity.get());
        return Either.right(linkUserVehicleRepository.saveAndFlush(newLink));
    }

    public Either<GenericErrors, RentalResponseDTO> getSingleRental(UserModel user, Long rentId) {
        if (rentId == null || user == null) return Either.left(new GenericErrors(432, "Not found"));
        if (user.getRole() == Role.ADMIN)
            try {
                Optional<RentEntity> rent = rentRepository.findById(rentId);
                if (rent.isPresent()) {
                    RentModel model = RentModel.entityToModel(rent.get());
                    return Either.right(RentModel.modelToDTO(model));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return null;
    }

    public Either<GenericErrors, List<RentalResponseDTO>> getAllByUserRole(UserModel user) {
        if (user != null) {
            try {
                if (user.getRole() == Role.ADMIN || user.getRole() == Role.SALESMAN) {
                    List<RentEntity> rentals = rentRepository.findAll();
                    return extractRentalResponse(rentals);
                } else if(user.getRole() == Role.CUSTOMER) {
                    List<LinkRentUserVehicleEntity> customerRentals = linkUserVehicleRepository.findByUser_Id(user.getId()).stream().toList();
                    List<RentEntity> rentals = customerRentals.stream().map(LinkRentUserVehicleEntity::getRentEntity).toList();
                    return extractRentalResponse(rentals);
                } else {
                    return Either.left(new UserError.DefaultUser());
                }
            } catch (Exception e) {
               return Either.left(new GenericErrors(433, "Error during all rentals retrieving"));
            }
        }else{
            return Either.left(new UserError.UserIsEmpty());
        }
    }

    private Either<GenericErrors, List<RentalResponseDTO>> extractRentalResponse(List<RentEntity> rentals) {
        if (rentals.isEmpty()) {
            return Either.left(new GenericErrors(434, "Impossible to retrieve, the list of rentals is empty"));
        }
        return Either.right(rentals.stream()
                .map(RentModel::entityToModel)
                .map(RentModel::modelToDTO)
                .toList());
    }
}
//TODO:
// 1_add a method for most rented vehicle/most active profile of sellers
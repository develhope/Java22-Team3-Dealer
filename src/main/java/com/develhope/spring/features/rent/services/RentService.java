package com.develhope.spring.features.rent.services;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.LinkRentUserVehicleEntity;
import com.develhope.spring.features.rent.repositories.LinkUserVehicleRepository;
import com.develhope.spring.features.rent.RentEntity;
import com.develhope.spring.features.rent.repositories.RentRepository;
import com.develhope.spring.features.rent.model.RentModel;

import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    UserDetails userDetails;
    @Autowired
    Logger logger = LoggerFactory.getLogger(RentService.class);
    private BaseEntityData baseEntityData;

    public RentalResponseDTO createRental(RentalRequestDTO request, UserEntity userEntity, Long vehicleId, @Nullable Long costumerId) {
        if (userEntity == null) return null;
        if (vehicleId == null) return null;
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return null;
        UserEntity user = (UserEntity) userDetails.getAuthorities();
        if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER)
            logger.info("Creation of new rental started");
        RentModel model = RentModel.dtoToModel(request);
        RentEntity entity = RentModel.modelToEntity(model);
        RentEntity savedEntity = rentRepository.saveAndFlush(entity);
        linkUserVehicleRepository.save(new LinkRentUserVehicleEntity(userEntity, vehicle.get(), savedEntity));
        RentModel savedModel = RentModel.entityToModel(savedEntity);
        logger.info("Creation of new rental finished{}", baseEntityData.getCreatedAt());
        return RentModel.modelToDTO(savedModel);
    }

    public boolean deleteRentalById(UserEntity userEntity, Long rentId) {
        UserEntity user = (UserEntity) userDetails.getAuthorities();
        if (userEntity != null & user != null) {
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
                System.out.println("Exception caught, no result for your research has been retrieved");
                return false;
            }
        }
        logger.info("Deleting process completed at:{}", baseEntityData.getDeletedAt());
        return true;
    }

    //this method updates rental's infos by checking first if the user has access to this function
    public RentalResponseDTO updateLinkRentById(UserEntity userEntity, Long rentId, RentalRequestDTO request) {
        UserEntity user = (UserEntity) userDetails.getAuthorities();
        RentModel model = null;
        if (userEntity != null & user != null) {
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
                e.printStackTrace();
                logger.error("Exception in RENT_SERVICE thrown");
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

    public List<RentalResponseDTO> getAll() {
        List<RentEntity> rentals = rentRepository.findAll();
        if (rentals.isEmpty()) {
            return null;
        }
        return rentals.stream()
                .map(RentModel::entityToModel)
                .map(RentModel::modelToDTO)
                .toList();
    }
}
//TODO:
// 1_add a method to retrieve all rentals for one user that works for only two roles
// 2_ add a method for all rentals by costumer accessible from all roles
// 3_add a method for most rented vehicle/most active profile of sellers
// 4_where do I get libraries of vehicles with all data?
// 5_add query in repo
// 6_ASK ANTONIO IF THE METHODS ARE RIGHT!!!!!!
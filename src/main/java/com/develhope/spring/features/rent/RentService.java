package com.develhope.spring.features.rent;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.model.RentModel;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import jakarta.annotation.Nullable;
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

    public RentalResponseDTO createRental(RentalRequestDTO request, UserEntity userEntity, Long vehicleId, @Nullable Long costumerId) {
        if (vehicleId == null) return null;
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return null;

        RentModel model = RentModel.dtoToModel(request);
        RentEntity entity = RentModel.modelToEntity(model);
        RentEntity savedEntity = rentRepository.saveAndFlush(entity);
        linkUserVehicleRepository.save(new LinkRentUserVehicle(userEntity, vehicle.get(), savedEntity));
        RentModel savedModel = RentModel.entityToModel(savedEntity);
        return RentModel.modelToDTO(savedModel);
    }

    public boolean deleteRentalById(Long id) {
        Optional<RentEntity> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            try {
                rentRepository.delete(rent.get());
                Optional<LinkRentUserVehicle> link = linkUserVehicleRepository.findByRent_Id(id);
                link.ifPresent(linkRentUserVehicle -> linkUserVehicleRepository.delete(linkRentUserVehicle));
                return true;
            } catch (Exception e) {
                System.out.println("Exception caught, no result for your research has been retrieved");
                return false;
            }
        } else {
            return false;
        }
    }

    public RentalResponseDTO updateLinkRentById(long id, UserEntity userId, RentalRequestDTO request) {
        Optional<RentEntity> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            rent.get().setRentalDeposit(request.getRentalDeposit() == null ? rent.get().getRentalDeposit() : request.getRentalDeposit());
            rent.get().setDailyRental(request.getDailyRental() == null ? rent.get().getDailyRental() : request.getDailyRental());
            rent.get().setTotalRent(request.getTotalRent() == null ? rent.get().getTotalRent() : request.getTotalRent());
            rent.get().setRentalStart(request.getRentalStart() == null ? rent.get().getRentalStart() : request.getRentalStart());
            rent.get().setRentalEnd(request.getRentalEnd() == null ? rent.get().getRentalEnd() : request.getRentalEnd());
            rent.get().setIsPayed(request.getIsPayed() == null ? rent.get().getIsPayed() : request.getIsPayed());
            RentEntity entity = rentRepository.saveAndFlush(rent.get());
            RentModel model = RentModel.entityToModel(entity);
            return RentModel.modelToDTO(model);
        } else {
            return null;
        }
    }

    public LinkRentUserVehicle updateLinkVehicleById(Long rentId, Long vehicleId) {
        if (rentId == null || vehicleId == null) return null;
        Optional<LinkRentUserVehicle> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty()) return null;
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(vehicleId);
        if (vehicleEntity.isEmpty()) return null;

        LinkRentUserVehicle newLink = rentLink.get();
        newLink.setVehicleEntity(vehicleEntity.get());
        return linkUserVehicleRepository.saveAndFlush(newLink);
    }

    public LinkRentUserVehicle updateLinkUserById(Long rentId, Long userId) {

        if (rentId == null || userId == null) return null;
        Optional<LinkRentUserVehicle> rentLink = linkUserVehicleRepository.findByRent_Id(rentId);
        if (rentLink.isEmpty()) return null;
        Optional<UserEntity> userEntity = usersRepository.findById(userId);
        if (userEntity.isEmpty()) return null;

        LinkRentUserVehicle newLink = rentLink.get();
        newLink.setUserEntity(userEntity.get());
        return linkUserVehicleRepository.saveAndFlush(newLink);
    }

    public RentalResponseDTO getSingleRental(long id) {
        Optional<RentEntity> rent = rentRepository.findById(id);
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

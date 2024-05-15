package com.develhope.spring.features.rent;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.model.RentModel;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public RentalResponseDTO createRental(RentalRequestDTO request) {
        RentModel model = RentModel.dtoToModel(request);
        RentEntity entity = RentModel.modelToEntity(model);
        RentEntity savedEntity = rentRepository.saveAndFlush(entity);
        RentModel savedModel = RentModel.entityToModel(savedEntity);
        return RentModel.modelToDTO(savedModel);
    }

    public boolean deleteRentalById(Long id) {
        Optional<RentEntity> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            try {
                rentRepository.delete(rent.get());
                return true;
            } catch (Exception e) {
                System.out.println("Exception caught, no result for your research has been retrieved");
                return false;
            }
        } else {
            return false;
        }
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

    public RentalResponseDTO updateRentById(long id, RentalRequestDTO request) {
        Optional<RentEntity> rent = rentRepository.findById(id);
        if (rent.isPresent()) {

            rent.get().setRentalDeposit(request.getRentalDeposit() == null ? rent.get().getRentalDeposit() : request.getRentalDeposit());
            rent.get().setDailyRental(request.getDailyRental() == null ? rent.get().getDailyRental() : request.getDailyRental());
            rent.get().setTotalRent(request.getTotalRent() == null ? rent.get().getTotalRent() : request.getTotalRent());
            rent.get().setRentalStart(request.getRentalStart() == null ? rent.get().getRentalStart() : request.getRentalStart());
            rent.get().setRentalEnd(request.getRentalEnd() == null ? rent.get().getRentalEnd() : request.getRentalEnd());
            rent.get().setIsPayed(request.getIsPayed() == null ? rent.get().getIsPayed() : request.getIsPayed());
            rent.get().setVehicle(request.getVehicle() == null ? rent.get().getVehicle() : request.getVehicle());
            RentEntity entity = rentRepository.saveAndFlush(rent.get());
            RentModel model = RentModel.entityToModel(entity);
            return RentModel.modelToDTO(model);
        } else {
            return null;
        }
    }


}

package com.develhope.spring.features.vehicle.service;


import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.errors.VehicleError;
import com.develhope.spring.features.rent.services.RentService;
import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;
    Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public Either<GenericErrors, VehicleResponse> createVehicle(UserModel user, VehicleRequest request) {
        if (user == null) return Either.left(new UserError.UserNotFound());
        if (user.getRole() == Role.ADMIN) {
            try {
                VehicleModel model = VehicleModel.dtoToModel(request);
                VehicleEntity entity = VehicleModel.modelToEntity(model);
                VehicleEntity savedEntity = repository.saveAndFlush(entity);
                VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
                return Either.right(VehicleModel.modelToDto(savedModel));
            } catch (Exception e) {
                return Either.left(new GenericErrors(432, "Impossible to complete this action"));
            }
        } else {
            return Either.left(new VehicleError.ImpossibleToCreateVehicle());
        }
    }

    public Either<GenericErrors, Boolean> deleteVehicleByID(UserModel user, Long vehicleId) {
        if (user != null) {
            try {
                if (user.getRole() == Role.ADMIN) {
                    Optional<VehicleEntity> toDelete = repository.findById(vehicleId);
                    toDelete.ifPresent(vehicleEntity -> repository.delete(vehicleEntity));
                }
            } catch (Exception e) {
                return Either.left(new VehicleError.VehicleIdNotFoundExc(vehicleId, e));
            }
        }
        return Either.right(true);
    }

    public VehicleResponse findById(Long vehicleId) {
        VehicleEntity entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.entityToModel(entity);
        return VehicleModel.modelToDto(model);
    }

    public VehicleResponse updateVehicle(Long vehicleId, VehicleRequest request) {
        VehicleEntity toUpdate = repository.findById(vehicleId).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.dtoToModel(request);
        VehicleEntity entity = VehicleModel.modelToEntity(model);
        VehicleEntity savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);
    }

    public List<VehicleEntity> getAll() throws Exception {
        List<VehicleEntity> vehicleEntities = repository.findAll();
        if (vehicleEntities.isEmpty()) {
            throw new Exception("Your list of vehicleEntities is empty");
        }
        return vehicleEntities;
    }
}


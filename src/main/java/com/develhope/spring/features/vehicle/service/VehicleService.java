package com.develhope.spring.features.vehicle.service;


import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.errors.VehicleError;
import com.develhope.spring.features.user.entity.Role;
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
                return Either.left(new VehicleError.VehicleIdNotFoundExc(vehicleId));
            }
        }
        return Either.right(true);
    }

    public Either<GenericErrors, VehicleResponse> findById(Long vehicleId) {
        try {
            VehicleEntity entity = repository.findById(vehicleId).orElse(null);
            if (entity == null) {
                return Either.left(new VehicleError.VehicleIdNotFoundExc(vehicleId));
            }
            VehicleModel model = VehicleModel.entityToModel(entity);
            VehicleResponse response = VehicleModel.modelToDto(model);
            return Either.right(response);
        } catch (Exception e) {
            return Either.left(new VehicleError.UnexpectedErrorOccurred(e));
        }
    }

    public Either<GenericErrors, VehicleResponse> updateVehicle(Long vehicleId, VehicleRequest request) {
        try {
            VehicleEntity toUpdate = repository.findById(vehicleId).orElse(null);
            if (toUpdate == null) {
                return Either.left(new VehicleError.VehicleIdNotFoundExc(vehicleId));
            }


            VehicleModel model = VehicleModel.dtoToModel(request);
            VehicleEntity entityToSave = VehicleModel.modelToEntity(model);
            entityToSave.setVehicleId(vehicleId);

            VehicleEntity savedEntity = repository.saveAndFlush(entityToSave);
            VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
            VehicleResponse response = VehicleModel.modelToDto(savedModel);

            return Either.right(response);
        } catch (Exception e) {
            return Either.left(new VehicleError.UnexpectedErrorOccurred(e));
        }
    }

    public Either<GenericErrors, List<VehicleEntity>> getAll() {
        try {
            List<VehicleEntity> vehicleEntities = repository.findAll();
            if (vehicleEntities.isEmpty()) {
                return Either.left(new VehicleError.VehicleListEmpty());
            }
            return Either.right(vehicleEntities);
        } catch (Exception e) {
            return Either.left(new VehicleError.UnexpectedErrorOccurred(e));
        }
    }
}


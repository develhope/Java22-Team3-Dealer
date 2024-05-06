package com.develhope.spring.vehicle.service;

import com.develhope.spring.User.DTOs.CreateUserRequest;
import com.develhope.spring.User.DTOs.UsersDTO;
import com.develhope.spring.User.entity.UserEntity;
import com.develhope.spring.User.model.UserModel;
import com.develhope.spring.vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.vehicle.DTOs.VehicleDTO;
import com.develhope.spring.vehicle.entity.VehicleEntity;
import com.develhope.spring.vehicle.model.VehicleModel;
import com.develhope.spring.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;
    public VehicleDTO createVehicle(CreateVehicleRequest request){
        VehicleModel model = VehicleModel.dtoToModel(request);
        VehicleEntity entity = VehicleModel.modelToEntity(model);
        VehicleEntity savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);

    }
    public void deleteVehicleByID(Long vehicleId) {
        VehicleEntity entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        } else {
            repository.deleteById(vehicleId);
        }
    }
    public VehicleDTO findById(Long vehicleId) {
        VehicleEntity entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.entityToModel(entity);
        return VehicleModel.modelToDto(model);
    }

    public VehicleDTO updateVehicle(Long vehicleId, CreateVehicleRequest request) {
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
}

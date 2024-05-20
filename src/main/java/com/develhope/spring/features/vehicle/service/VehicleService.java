package com.develhope.spring.features.vehicle.service;


import com.develhope.spring.features.vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;

    public VehicleResponse createVehicle(CreateVehicleRequest request){
        VehicleModel model = VehicleModel.dtoToModel(request);
        VehicleEntity entity = VehicleModel.modelToEntity(model);
        VehicleEntity savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);

    }
    public boolean deleteVehicleByID(Long vehicleId) {
        VehicleEntity entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        } else {
            repository.deleteById(vehicleId);
            return true;
        }
    }
    public VehicleResponse findById(Long vehicleId) {
        VehicleEntity entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.entityToModel(entity);
        return VehicleModel.modelToDto(model);
    }

    public VehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest request) {
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
        if(vehicleEntities.isEmpty()){
            throw new Exception("Your list of vehicleEntities is empty");
        }
        return vehicleEntities;
    }
}


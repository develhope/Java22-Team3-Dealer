package com.develhope.spring.vehicle.service;

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
        VehicleDTO newVehicle = VehicleModel.modelToDto(savedModel);
        return newVehicle;

    }
}

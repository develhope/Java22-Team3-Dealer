package com.develhope.spring.vehicle.service;

import com.develhope.spring.Vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.Vehicle.DTOs.VehicleResponse;
import com.develhope.spring.vehicle.entity.Vehicle;
import com.develhope.spring.vehicle.model.VehicleModel;
import com.develhope.spring.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;

    public VehicleResponse createVehicle(CreateVehicleRequest request){
        VehicleModel model = VehicleModel.dtoToModel(request);
        Vehicle entity = VehicleModel.modelToEntity(model);
        Vehicle savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);

    }
    public boolean deleteVehicleByID(Long vehicleId) {
        Vehicle entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        } else {
            repository.deleteById(vehicleId);
            return true;
        }
    }
    public VehicleResponse findById(Long vehicleId) {
        Vehicle entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.entityToModel(entity);
        return VehicleModel.modelToDto(model);
    }

    public VehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest request) {
        Vehicle toUpdate = repository.findById(vehicleId).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.dtoToModel(request);
        Vehicle entity = VehicleModel.modelToEntity(model);
        Vehicle savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);
    }

    public List<Vehicle> getAll() throws Exception {
        List<Vehicle> vehicles = repository.findAll();
        if(vehicles.isEmpty()){
            throw new Exception("Ops, looks like there is nothing here...");
        }
        return vehicles;
    }
}


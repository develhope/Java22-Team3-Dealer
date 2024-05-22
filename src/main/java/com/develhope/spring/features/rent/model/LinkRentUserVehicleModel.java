package com.develhope.spring.features.rent.model;

import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleRequestDTO;
import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleResponseDTO;
import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRentUserVehicleModel {
    private Long id;
    private VehicleModel vehicle;
    private UserModel user;
    private RentModel rental;

    public LinkRentUserVehicleModel(VehicleModel vehicle, UserModel user, RentModel rental) {
        this.vehicle = vehicle;
        this.user = user;
        this.rental = rental;
    }

    public LinkRentUserVehicleResponseDTO modelToDto(LinkRentUserVehicleModel model) {
        return new LinkRentUserVehicleResponseDTO(model.getId(),VehicleModel.modelToDto(model.getVehicle()),UserModel.modelToDto(model.getUser()),RentModel.modelToDTO(model.getRental()));
    }

    public LinkRentUserVehicleModel dtoToModel(LinkRentUserVehicleRequestDTO request){
        return new LinkRentUserVehicleModel(VehicleModel.dtoToModel(request.getVehicle()),UserModel.dtoToModel(request.getUser()),RentModel.dtoToModel(request.getRental()));
    }

    public LinkRentUserVehicleEntity modelToEntity(LinkRentUserVehicleModel model) {
        return new LinkRentUserVehicleEntity(model.getId(), UserModel.modelToEntity(model.getUser()),VehicleModel.modelToEntity(model.getVehicle()),RentModel.modelToEntity(model.getRental()));
    }
    public LinkRentUserVehicleModel entityToModel(LinkRentUserVehicleEntity entity){
        return new LinkRentUserVehicleModel(entity.getId(), VehicleModel.entityToModel(entity.getVehicleEntity()),UserModel.entityToModel(entity.getUserEntity()),RentModel.entityToModel(entity.getRentEntity()));
    }

}


package com.develhope.spring.features.rent.model;

import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleRequestDTO;
import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleResponseDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
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
        return new LinkRentUserVehicleResponseDTO();
    }

    public LinkRentUserVehicleModel modelToDTO(LinkRentUserVehicleRequestDTO request){
        return new LinkRentUserVehicleModel();
    }

    public LinkRentUserVehicleEntity modelToEntity(LinkRentUserVehicleModel model) {
        return new LinkRentUserVehicleEntity();
    }
    public LinkRentUserVehicleModel entityToModel(LinkRentUserVehicleEntity entity){
        return new LinkRentUserVehicleModel();
    }

}


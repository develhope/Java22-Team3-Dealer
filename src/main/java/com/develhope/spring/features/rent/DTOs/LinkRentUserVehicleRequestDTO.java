package com.develhope.spring.features.rent.DTOs;

import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRentUserVehicleRequestDTO {
    private VehicleResponse vehicleDTO;
    private UserResponse userDTO;
    private RentalResponseDTO rentalDTO;
}

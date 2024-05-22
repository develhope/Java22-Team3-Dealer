package com.develhope.spring.features.rent.DTOs;

import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRentUserVehicleRequestDTO {
    private VehicleRequest vehicle;
    private UserRequest user;
    private RentalRequestDTO rental;
}

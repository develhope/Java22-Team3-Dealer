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
public class LinkRentUserVehicleResponseDTO {
private Long id;
private VehicleResponse vehicle;
private UserResponse user;
private RentalResponseDTO rental;
}

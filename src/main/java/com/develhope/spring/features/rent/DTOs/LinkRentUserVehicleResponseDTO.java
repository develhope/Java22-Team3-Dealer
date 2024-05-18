package com.develhope.spring.features.rent.DTOs;

import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkRentUserVehicleResponseDTO {
private List<RentalResponseDTO> rentalResponseDTOs;
private List<UserResponse> users;
private List<VehicleResponse> vehicles;

}

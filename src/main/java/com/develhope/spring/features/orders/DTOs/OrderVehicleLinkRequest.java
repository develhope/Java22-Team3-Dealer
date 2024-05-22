package com.develhope.spring.features.orders.DTOs;

import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVehicleLinkRequest {
    private OrderResponseDto orderDto;
    private VehicleResponse vehicleDto;
    private UserResponse userDto;

}



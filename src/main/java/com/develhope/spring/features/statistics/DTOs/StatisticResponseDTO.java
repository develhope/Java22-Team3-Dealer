package com.develhope.spring.features.statistics.DTOs;

import com.develhope.spring.features.orders.DTOs.OrderResponseDto;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
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
public class StatisticResponseDTO {
    private Long id;
    private UserResponse user;
    private VehicleResponse vehicle;
    private RentalResponseDTO rent;
    private OrderResponseDto order;
    private PurchaseResponseDTO purchase;
}

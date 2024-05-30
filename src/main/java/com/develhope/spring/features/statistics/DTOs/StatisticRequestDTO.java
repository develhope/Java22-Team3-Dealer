package com.develhope.spring.features.statistics.DTOs;

import com.develhope.spring.features.orders.DTOs.OrderRequestDto;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
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
public class StatisticRequestDTO {
    private UserRequest user;
    private VehicleRequest vehicle;
    private RentalRequestDTO rent;
    private OrderRequestDto order;
    private PurchaseRequestDTO purchase;
}

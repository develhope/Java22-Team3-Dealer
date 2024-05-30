package com.develhope.spring.features.purchase.DTO;

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
public class LinkPurchaseUserVehicleRequestDTO {
    private VehicleRequest vehicleRequestDTO;
    private UserRequest userRequestDTO;
    private PurchaseResponseDTO purchaseDTO;
}

package com.develhope.spring.features.purchase.DTO;

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
public class LinkPurchaseUserVehicleResponseDTO {
    private Long id;
    private VehicleResponse vehicleResponseDTO;
    private UserResponse userResponseDTO;
    private PurchaseResponseDTO purchaseDTO;
}

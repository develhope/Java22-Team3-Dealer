package com.develhope.spring.features.purchase.DTO;

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
public class LinkPurchaseUserVehicleRequestDTO {
    private VehicleResponse vehicleDTO;
    private UserResponse userDTO;
    private PurchaseResponseDTO purchaseResponseDTO;
}

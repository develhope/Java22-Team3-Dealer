package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.purchase.DTO.LinkPurchaseUserVehicleRequestDTO;
import com.develhope.spring.features.purchase.DTO.LinkPurchaseUserVehicleResponseDTO;
import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleRequestDTO;
import com.develhope.spring.features.rent.DTOs.LinkRentUserVehicleResponseDTO;
import com.develhope.spring.features.rent.entities.LinkRentUserVehicleEntity;
import com.develhope.spring.features.rent.model.LinkRentUserVehicleModel;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkPurchaseUserVehicleModel {
    private Long id;
    private VehicleModel vehicle;
    private UserModel user;
    private PurchaseModel purchaseModel;

    public LinkPurchaseUserVehicleModel(VehicleModel vehicle, UserModel user, PurchaseModel purchaseModel) {
        this.vehicle = vehicle;
        this.user = user;
        this.purchaseModel = purchaseModel;
    }

    public LinkPurchaseUserVehicleResponseDTO modelToDto(LinkPurchaseUserVehicleModel model) {
        return new LinkPurchaseUserVehicleResponseDTO();
    }

    public LinkPurchaseUserVehicleModel modelToDTO(LinkPurchaseUserVehicleRequestDTO request){
        return new LinkPurchaseUserVehicleModel();
    }

    public LinkPurchaseUserVehicleEntity modelToEntity(LinkPurchaseUserVehicleModel model) {
        return new LinkPurchaseUserVehicleEntity();
    }
    public LinkPurchaseUserVehicleModel entityToModel(LinkPurchaseUserVehicleEntity entity){
        return new LinkPurchaseUserVehicleModel();
    }
}

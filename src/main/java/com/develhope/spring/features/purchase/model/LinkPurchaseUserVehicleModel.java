package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.purchase.DTO.LinkPurchaseUserVehicleRequestDTO;
import com.develhope.spring.features.purchase.DTO.LinkPurchaseUserVehicleResponseDTO;
import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
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
        return new LinkPurchaseUserVehicleResponseDTO(model.getId(),VehicleModel.modelToDto(model.getVehicle()),UserModel.modelToDto(model.getUser()), PurchaseModel.modelToDTO(model.getPurchaseModel()));
    }

    public LinkPurchaseUserVehicleModel dtoToModel(LinkPurchaseUserVehicleRequestDTO request){
        return new LinkPurchaseUserVehicleModel(VehicleModel.dtoToModel(request.getVehicleRequestDTO()),UserModel.dtoToModel(request.getUserRequestDTO()),PurchaseModel.dtoToModel(request.getPurchaseDTO()));
    }

    public LinkPurchaseUserVehicleEntity modelToEntity(LinkPurchaseUserVehicleModel model) {
        return new LinkPurchaseUserVehicleEntity(model.getId(), UserModel.modelToEntity(model.getUser()),VehicleModel.modelToEntity(model.getVehicle()),PurchaseModel.modelToEntity(model.getPurchaseModel()));
    }
    public LinkPurchaseUserVehicleModel entityToModel(LinkPurchaseUserVehicleEntity entity){
        return new LinkPurchaseUserVehicleModel(entity.getId(), VehicleModel.entityToModel(entity.getVehicleEntity()),UserModel.entityToModel(entity.getUserEntity()),PurchaseModel.entityToModel(entity.getPurchaseEntity()));
    }
}

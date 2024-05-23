package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Long id;
    private BigDecimal purchaseDeposit;
    private OffsetDateTime purchaseDate;
    private Boolean isPayed;
    private Long vehicleId;

    public PurchaseModel(BigDecimal purchaseDeposit, OffsetDateTime purchaseDate, Boolean isPayed, Long vehicleId) {
        this.purchaseDeposit = purchaseDeposit;
        this.purchaseDate = purchaseDate;
        this.isPayed = isPayed;
        this.vehicleId = vehicleId;
    }

    public static PurchaseResponseDTO modelToDTO(PurchaseModel model){
        return new PurchaseResponseDTO(model.getId(), model.getPurchaseDeposit(),model.getIsPayed(),model.getVehicleId(),model.getPurchaseDate());
    }
        public static PurchaseModel dtoToModel(PurchaseRequestDTO request){
            return new PurchaseModel(request.getPurchaseDeposit(),request.getOrderDate(),request.isPayed(),request.getVehicleID());
        }
        public static PurchaseEntity modelToEntity(PurchaseModel model){
            return new PurchaseEntity(model.getId(), model.getPurchaseDeposit(),model.getIsPayed(),model.getPurchaseDate(),model.getVehicleId());
        }
        public static PurchaseModel entityToModel(PurchaseEntity entity){
            return new PurchaseModel(entity.getId(), entity.getPurchaseDeposit(),entity.getPurchaseDate(),entity.getIsPayed(),entity.getVehicleId());
        }
    }
=======
import com.develhope.spring.features.orders.entity.OrderStatus;
import com.develhope.spring.features.purchase.DTO.PurchaseRequest;
import com.develhope.spring.features.purchase.DTO.PurchaseResponse;
import com.develhope.spring.features.purchase.entities.PurchaseEntity;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseModel {
    private BigDecimal deposit;
    private boolean payed;
    private OrderStatus status;
    private VehicleResponse vehicle;

    private OffsetDateTime purchaseDate;

    public static PurchaseEntity dtoToEntity(PurchaseRequest purchaseRequest) {
        PurchaseEntity purchase = new PurchaseEntity();
//        purchase.setDeposit(purchaseRequest.getDeposit());
//        purchase.setPayed(purchaseRequest.isPayed());
//        purchase.setStatus(OrderStatus.convertStringToStatus(purchaseRequest.getStatus()));
//        purchase.setPurchaseDate(purchaseRequest.getOrderDate());
        return purchase;
    }

    public static PurchaseResponse entityToDto(PurchaseEntity purchaseEntity) {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
//        purchaseResponse.setDeposit(purchase.getDeposit());
//        purchaseResponse.setPayed(purchase.isPayed());
//        purchaseResponse.setStatus(purchase.getStatus().toString());
//        purchaseResponse.setPurchaseDate(purchase.getPurchaseDate());
        return purchaseResponse;
    }

    public static PurchaseRequest entityDtoRequest(PurchaseEntity purchase) {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
//        purchaseRequest.setDeposit(purchase.getDeposit());
//        purchaseRequest.setPayed(purchase.isPayed());
//        purchaseRequest.setStatus(purchase.getStatus().toString());
//        purchaseRequest.setOrderDate(purchase.getPurchaseDate());
        return purchaseRequest;
    }
}

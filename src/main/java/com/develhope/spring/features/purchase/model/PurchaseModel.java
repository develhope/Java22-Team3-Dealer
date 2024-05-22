package com.develhope.spring.features.purchase.model;

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

    @NotNull
    private BigDecimal deposit;

    @NotNull
    private boolean payed;

    @NotNull
    private OrderStatus status;

    @NotNull
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
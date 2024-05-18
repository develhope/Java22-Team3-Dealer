package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.orders.entity.OrderStatus;
import com.develhope.spring.features.purchase.DTO.PurchaseRequest;
import com.develhope.spring.features.purchase.DTO.PurchaseResponse;
import com.develhope.spring.features.purchase.entity.Purchase;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseModel {
    private BigDecimal deposit;
    private boolean payed;
    private OrderStatus status;
    private OffsetDateTime purchaseDate;

    public static Purchase dtoToEntity(PurchaseRequest purchaseRequest) {
        Purchase purchase = new Purchase();
        purchase.setDeposit(purchaseRequest.getDeposit());
        purchase.setPayed(purchaseRequest.isPayed());
        purchase.setStatus(OrderStatus.convertStringToStatus(purchaseRequest.getStatus()));
        purchase.setPurchaseDate(purchaseRequest.getOrderDate());
        return purchase;
    }

    public static PurchaseResponse entityToDto(Purchase purchase) {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setDeposit(purchase.getDeposit());
        purchaseResponse.setPayed(purchase.isPayed());
        purchaseResponse.setStatus(purchase.getStatus().toString());
        purchaseResponse.setPurchaseDate(purchase.getPurchaseDate());
        return purchaseResponse;
    }

    public static PurchaseRequest entityDtoRequest(Purchase purchase) {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setDeposit(purchase.getDeposit());
        purchaseRequest.setPayed(purchase.isPayed());
        purchaseRequest.setStatus(purchase.getStatus().toString());
        purchaseRequest.setOrderDate(purchase.getPurchaseDate());
        return purchaseRequest;
    }
}

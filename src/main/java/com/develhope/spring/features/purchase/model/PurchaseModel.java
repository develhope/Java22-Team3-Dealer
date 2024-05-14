package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.Vehicle.entity.Vehicle;
import com.develhope.spring.features.orders.DTOs.CreateOrderRequest;
import com.develhope.spring.features.orders.DTOs.OrderResponse;
import com.develhope.spring.features.orders.entity.Order;
import com.develhope.spring.features.orders.entity.OrderStatus;
import com.develhope.spring.features.purchase.DTO.PurchaseRequest;
import com.develhope.spring.features.purchase.DTO.PurchaseResponse;
import com.develhope.spring.features.purchase.entity.Purchase;
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
    private Vehicle vehicle;

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

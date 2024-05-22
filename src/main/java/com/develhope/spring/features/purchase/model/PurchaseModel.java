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

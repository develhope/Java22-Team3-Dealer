package com.develhope.spring.features.purchase.model;

import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Long id;
    private BigDecimal purchaseDeposit;
    private Boolean isPayed;
    private LocalDateTime purchaseDate;
    private Long costumerId;
    private Long sellerId;
    private Long vehicleId;

    public PurchaseModel(BigDecimal purchaseDeposit, Boolean isPayed, LocalDateTime purchaseDate, Long costumerId, Long sellerId, Long vehicleId) {
        this.purchaseDeposit = purchaseDeposit;
        this.isPayed = isPayed;
        this.purchaseDate = purchaseDate;
        this.costumerId = costumerId;
        this.sellerId = sellerId;
        this.vehicleId = vehicleId;
    }

    public static PurchaseResponseDTO modelToDTO(PurchaseModel model) {
        return new PurchaseResponseDTO(model.getId(), model.getPurchaseDeposit(), model.getIsPayed(), model.getPurchaseDate(), model.getCostumerId(), model.getSellerId(), model.getVehicleId());
    }

    public static PurchaseModel dtoToModel(PurchaseRequestDTO request) {
        return new PurchaseModel(request.getPurchaseDeposit(), request.getIsPayed(), request.getPurchaseDate(), request.getCostumerId(), request.getSellerId(), request.getVehicleId());
    }

    public static PurchaseEntity modelToEntity(PurchaseModel model) {
        return new PurchaseEntity(model.getId(), model.getPurchaseDeposit(), model.getIsPayed(), model.getPurchaseDate(), model.getCostumerId(), model.getSellerId(), model.getVehicleId());
    }

    public static PurchaseModel entityToModel(PurchaseEntity entity) {
        return new PurchaseModel(entity.getId(), entity.getPurchaseDeposit(), entity.getIsPayed(), entity.getPurchaseDate(), entity.getCostumerId(), entity.getSellerId(), entity.getVehicleId());
    }
}

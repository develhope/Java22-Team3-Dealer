package com.develhope.spring.features.rent.model;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.entities.RentEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentModel {
    private Long id;
    private BigDecimal rentalDeposit;
    private BigDecimal dailyRental;
    private BigDecimal totalRent;
    private OffsetDateTime rentalStart;
    private OffsetDateTime rentalEnd;
    private Boolean isPayed;
    private Long costumerId;
    private Long sellerId;
    private Long vehicleId;

    public RentModel(BigDecimal rentalDeposit, BigDecimal dailyRental, BigDecimal totalRent, OffsetDateTime rentalStart, OffsetDateTime rentalEnd, Boolean isPayed, Long costumerId, Long sellerId, Long vehicleId) {
        this.rentalDeposit = rentalDeposit;
        this.dailyRental = dailyRental;
        this.totalRent = totalRent;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.isPayed = isPayed;
        this.costumerId = costumerId;
        this.sellerId = sellerId;
        this.vehicleId = vehicleId;
    }

    public static RentalResponseDTO modelToDTO(RentModel model){
        return new RentalResponseDTO(model.getId(), model.getRentalDeposit(),model.getDailyRental(),model.getTotalRent(),model.getRentalStart(),model.getRentalEnd(),model.getIsPayed(),model.getCostumerId(),model.getSellerId(), model.getVehicleId());
    }
    public static RentModel dtoToModel(RentalRequestDTO request){
        return new RentModel(request.getRentalDeposit(),request.getDailyRental(),request.getTotalRent(),request.getRentalStart(),request.getRentalEnd(),request.getIsPayed(),request.getCostumerId(),request.getSellerId(), request.getVehicleId());
    }
    public static RentEntity modelToEntity(RentModel model){
        return new RentEntity(model.getId(), model.getRentalDeposit(),model.getDailyRental(),model.getTotalRent(),model.getRentalStart(),model.getRentalEnd(),model.getIsPayed(),model.getCostumerId(),model.getSellerId(), model.getVehicleId());
    }
    public static RentModel entityToModel(RentEntity entity){
        return new RentModel(entity.getId(), entity.getRentalDeposit(),entity.getDailyRental(),entity.getTotalRent(),entity.getRentalStart(),entity.getRentalEnd(),entity.getIsPayed(),entity.getCostumerId(),entity.getSellerId(), entity.getVehicleId());
    }
}


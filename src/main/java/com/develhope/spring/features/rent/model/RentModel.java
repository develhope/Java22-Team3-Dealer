package com.develhope.spring.features.rent.model;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.RentEntity;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
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
    private VehicleEntity vehicle;

    public RentModel(BigDecimal rentalDeposit, BigDecimal dailyRental, BigDecimal totalRent, OffsetDateTime rentalStart, OffsetDateTime rentalEnd, Boolean isPayed, VehicleEntity vehicle) {
        this.rentalDeposit = rentalDeposit;
        this.dailyRental = dailyRental;
        this.totalRent = totalRent;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
        this.isPayed = isPayed;
        this.vehicle = vehicle;
    }

    public static RentalResponseDTO modelToDTO(RentModel model){
        return new RentalResponseDTO(model.getId(), model.getRentalDeposit(),model.getDailyRental(),model.getTotalRent(),model.getRentalStart(),model.getRentalEnd(),model.getIsPayed(),model.getVehicle());
    }
    public static RentModel dtoToModel(RentalRequestDTO request){
        return new RentModel(request.getRentalDeposit(),request.getDailyRental(),request.getTotalRent(),request.getRentalStart(),request.getRentalEnd(),request.getIsPayed(),request.getVehicle());
    }
    public static RentEntity modelToEntity(RentModel model){
        return new RentEntity(model.getId(), model.getRentalDeposit(),model.getDailyRental(),model.getTotalRent(),model.getRentalStart(),model.getRentalEnd(),model.getIsPayed(),model.getVehicle());
    }
    public static RentModel entityToModel(RentEntity entity){
        return new RentModel(entity.getRentalDeposit(),entity.getDailyRental(),entity.getTotalRent(),entity.getRentalStart(),entity.getRentalEnd(),entity.getIsPayed(),entity.getVehicle());
    }
}


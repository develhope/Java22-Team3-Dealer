package com.develhope.spring.features.statistics.models;

import com.develhope.spring.features.orders.model.OrderModel;
import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.rent.model.RentModel;
import com.develhope.spring.features.statistics.DTOs.StatisticRequestDTO;
import com.develhope.spring.features.statistics.DTOs.StatisticResponseDTO;
import com.develhope.spring.features.statistics.entities.StatisticsEntity;
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
public class StatisticModel {
    private Long id;
    private UserModel user;
    private VehicleModel vehicle;
    private RentModel rent;
    private OrderModel order;
    private PurchaseModel purchase;

    public StatisticModel(UserModel user, VehicleModel vehicle, RentModel rent, OrderModel order, PurchaseModel purchase) {
        this.user = user;
        this.vehicle = vehicle;
        this.rent = rent;
        this.order = order;
        this.purchase = purchase;
    }

    public StatisticResponseDTO modelToDto(StatisticModel model) {
        return new StatisticResponseDTO(model.getId(), UserModel.modelToDto(model.getUser()),VehicleModel.modelToDto(model.getVehicle()),RentModel.modelToDTO(model.getRent()),OrderModel.modelToDto(model.getOrder()),PurchaseModel.modelToDTO(model.getPurchase()));
    }
    public StatisticModel dtoToModel(StatisticRequestDTO request){
        return new StatisticModel(UserModel.dtoToModel(request.getUser()),VehicleModel.dtoToModel(request.getVehicle()),RentModel.dtoToModel(request.getRent()),OrderModel.dtoToModel(request.getOrder()),PurchaseModel.dtoToModel(request.getPurchase()));
    }

    public StatisticsEntity modelToEntity(StatisticModel model) {
        return new StatisticsEntity(model.getId(), UserModel.modelToEntity(model.getUser()),VehicleModel.modelToEntity(model.getVehicle()),RentModel.modelToEntity(model.getRent()),OrderModel.modelToEntity(model.getOrder()),PurchaseModel.modelToEntity(model.getPurchase()));
    }
    public StatisticModel entityToModel(StatisticsEntity entity){
        return new StatisticModel(entity.getId(), UserModel.entityToModel(entity.getUserEntity()),VehicleModel.entityToModel(entity.getVehicleEntity()),RentModel.entityToModel(entity.getRentEntity()),OrderModel.entityToModel(entity.getOrderEntity()),PurchaseModel.entityToModel(entity.getPurchaseEntity()));
    }
}

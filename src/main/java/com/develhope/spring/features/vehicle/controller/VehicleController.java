package com.develhope.spring.features.vehicle.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.VehicleError;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity, @RequestBody VehicleRequest request) {
        Either<GenericErrors, VehicleResponse> result = service.createVehicle(UserModel.entityToModel(userEntity), request);
        return result.fold(
                errors -> new ResponseEntity<>(new GenericErrors(errors.getCode(), errors.getMessage()), HttpStatus.valueOf(errors.getCode())),
                createVehicle -> new ResponseEntity<>(HttpStatus.CREATED)
        );
    }

    @PostMapping("/delete_Vehicle{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@AuthenticationPrincipal UserEntity user, @RequestBody Long vehicleId) {
        Either<GenericErrors, Boolean> result = service.deleteVehicleByID(UserModel.entityToModel(user), vehicleId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                delete -> new ResponseEntity<>(delete, HttpStatus.OK)
        );
    }

    @PostMapping("/update_Vehicle")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody VehicleRequest request) {
        Either<GenericErrors, VehicleResponse> result = service.updateVehicle(id, request);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
    }


    @PostMapping("/getVehicleEntity")
    public ResponseEntity<?> getById(Long id) {
        Either<GenericErrors, VehicleResponse> result = service.findById(id);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
    }

    @GetMapping("GetAll vehicles")
    public ResponseEntity<?> getAllVehicle() {
        Either<GenericErrors, List<VehicleEntity>> result = service.getAll();

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
    }
}

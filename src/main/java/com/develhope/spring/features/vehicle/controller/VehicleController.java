package com.develhope.spring.features.vehicle.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
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

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity, @RequestBody VehicleRequest request) {
        Either<GenericErrors, VehicleResponse> result = service.createVehicle(UserModel.entityToModel(userEntity), request);
        return result.fold(
                errors -> new ResponseEntity<>(new GenericErrors(errors.getCode(),errors.getMessage()), HttpStatus.valueOf(errors.getCode())),
                createVehicle-> new ResponseEntity<>(HttpStatus.CREATED)
        );
    }

    @PostMapping("/delete_Vehicle{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@AuthenticationPrincipal UserEntity user,@RequestBody Long vehicleId){
        Either<GenericErrors, Boolean> result = service.deleteVehicleByID(UserModel.entityToModel(user),vehicleId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                delete -> new ResponseEntity<>(delete, HttpStatus.OK)
        );
    }


    @PostMapping("/update_Vehicle")
    public ResponseEntity<?> update (Long id, VehicleRequest request){
        VehicleResponse updated = service.updateVehicle(id,request);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @Operation(summary = "Get vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Research executed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PostMapping("/getVehicleEntity")
    public ResponseEntity<?> getById (Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Get all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All vehicles retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PostMapping("/getAllVehicle")
    public ResponseEntity<?> getAll ( ) throws Exception {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}

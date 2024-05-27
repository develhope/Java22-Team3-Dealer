package com.develhope.spring.features.rent.controllers;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.services.RentService;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v2/rentals")
public class RentController {
    @Autowired
    RentService service;

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity, @RequestBody RentalRequestDTO request, @PathVariable Long vehicleId) {
        Either<GenericErrors, RentalResponseDTO> result = service.createRental(UserModel.entityToModel(userEntity), request, vehicleId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error creating new rental"), HttpStatus.valueOf(error.getCode())),
                createRental -> new ResponseEntity<>(createRental, HttpStatus.CREATED)
        );
    }

    @DeleteMapping("/delete/rental/{rentalId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        Either<GenericErrors, Boolean> result = service.deleteRentalById(UserModel.entityToModel(userEntity), id);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                delete -> new ResponseEntity<>(delete, HttpStatus.OK)
        );
    }

    //here the userEntity found is converted into a model
    @PutMapping("/update/{rentId}")
    public ResponseEntity<?> updateRentById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long rentId, @RequestBody RentalRequestDTO request) {
        Either<GenericErrors, RentalResponseDTO> result  = service.updateLinkRentById(UserModel.entityToModel(userEntity), rentId, request);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                update -> new ResponseEntity<>(update, HttpStatus.OK)
        );
    }

    @GetMapping("/getSingleRent/{rentId}")
    public ResponseEntity<?> getSingleById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long rentId) {
        Either<GenericErrors, RentalResponseDTO> result = service.getSingleRental(UserModel.entityToModel(userEntity), rentId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                single -> new ResponseEntity<>(single, HttpStatus.OK)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserEntity user) {
        Either<GenericErrors, List<RentalResponseDTO>> result  = service.getAllByUserRole(UserModel.entityToModel(user));
        if (result.isEmpty()) {
            return ResponseEntity.status(422).body("Your list of rentals is empty");
        }
        return ResponseEntity.ok(result);
    }
}

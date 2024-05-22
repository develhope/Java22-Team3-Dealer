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
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity,@RequestBody RentalRequestDTO request, @PathVariable Long vehicleId, @RequestParam(required = false) Long costumerId) {
        Either<GenericErrors,RentalResponseDTO> result = service.createRental(UserModel.entityToModel(userEntity), request, vehicleId,costumerId);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.status(420).body("Impossible to create new rental");
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @DeleteMapping("/delete/rental/{rentalId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        Either<GenericErrors, Boolean> result = service.deleteRentalById(UserModel.entityToModel(userEntity),id);
        if (result.isRight()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
    }
//here the userEntity found is converted into a model
    @PutMapping("/update/{rentId}")
    public ResponseEntity<?> updateRentById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long rentId, @RequestBody RentalRequestDTO request){
        RentalResponseDTO updatedRent = service.updateLinkRentById(UserModel.entityToModel(userEntity), rentId, request);
        if (updatedRent == null) {
            return ResponseEntity.status(422).body("No rentals found for the rentId: " + rentId);
        }
        return ResponseEntity.ok(updatedRent);
    }

    @GetMapping("/getSingleRent/{rentId}")
    public ResponseEntity<?> getSingleById(@AuthenticationPrincipal UserEntity userEntity,@PathVariable Long rentId){
        RentalResponseDTO rental = service.getSingleRental(userEntity, rentId);
        if(rental == null){
            return  ResponseEntity.status(422).body("No rental found by the id: " + rentId);
        }
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserEntity user){
        List<RentalResponseDTO> result = service.getAllByUserRole(user);
        if(result.isEmpty()){
            return ResponseEntity.status(422).body("Your list of rentals is empty");
        }
        return ResponseEntity.ok(result);
    }
}

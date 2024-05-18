package com.develhope.spring.features.rent.controllers;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.rent.services.RentService;
import com.develhope.spring.features.user.entity.UserEntity;
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
    public ResponseEntity<?> create(@RequestBody RentalRequestDTO request,@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long vehicleId, @RequestParam(required = false) Long costumerId) {
        RentalResponseDTO result = service.createRental(request, userEntity, vehicleId,costumerId);
        if (result == null) {
            return ResponseEntity.status(420).body("Impossible to create new rental");
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @DeleteMapping("/delete/rental/{rentalId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        boolean result = service.deleteRentalById(userEntity,id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("Impossible to delete the rental with the id: " + id);
        }
    }

    @PutMapping("/update/{rentId}")
    public ResponseEntity<?> updateById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long rentId, @RequestBody RentalRequestDTO request){
        RentalResponseDTO updatedRent = service.updateLinkRentById(userEntity, rentId, request);
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
    public ResponseEntity<?> getAll(){
        List<RentalResponseDTO> result = service.getAll();
        if(result.isEmpty()){
            return ResponseEntity.status(422).body("Your list of rentals is empty");
        }
        return ResponseEntity.ok(result);
    }
}

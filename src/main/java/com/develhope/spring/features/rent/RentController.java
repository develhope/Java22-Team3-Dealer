package com.develhope.spring.features.rent;

import com.develhope.spring.features.rent.DTOs.RentalRequestDTO;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v2/rentals")
public class RentController {
    @Autowired
    RentService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RentalRequestDTO request) {
        RentalResponseDTO result = service.createRental(request);
        if (result == null) {
            return ResponseEntity.status(420).body("Impossible to create new rental");
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @DeleteMapping("/delete/rental/{rentalId}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean result = service.deleteRentalById(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("Impossible to delete the rental with the id: " + id);
        }
    }

    @PutMapping("/update/{rentId}")
    public ResponseEntity<?> updateById(@PathVariable Long rentId, @RequestBody RentalRequestDTO request){
        RentalResponseDTO updatedRent = service.updateRentById(rentId, request);
        if (updatedRent == null) {
            return ResponseEntity.status(422).body("No rentals found for the rentId: " + rentId);
        }
            return ResponseEntity.ok(updatedRent);
    }

    @GetMapping("/getSingleRent/{rentId}")
    public ResponseEntity<?> getSingleById(@PathVariable Long rentId){
        RentalResponseDTO rental = service.getSingleRental(rentId);
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

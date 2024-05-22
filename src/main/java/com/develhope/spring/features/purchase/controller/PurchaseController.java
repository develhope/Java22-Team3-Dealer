package com.develhope.spring.features.purchase.controller;

import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.service.PurchaseService;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
@Autowired
PurchaseService purchaseService;

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity, @RequestBody PurchaseRequestDTO request, @PathVariable Long vehicleId) {
        PurchaseResponseDTO purchase = purchaseService.createPurchase(request,userEntity,vehicleId);
        if (purchase == null) {
            return ResponseEntity.status(420).body("Impossible to create new rental");
        } else {
            return ResponseEntity.ok(purchase);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        boolean result = purchaseService.deletePurchaseById(userEntity,id);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(421).body("Impossible to delete the purchase with the id: " + id);
        }
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<?> updateRentById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long Id, @RequestBody PurchaseRequestDTO request){
        PurchaseResponseDTO updatedRent = purchaseService.updateLinkPurchaseById(UserModel.entityToModel(userEntity), Id, request);
        if (updatedRent == null) {
            return ResponseEntity.status(422).body("No purchases found for the Id: " + Id);
        }
        return ResponseEntity.ok(updatedRent);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<?> getSingleById(@AuthenticationPrincipal UserEntity userEntity,@PathVariable Long Id){
        PurchaseResponseDTO purchaseResponseDTO = purchaseService.getSinglePurchase(userEntity, Id);
        if(purchaseResponseDTO == null){
            return  ResponseEntity.status(422).body("No rental found by the id: " + Id);
        }
        return ResponseEntity.ok(purchaseResponseDTO);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserEntity user){
        List<PurchaseResponseDTO> result = purchaseService.getAllForUser_id(user);
        if(result.isEmpty()){
            return ResponseEntity.status(422).body("Your purchase list is empty");
        }
        return ResponseEntity.ok(result);
    }

}

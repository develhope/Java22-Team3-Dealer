package com.develhope.spring.features.purchase.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.service.PurchaseService;
import com.develhope.spring.features.rent.DTOs.RentalResponseDTO;
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
@RequestMapping("/purchase")
public class PurchaseController {
@Autowired
PurchaseService purchaseService;

    @PostMapping("/create/{vehicleId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserEntity userEntity, @RequestBody PurchaseRequestDTO request, @PathVariable Long vehicleId) {
        Either<GenericErrors, PurchaseResponseDTO> purchase = purchaseService.createPurchase(UserModel.entityToModel(userEntity), request,vehicleId);
        return purchase.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error creating new rental"), HttpStatus.valueOf(error.getCode())),
                createPurchase -> new ResponseEntity<>(createPurchase, HttpStatus.CREATED)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        Either<GenericErrors, Boolean> result = purchaseService.deletePurchaseById(UserModel.entityToModel(userEntity), id);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                delete -> new ResponseEntity<>(delete, HttpStatus.OK)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePurchaseById(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id, @RequestBody PurchaseRequestDTO request){
        Either<GenericErrors, PurchaseResponseDTO> upPurchase = purchaseService.updateLinkPurchaseById(UserModel.entityToModel(userEntity), id, request);
        return upPurchase.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                update -> new ResponseEntity<>(update, HttpStatus.OK)
        );
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<?> getSingleById(@AuthenticationPrincipal UserEntity userEntity,@PathVariable Long Id){
        Either<GenericErrors, PurchaseResponseDTO> purchaseResponseDTO = purchaseService.getSinglePurchase(UserModel.entityToModel(userEntity), Id);
        return purchaseResponseDTO.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                single -> new ResponseEntity<>(single, HttpStatus.OK)
        );
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserEntity user){
        Either<GenericErrors, List<PurchaseResponseDTO>> result = purchaseService.getAllByUserRole(UserModel.entityToModel(user));
        if(result.isEmpty()){
            return ResponseEntity.status(422).body("Your purchase list is empty");
        }
        return ResponseEntity.ok(result);
    }

}

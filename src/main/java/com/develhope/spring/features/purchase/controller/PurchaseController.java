package com.develhope.spring.features.purchase.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.service.PurchaseService;
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
@RequestMapping("v2/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@AuthenticationPrincipal UserEntity userEntity, @RequestBody PurchaseRequestDTO request) {
        Either<GenericErrors, PurchaseResponseDTO> result = purchaseService.createPurchase(UserModel.entityToModel(userEntity), request);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), "Error creating new purchase"), HttpStatus.valueOf(error.getCode())),
                created -> new ResponseEntity<>(created, HttpStatus.CREATED)
        );
    }

    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity<?> deletePurchase(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long purchaseId) {
        Either<GenericErrors, Boolean> result = purchaseService.deletePurchaseById(UserModel.entityToModel(userEntity), purchaseId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                deleted -> new ResponseEntity<>(deleted, HttpStatus.OK)
        );
    }

    @PutMapping("/update/{purchaseId}")
    public ResponseEntity<?> updatePurchase(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long purchaseId, @RequestBody PurchaseRequestDTO request) {
        Either<GenericErrors, PurchaseResponseDTO> result = purchaseService.updatePurchaseById(UserModel.entityToModel(userEntity), purchaseId, request);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                updated -> new ResponseEntity<>(updated, HttpStatus.OK)
        );
    }

    @GetMapping("/getSingle/{purchaseId}")
    public ResponseEntity<?> getSinglePurchase(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long purchaseId) {
        Either<GenericErrors, PurchaseResponseDTO> result = purchaseService.getPurchaseById(UserModel.entityToModel(userEntity), purchaseId);
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                single -> new ResponseEntity<>(single, HttpStatus.OK)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPurchases(@AuthenticationPrincipal UserEntity userEntity) {
        Either<GenericErrors, List<PurchaseResponseDTO>> result = purchaseService.getAllPurchases(UserModel.entityToModel(userEntity));
        return result.fold(
                error -> new ResponseEntity<>(new GenericErrors(error.getCode(), error.getMessage()), HttpStatus.valueOf(error.getCode())),
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        );
    }
}

package com.develhope.spring.features.purchase.controller;

import com.develhope.spring.features.purchase.DTO.PurchaseRequest;
import com.develhope.spring.features.purchase.DTO.PurchaseResponse;
import com.develhope.spring.features.purchase.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
@Autowired
private PurchaseService purchaseService;

public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Operation(summary = "Purchase confirmation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchased confirmed"),
            @ApiResponse(responseCode = "400", description = "Bad requests!!!")})
    @PostMapping("/createPurchase")
    public ResponseEntity<PurchaseResponse> createPurchase (@RequestBody PurchaseRequest request){
        PurchaseResponse purchaseResponse = purchaseService.createPurchase(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseResponse);
    }
    @Operation(summary = "Purchase update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PutMapping("/updatePurchase/{id}")
    public ResponseEntity<PurchaseResponse>updatePurchase(@PathVariable Long id, @RequestBody PurchaseRequest request){
        PurchaseResponse purchaseResponse = purchaseService.updatePurchase(id,request);
        return ResponseEntity.ok(purchaseResponse);
    }
    @Operation(summary = "Delete purchase by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @DeleteMapping("/deletePurchase/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id){
        purchaseService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Get purchase by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase found successfully"),
            @ApiResponse(responseCode = "404", description = "Purchase not found!")})
    @GetMapping("/getPurchase/{id}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(@PathVariable Long id){
        PurchaseResponse purchaseResponse = purchaseService.findById(id);
        return ResponseEntity.ok(purchaseResponse);
    }
    @Operation(summary = "Get all purchases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All purchases retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No purchases found!")})
    @GetMapping("/getAllPurchases")
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases() throws Exception{
        List<PurchaseResponse> purchase = purchaseService.getAllPurchase();
        return ResponseEntity.ok(purchase);
    }

}

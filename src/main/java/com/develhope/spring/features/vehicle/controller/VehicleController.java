package com.develhope.spring.features.vehicle.controller;

import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.DTOs.VehicleResponse;
import com.develhope.spring.features.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @Operation(summary = "Create vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new vehicle has been added to your table"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PostMapping("/createVehicle")
    public ResponseEntity<?> createVehicle( @RequestBody VehicleRequest request){
        VehicleResponse newVehicle = service.createVehicle(request);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete vehicle by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PostMapping("/deleteVehicle")
    public ResponseEntity<?> deleteVehicle(Long id){
        service.deleteVehicleByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update vehicle attributes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad requests!")})
    @PostMapping("/deleteVehicle")
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

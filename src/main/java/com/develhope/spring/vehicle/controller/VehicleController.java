package com.develhope.spring.Vehicle.controller;
import com.develhope.spring.Vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.Vehicle.DTOs.VehicleResponse;
import com.develhope.spring.Vehicle.service.VehicleService;
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
            @ApiResponse(responseCode = "400", description = "Bad request!")})
    @PostMapping("/createVehicle")
    public ResponseEntity<?> createVehicle(@RequestBody CreateVehicleRequest request){
        VehicleResponse newVehicle = service.createVehicle(request);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete vehicle by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request!")})
    @PostMapping("/deleteVehicle")
    public ResponseEntity<?> deleteVehicle(Long id){
        service.deleteVehicleByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update vehicle attributes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request!")})
    @PostMapping("/deleteVehicle")
    public ResponseEntity<?> update (Long id, CreateVehicleRequest request){
        VehicleResponse updated = service.updateVehicle(id,request);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @Operation(summary = "Get vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "This is the result of your research"),
            @ApiResponse(responseCode = "400", description = "Bad request!")})
    @PostMapping("/getVehicle")
    public ResponseEntity<?> getById (Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}

//    @Operation(summary = "Get vehicles by price")
//    @GetMapping("/byPrice")
//    public ResponseEntity<?> getVehicleByPrice(@AuthenticationPrincipal UserEntity user, @RequestParam(defaultValue = "0") BigDecimal minPrice, @RequestParam(defaultValue = "1000000000000") BigDecimal maxPrice) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByPrice(user, minPrice, maxPrice);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//
//    }
//
//    @Operation(summary = "Get vehicles by color")
//    @GetMapping("/byColor")
//    public ResponseEntity<?> getVehicleByColor(@AuthenticationPrincipal UserEntity user, @RequestParam String color) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByColor(user, color);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (IllegalArgumentException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//
//    }
//
//    @Operation(summary = "Get vehicles by brand")
//    @GetMapping("/byBrand")
//    public ResponseEntity<?> getVehicleByBrand(@AuthenticationPrincipal UserEntity user, @RequestParam String brand) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByBrand(user, brand);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (IllegalArgumentException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "Get vehicles by model")
//    @GetMapping("/byModel")
//    public ResponseEntity<?> getVehicleByModel(@AuthenticationPrincipal UserEntity user, @RequestParam String model) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByModel(user, model);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (IllegalArgumentException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "Get vehicles by type")
//    @GetMapping("/byType")
//    public ResponseEntity<?> getVehicleByType(@AuthenticationPrincipal UserEntity user, @RequestParam VehicleType vehicleType) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByType(user, vehicleType);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (CustomExceptions.InvalidTypeException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "Get vehicles by status")
//    @GetMapping("/byStatus")
//    public ResponseEntity<?> getVehicleByStatus(@AuthenticationPrincipal UserEntity user, @RequestParam VehicleStatus vehicleStatus) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByStatus(user, vehicleStatus);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (CustomExceptions.InvalidStatusException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "Get vehicles new")
//    @GetMapping("/isNew")
//    public ResponseEntity<?> getVehicleNew(@AuthenticationPrincipal UserEntity user, @RequestParam Boolean isNew) {
//        try {
//            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleNew(user, isNew);
//            return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
//        } catch (CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "update a vehicle")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @AuthenticationPrincipal UserEntity user, @RequestBody VehicleDTO updateVehicleDTO) {
//        try {
//            vehicleService.updateVehicle(id, user, updateVehicleDTO);
//            return new ResponseEntity<>(updateVehicleDTO, HttpStatus.OK);
//        } catch (CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//
//    @Operation(summary = "Change a vehicle status")
//    @PutMapping("/status/{id}")
//    public ResponseEntity<?> changeStatus(@PathVariable Long id, @AuthenticationPrincipal UserEntity user, @RequestBody VehicleStatusDTO vehicleStatusDTO) {
//        try {
//            VehicleDTO vehicleDTO = vehicleService.chanceStatus(id, user, vehicleStatusDTO);
//            return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
//        } catch (CustomExceptions.InvalidIdException | CustomExceptions.InvalidStatusException |
//                 CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//
//    }
//
//    @Operation(summary = "Delete a vehicle by Id")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteVehicle(@PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
//        try {
//            vehicleService.deleteVehicle(id, user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (CustomExceptions.InvalidIdException | CustomExceptions.AccessDeniedException e) {
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .body(Collections.singletonMap("error", e.getMessage()));
//        }
//    }
//}


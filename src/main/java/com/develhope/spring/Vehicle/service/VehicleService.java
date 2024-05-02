package com.develhope.spring.Vehicle.service;


import com.develhope.spring.User.entity.Role;
import com.develhope.spring.User.entity.Users;
import com.develhope.spring.Vehicle.DTO.VehicleDTO;
import com.develhope.spring.Vehicle.DTO.VehicleStatusDTO;
import com.develhope.spring.Vehicle.entity.Vehicle;
import com.develhope.spring.Vehicle.entity.VehicleStatus;
import com.develhope.spring.Vehicle.entity.VehicleType;
import com.develhope.spring.Vehicle.repository.VehicleRepository;
import com.develhope.spring.exeptions.CustomExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public void createVehicle(Users user, VehicleDTO vehicleDTO) {
        if (user.getRole() == Role.ADMIN) {
            Vehicle vehicle = convertToEntity(vehicleDTO);
            vehicleRepository.save(vehicle);
        } else {
            throw new CustomExceptions.AccessDeniedException("Only admin can");
        }
    }

    public VehicleDTO getVehicleById(Long vehicleId, Users user) {
        if (user.getRole() == Role.CUSTOMER || user.getRole() == Role.SALESMAN) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicle == null) {
                throw new CustomExceptions.InvalidIdException("Vehicle with this id not found: " + vehicleId);
            }
            return convertToDTO(vehicle);
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }

    }

    public List<VehicleDTO> getAllVehicle() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleList.isEmpty()) {
            throw new IllegalArgumentException("No vehicle found");
        } else {
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
        }
        return vehicleDTOList;
    }

    public List<VehicleDTO> getVehicleByPrice(Users user, BigDecimal minPrice, BigDecimal maxPrice) {//dare valori default in controller
        if (user.getRole() == Role.CUSTOMER) {
            if (minPrice.compareTo(maxPrice) > 0) {
                throw new IllegalArgumentException("the lower price cannot be greater than the higher price");
            }
            List<Vehicle> vehicleList = vehicleRepository.getAllVehicleByPrice(minPrice, maxPrice);
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleByColor(Users user, String color) {
        if (user.getRole() == Role.CUSTOMER) {
            List<Vehicle> vehicleList = vehicleRepository.getAllVehicleByColor(color);
            if (vehicleList.isEmpty()) {
                throw new IllegalArgumentException("No vehicle found with color: " + color);
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleByBrand(Users user, String brand) {
        if (user.getRole() == Role.CUSTOMER) {
            List<Vehicle> vehicleList = vehicleRepository.getAllVehicleByBrand(brand);
            if (vehicleList.isEmpty()) {
                throw new IllegalArgumentException("No vehicle found with brand: " + brand);
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleByModel(Users user, String model) {
        if (user.getRole() == Role.CUSTOMER) {
            List<Vehicle> vehicleList = vehicleRepository.getAllVehicleByModel(model);
            if (vehicleList.isEmpty()) {
                throw new IllegalArgumentException("No vehicle found with model: " + model);
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleByType(Users user, VehicleType vehicleType) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            if (vehicleType == VehicleType.CAR
                    || vehicleType == VehicleType.MOTORBIKE
                    || vehicleType == VehicleType.SCOOTER
                    || vehicleType == VehicleType.VAN) {
                List<Vehicle> vehicleList = vehicleRepository.getAllVehicleByType(vehicleType.toString());
                for (Vehicle vehicle : vehicleList) {
                    vehicleDTOList.add(convertToDTO(vehicle));
                }
            } else {
                throw new CustomExceptions.InvalidTypeException("Type not valid: " + vehicleType);
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleByStatus(Users user, VehicleStatus vehicleStatus) {
        if (user.getRole() == Role.CUSTOMER) {

            if (vehicleStatus == VehicleStatus.ORDERABLE
                    || vehicleStatus == VehicleStatus.PURCHASABLE
                    || vehicleStatus == VehicleStatus.NOT_AVAILABLE) {
                List<VehicleDTO> vehicleDTOList = new ArrayList<>();
                List<Vehicle> vehicleList = vehicleRepository.getAllVehicleStatus(vehicleStatus.toString());
                for (Vehicle vehicle : vehicleList) {
                    vehicleDTOList.add(convertToDTO(vehicle));
                }
                return vehicleDTOList;
            } else {
                throw new CustomExceptions.InvalidStatusException("Type not valid: " + vehicleStatus);
            }

        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public List<VehicleDTO> getVehicleNew(Users user, Boolean isNew) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            List<Vehicle> vehicleList = vehicleRepository.getAllVehicleNew(isNew);
            for (Vehicle vehicle : vehicleList) {
                vehicleDTOList.add(convertToDTO(vehicle));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("You don't have access");
        }
    }

    public void updateVehicle(Long vehicleId, Users user, VehicleDTO updateVehicleDTO) {
        if (user.getRole() == Role.ADMIN) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicle == null) {
                throw new CustomExceptions.InvalidIdException("Vehicle with this id not found: " + vehicleId);
            }
            Vehicle updateVehicle = convertToEntity(updateVehicleDTO);
            vehicle.setBrand(updateVehicle.getBrand());
            vehicle.setModel(updateVehicle.getModel());
            vehicle.setDisplacement(updateVehicle.getDisplacement());
            vehicle.setColor(updateVehicle.getColor());
            vehicle.setPower(updateVehicle.getPower());
            vehicle.setTransmission(updateVehicle.getTransmission());
            vehicle.setRegistrationYear(updateVehicle.getRegistrationYear());
            vehicle.setFullType(updateVehicle.getFullType());
            vehicle.setPrice(updateVehicle.getPrice());
            vehicle.setDiscount(updateVehicle.getDiscount());
            vehicle.setAccessories(updateVehicle.getAccessories());
            vehicle.setIsNew(updateVehicle.getIsNew());
            vehicle.setVehicleStatus(updateVehicle.getVehicleStatus());
            vehicle.setVehicleType(updateVehicle.getVehicleType());
            vehicleRepository.save(vehicle);
        } else {
            throw new CustomExceptions.AccessDeniedException("Only admin can");
        }
    }

    public VehicleDTO chanceStatus(Long vehicleId, Users user, VehicleStatusDTO vehicleStatusDTO) {
        if (user.getRole() == Role.ADMIN) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicle == null) {
                throw new CustomExceptions.InvalidIdException("Vehicle with this id not found: " + vehicleId);
            }
            if (vehicleStatusDTO.getVehicleStatus() == VehicleStatus.ORDERABLE
                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.PURCHASABLE
                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.NOT_AVAILABLE) {
                vehicle.setVehicleStatus(vehicleStatusDTO.getVehicleStatus());
                vehicleRepository.save(vehicle);
                return convertToDTO(vehicle);
            } else {
                throw new CustomExceptions.InvalidStatusException("Status not valid: " + vehicleStatusDTO.getVehicleStatus());
            }
        } else {
            throw new CustomExceptions.AccessDeniedException("Only admin can");
        }
    }

    public void deleteVehicle(Long vehicleId, Users user) {
        if (user.getRole() == Role.ADMIN) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicle == null) {
                throw new CustomExceptions.InvalidIdException("Vehicle with this id not found: " + vehicleId);
            } else {
                vehicleRepository.deleteById(vehicleId);
            }
        } else {
            throw new CustomExceptions.AccessDeniedException("Only admin can");
        }
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setDisplacement(vehicleDTO.getDisplacement());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setPower(vehicleDTO.getPower());
        vehicle.setTransmission(vehicleDTO.getTransmission());
        vehicle.setRegistrationYear(vehicleDTO.getRegistrationYear());
        vehicle.setFullType(vehicleDTO.getFullType());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setDiscount(vehicleDTO.getDiscount());
        vehicle.setAccessories(vehicleDTO.getAccessories());
        vehicle.setIsNew(vehicleDTO.getIsNew());
        vehicle.setVehicleStatus(vehicleDTO.getVehicleStatus());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        return vehicle;
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setDisplacement(vehicle.getDisplacement());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setPower(vehicle.getPower());
        vehicleDTO.setTransmission(vehicle.getTransmission());
        vehicleDTO.setRegistrationYear(vehicle.getRegistrationYear());
        vehicleDTO.setFullType(vehicle.getFullType());
        vehicleDTO.setPrice(vehicle.getPrice());
        vehicleDTO.setDiscount(vehicle.getDiscount());
        vehicleDTO.setAccessories(vehicle.getAccessories());
        vehicleDTO.setIsNew(vehicle.getIsNew());
        vehicleDTO.setVehicleStatus(vehicle.getVehicleStatus());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        return vehicleDTO;
    }
}


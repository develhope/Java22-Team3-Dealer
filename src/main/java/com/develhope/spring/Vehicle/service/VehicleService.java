package com.develhope.spring.Vehicle.service;


import com.develhope.spring.User.entity.Role;
import com.develhope.spring.User.entity.UserEntity;
import com.develhope.spring.Vehicle.DTO.VehicleDTO;
import com.develhope.spring.Vehicle.DTO.VehicleStatusDTO;
import com.develhope.spring.Vehicle.entity.VehicleEntity;
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

    public void createVehicle(UserEntity user, VehicleDTO vehicleDTO) {
        if (user.getRole() == Role.ADMIN) {
            VehicleEntity vehicleEntity = convertToEntity(vehicleDTO);
            vehicleRepository.save(vehicleEntity);
        } else {
            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
        }
    }

    public VehicleDTO getVehicleById(Long vehicleId, UserEntity user) {
        if (user.getRole() == Role.CUSTOMER || user.getRole() == Role.SALESMAN) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicleEntity == null) {
                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
            }
            return convertToDTO(vehicleEntity);
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }

    }

    public List<VehicleDTO> getAllVehicle() {
        List<VehicleEntity> vehicleEntityList = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (vehicleEntityList.isEmpty()) {
            throw new IllegalArgumentException("No vehicle found");
        } else {
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
        }
        return vehicleDTOList;
    }

    public List<VehicleDTO> getVehicleByPrice(UserEntity user, BigDecimal minPrice, BigDecimal maxPrice) {//dare valori default in controller
        if (user.getRole() == Role.CUSTOMER) {
            if (minPrice.compareTo(maxPrice) > 0) {
                throw new IllegalArgumentException("The lower price cannot be greater than the higher price");
            }
            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByPrice(minPrice, maxPrice);
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleByColor(UserEntity user, String color) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByColor(color);
            if (vehicleEntityList.isEmpty()) {
                throw new IllegalArgumentException("There are no vehicles in: " + color + " here...");
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleByBrand(UserEntity user, String brand) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByBrand(brand);
            if (vehicleEntityList.isEmpty()) {
                throw new IllegalArgumentException("There are no " + brand + " vehicles here...");
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleByModel(UserEntity user, String model) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByModel(model);
            if (vehicleEntityList.isEmpty()) {
                throw new IllegalArgumentException("There are no " + model + " here....");
            }
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleByType(UserEntity user, VehicleType vehicleType) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            if (vehicleType == VehicleType.CAR
                    || vehicleType == VehicleType.MOTORBIKE
                    || vehicleType == VehicleType.SCOOTER
                    || vehicleType == VehicleType.VAN) {
                List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByType(vehicleType.toString());
                for (VehicleEntity vehicleEntity : vehicleEntityList) {
                    vehicleDTOList.add(convertToDTO(vehicleEntity));
                }
            } else {
                throw new CustomExceptions.InvalidTypeException("UInvalid type: " + vehicleType);
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleByStatus(UserEntity user, VehicleStatus vehicleStatus) {
        if (user.getRole() == Role.CUSTOMER) {

            if (vehicleStatus == VehicleStatus.ORDERABLE
                    || vehicleStatus == VehicleStatus.PURCHASABLE
                    || vehicleStatus == VehicleStatus.NOT_AVAILABLE) {
                List<VehicleDTO> vehicleDTOList = new ArrayList<>();
                List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleStatus(vehicleStatus.toString());
                for (VehicleEntity vehicleEntity : vehicleEntityList) {
                    vehicleDTOList.add(convertToDTO(vehicleEntity));
                }
                return vehicleDTOList;
            } else {
                throw new CustomExceptions.InvalidStatusException("Invalid type: " + vehicleStatus);
            }

        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public List<VehicleDTO> getVehicleNew(UserEntity user, Boolean isNew) {
        if (user.getRole() == Role.CUSTOMER) {
            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleNew(isNew);
            for (VehicleEntity vehicleEntity : vehicleEntityList) {
                vehicleDTOList.add(convertToDTO(vehicleEntity));
            }
            return vehicleDTOList;
        } else {
            throw new CustomExceptions.AccessDeniedException("Access denied!!");
        }
    }

    public void updateVehicle(Long vehicleId, UserEntity user, VehicleDTO updateVehicleDTO) {
        if (user.getRole() == Role.ADMIN) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicleEntity == null) {
                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
            }
            VehicleEntity updateVehicleEntity = convertToEntity(updateVehicleDTO);
            vehicleEntity.setBrand(updateVehicleEntity.getBrand());
            vehicleEntity.setModel(updateVehicleEntity.getModel());
            vehicleEntity.setDisplacement(updateVehicleEntity.getDisplacement());
            vehicleEntity.setColor(updateVehicleEntity.getColor());
            vehicleEntity.setPower(updateVehicleEntity.getPower());
            vehicleEntity.setTransmission(updateVehicleEntity.getTransmission());
            vehicleEntity.setRegistrationYear(updateVehicleEntity.getRegistrationYear());
            vehicleEntity.setFullType(updateVehicleEntity.getFullType());
            vehicleEntity.setPrice(updateVehicleEntity.getPrice());
            vehicleEntity.setDiscount(updateVehicleEntity.getDiscount());
            vehicleEntity.setAccessories(updateVehicleEntity.getAccessories());
            vehicleEntity.setIsNew(updateVehicleEntity.getIsNew());
            vehicleEntity.setVehicleStatus(updateVehicleEntity.getVehicleStatus());
            vehicleEntity.setVehicleType(updateVehicleEntity.getVehicleType());
            vehicleRepository.save(vehicleEntity);
        } else {
            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
        }
    }

    public VehicleDTO chanceStatus(Long vehicleId, UserEntity user, VehicleStatusDTO vehicleStatusDTO) {
        if (user.getRole() == Role.ADMIN) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicleEntity == null) {
                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
            }
            if (vehicleStatusDTO.getVehicleStatus() == VehicleStatus.ORDERABLE
                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.PURCHASABLE
                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.NOT_AVAILABLE) {
                vehicleEntity.setVehicleStatus(vehicleStatusDTO.getVehicleStatus());
                vehicleRepository.save(vehicleEntity);
                return convertToDTO(vehicleEntity);
            } else {
                throw new CustomExceptions.InvalidStatusException("Invalid status: " + vehicleStatusDTO.getVehicleStatus());
            }
        } else {
            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
        }
    }

    public void deleteVehicle(Long vehicleId, UserEntity user) {
        if (user.getRole() == Role.ADMIN) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
            if (vehicleEntity == null) {
                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
            } else {
                vehicleRepository.deleteById(vehicleId);
            }
        } else {
            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
        }
    }

    private VehicleEntity convertToEntity(VehicleDTO vehicleDTO) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setBrand(vehicleDTO.getBrand());
        vehicleEntity.setModel(vehicleDTO.getModel());
        vehicleEntity.setDisplacement(vehicleDTO.getDisplacement());
        vehicleEntity.setColor(vehicleDTO.getColor());
        vehicleEntity.setPower(vehicleDTO.getPower());
        vehicleEntity.setTransmission(vehicleDTO.getTransmission());
        vehicleEntity.setRegistrationYear(vehicleDTO.getRegistrationYear());
        vehicleEntity.setFullType(vehicleDTO.getFullType());
        vehicleEntity.setPrice(vehicleDTO.getPrice());
        vehicleEntity.setDiscount(vehicleDTO.getDiscount());
        vehicleEntity.setAccessories(vehicleDTO.getAccessories());
        vehicleEntity.setIsNew(vehicleDTO.getIsNew());
        vehicleEntity.setVehicleStatus(vehicleDTO.getVehicleStatus());
        vehicleEntity.setVehicleType(vehicleDTO.getVehicleType());
        return vehicleEntity;
    }

    private VehicleDTO convertToDTO(VehicleEntity vehicleEntity) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setBrand(vehicleEntity.getBrand());
        vehicleDTO.setModel(vehicleEntity.getModel());
        vehicleDTO.setDisplacement(vehicleEntity.getDisplacement());
        vehicleDTO.setColor(vehicleEntity.getColor());
        vehicleDTO.setPower(vehicleEntity.getPower());
        vehicleDTO.setTransmission(vehicleEntity.getTransmission());
        vehicleDTO.setRegistrationYear(vehicleEntity.getRegistrationYear());
        vehicleDTO.setFullType(vehicleEntity.getFullType());
        vehicleDTO.setPrice(vehicleEntity.getPrice());
        vehicleDTO.setDiscount(vehicleEntity.getDiscount());
        vehicleDTO.setAccessories(vehicleEntity.getAccessories());
        vehicleDTO.setIsNew(vehicleEntity.getIsNew());
        vehicleDTO.setVehicleStatus(vehicleEntity.getVehicleStatus());
        vehicleDTO.setVehicleType(vehicleEntity.getVehicleType());
        return vehicleDTO;
    }
}


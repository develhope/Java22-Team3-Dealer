package com.develhope.spring.Vehicle.service;

import com.develhope.spring.Vehicle.DTOs.CreateVehicleRequest;
import com.develhope.spring.Vehicle.DTOs.VehicleResponse;
import com.develhope.spring.Vehicle.entity.Vehicle;
import com.develhope.spring.Vehicle.model.VehicleModel;
import com.develhope.spring.Vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//TODO: Optional? creare interfaccia veicolo/user? implementazione autenticazione
@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;

    public VehicleResponse createVehicle(CreateVehicleRequest request){
        VehicleModel model = VehicleModel.dtoToModel(request);
        Vehicle entity = VehicleModel.modelToEntity(model);
        Vehicle savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);

    }
    public boolean deleteVehicleByID(Long vehicleId) {
        Vehicle entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        } else {
            repository.deleteById(vehicleId);
            return true;
        }
    }
    public VehicleResponse findById(Long vehicleId) {
        Vehicle entity = repository.findById(vehicleId).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.entityToModel(entity);
        return VehicleModel.modelToDto(model);
    }

    public VehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest request) {
        Vehicle toUpdate = repository.findById(vehicleId).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No vehicles found for the id: " + vehicleId);
        }
        VehicleModel model = VehicleModel.dtoToModel(request);
        Vehicle entity = VehicleModel.modelToEntity(model);
        Vehicle savedEntity = repository.saveAndFlush(entity);
        VehicleModel savedModel = VehicleModel.entityToModel(savedEntity);
        return VehicleModel.modelToDto(savedModel);
    }
}

//    public List<VehicleDTO> getVehicleByPrice(UserEntity user, BigDecimal minPrice, BigDecimal maxPrice) {//dare valori default in controller
//        if (user.getRole() == Role.CUSTOMER) {
//            if (minPrice.compareTo(maxPrice) > 0) {
//                throw new IllegalArgumentException("The lower price cannot be greater than the higher price");
//            }
//            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByPrice(minPrice, maxPrice);
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                vehicleDTOList.add(convertToDTO(vehicleEntity));
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }

//    public List<VehicleDTO> getVehicleByColor(UserEntity user, String color) {
//        if (user.getRole() == Role.CUSTOMER) {
//            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByColor(color);
//            if (vehicleEntityList.isEmpty()) {
//                throw new IllegalArgumentException("There are no vehicles in: " + color + " here...");
//            }
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                vehicleDTOList.add(convertToDTO(vehicleEntity));
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public List<VehicleDTO> getVehicleByBrand(UserEntity user, String brand) {
//        if (user.getRole() == Role.CUSTOMER) {
//            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByBrand(brand);
//            if (vehicleEntityList.isEmpty()) {
//                throw new IllegalArgumentException("There are no " + brand + " vehicles here...");
//            }
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                vehicleDTOList.add(convertToDTO(vehicleEntity));
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public List<VehicleDTO> getVehicleByModel(UserEntity user, String model) {
//        if (user.getRole() == Role.CUSTOMER) {
//            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByModel(model);
//            if (vehicleEntityList.isEmpty()) {
//                throw new IllegalArgumentException("There are no " + model + " here....");
//            }
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                vehicleDTOList.add(convertToDTO(vehicleEntity));
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public List<VehicleDTO> getVehicleByType(UserEntity user, VehicleType vehicleType) {
//        if (user.getRole() == Role.CUSTOMER) {
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            if (vehicleType == VehicleType.CAR
//                    || vehicleType == VehicleType.MOTORBIKE
//                    || vehicleType == VehicleType.SCOOTER
//                    || vehicleType == VehicleType.VAN) {
//                List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleByType(vehicleType.toString());
//                for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                    vehicleDTOList.add(convertToDTO(vehicleEntity));
//                }
//            } else {
//                throw new CustomExceptions.InvalidTypeException("UInvalid type: " + vehicleType);
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public List<VehicleDTO> getVehicleByStatus(UserEntity user, VehicleStatus vehicleStatus) {
//        if (user.getRole() == Role.CUSTOMER) {
//
//            if (vehicleStatus == VehicleStatus.ORDERABLE
//                    || vehicleStatus == VehicleStatus.PURCHASABLE
//                    || vehicleStatus == VehicleStatus.NOT_AVAILABLE) {
//                List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//                List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleStatus(vehicleStatus.toString());
//                for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                    vehicleDTOList.add(convertToDTO(vehicleEntity));
//                }
//                return vehicleDTOList;
//            } else {
//                throw new CustomExceptions.InvalidStatusException("Invalid type: " + vehicleStatus);
//            }
//
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public List<VehicleDTO> getVehicleNew(UserEntity user, Boolean isNew) {
//        if (user.getRole() == Role.CUSTOMER) {
//            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
//            List<VehicleEntity> vehicleEntityList = vehicleRepository.getAllVehicleNew(isNew);
//            for (VehicleEntity vehicleEntity : vehicleEntityList) {
//                vehicleDTOList.add(convertToDTO(vehicleEntity));
//            }
//            return vehicleDTOList;
//        } else {
//            throw new CustomExceptions.AccessDeniedException("Access denied!!");
//        }
//    }
//
//    public void updateVehicle(Long vehicleId, CreateVehicleRequest request) {
//        if (user.getRole() == Role.ADMIN) {
//            Vehicle vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
//            if (vehicleEntity == null) {
//                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
//            }
//        } else {
//            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
//        }
//    }
//
//    public VehicleDTO chanceStatus(Long vehicleId, UserEntity user, VehicleStatusDTO vehicleStatusDTO) {
//        if (user.getRole() == Role.ADMIN) {
//            VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
//            if (vehicleEntity == null) {
//                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
//            }
//            if (vehicleStatusDTO.getVehicleStatus() == VehicleStatus.ORDERABLE
//                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.PURCHASABLE
//                    || vehicleStatusDTO.getVehicleStatus() == VehicleStatus.NOT_AVAILABLE) {
//                vehicleEntity.setVehicleStatus(vehicleStatusDTO.getVehicleStatus());
//                vehicleRepository.save(vehicleEntity);
//                return convertToDTO(vehicleEntity);
//            } else {
//                throw new CustomExceptions.InvalidStatusException("Invalid status: " + vehicleStatusDTO.getVehicleStatus());
//            }
//        } else {
//            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
//        }
//    }
//
//    public void deleteVehicle(Long vehicleId, User user) {
//        if (user.getRole() == Role.ADMIN) {
//            Vehicle vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
//            if (vehicleEntity == null) {
//                throw new CustomExceptions.InvalidIdException("No vehicles found for the id: " + vehicleId);
//            } else {
//                vehicleRepository.deleteById(vehicleId);
//            }
//        } else {
//            throw new CustomExceptions.AccessDeniedException("This action can be performed only by admins.");
//        }
//    }
//}


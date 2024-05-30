package com.develhope.spring.features.purchase.service;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.errors.VehicleError;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.purchase.repository.LinkUserVehiclePurchaseRepository;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.features.vehicle.DTOs.VehicleRequest;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    LinkUserVehiclePurchaseRepository linkUserVehiclePurchaseRepository;
    @Autowired
    UsersRepository usersRepository;
    Logger logger = LoggerFactory.getLogger(PurchaseService.class);
    private BaseEntityData baseEntityData;

    public Either<GenericErrors, PurchaseResponseDTO> createPurchase(UserModel user, PurchaseRequestDTO request, Long vehicleId) {

        if (user == null) return Either.left(new UserError.UserNotFound());
        if (vehicleId == null) return Either.left(new VehicleError.VehicleNotFound());
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return Either.left(new GenericErrors(434, "This vehicle is empty"));
        if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) ;
        try {
            logger.info("Creation of new purchase started");
            PurchaseModel model = PurchaseModel.dtoToModel(request);
            PurchaseEntity entity = PurchaseModel.modelToEntity(model);
            PurchaseEntity savedEntity = purchaseRepository.saveAndFlush(entity);
            linkUserVehiclePurchaseRepository.save(new LinkPurchaseUserVehicleEntity(UserModel.modelToEntity(user), vehicle.get(), savedEntity));
            PurchaseModel savedModel = PurchaseModel.entityToModel(savedEntity);
            logger.info("Creation of new purchase finished{}", baseEntityData.getCreatedAt());
            return Either.right(PurchaseModel.modelToDTO(savedModel));
        } catch (Exception e) {
            return Either.left(new GenericErrors(435, "Impossible to save " + e.getMessage()));
        }
    }

    public Either<GenericErrors, Boolean> deletePurchaseById(UserModel user, Long purchaseId) {
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<PurchaseEntity> purchaseToDelete = purchaseRepository.findById(purchaseId);
                    if (purchaseToDelete.isPresent()) {
                        logger.info("Purchase deleting started at: {}", baseEntityData.getDeletedAt());
                        purchaseRepository.delete(purchaseToDelete.get());
//                        Optional<LinkPurchaseUserVehicleEntity> link = linkUserVehiclePurchaseRepository.findByPurchase_Id(purchaseId);
//                        link.ifPresent(linkRentUserVehicleEntity -> linkUserVehiclePurchaseRepository.delete(linkRentUserVehicleEntity));
                    }
                }
            } catch (Exception e) {
                return Either.left(new UserError.UserIdNotFoundExc(user.getId(), e));
            }
        }
        logger.info("Deleting process completed at:{}", baseEntityData.getDeletedAt());
        return Either.right(true);
    }

    //this method updates purchase info by checking first if the user has access to this function
    public Either<GenericErrors, PurchaseResponseDTO> updateLinkPurchaseById(UserModel user, Long purchaseId, PurchaseRequestDTO request) {
        PurchaseModel model = null;
        if (user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<PurchaseEntity> purchase = purchaseRepository.findById(purchaseId);
                    if (purchase.isPresent()) {
                        logger.info("The purchase updating process started at:{}", baseEntityData.getUpdatedAt());
                        purchase.get().setPurchaseDeposit(request.getPurchaseDeposit() == null ? purchase.get().getPurchaseDeposit() : request.getPurchaseDeposit());
                        purchase.get().setPurchaseDate(request.getOrderDate() == null ? purchase.get().getPurchaseDate() : request.getOrderDate());
                        purchase.get().setIsPayed(request.getIsPayed() == null ? purchase.get().getIsPayed() : request.getIsPayed());
                        PurchaseEntity entity = purchaseRepository.saveAndFlush(purchase.get());
                        model = PurchaseModel.entityToModel(entity);
                        logger.info("Rental updating process finished at:{}", baseEntityData.getUpdatedAt());
                    }
                }
            } catch (Exception e) {
                return Either.left(new VehicleError.VehicleIdNotFoundExc(user.getId(), e));
            }
        }
        assert model != null;
        return Either.right(PurchaseModel.modelToDTO(model));
    }

    public Either<GenericErrors, LinkPurchaseUserVehicleEntity> updateLinkVehicleById(UserModel user, Long purchaseId, VehicleRequest vehicleRequest) {
        if (purchaseId == null || vehicleRequest == null) return Either.left(new GenericErrors());
        Optional<LinkPurchaseUserVehicleEntity> purchaseLink = linkUserVehiclePurchaseRepository.findByPurchase_Id(purchaseId);
        if (purchaseLink.isEmpty()) return null;
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(user.getId());
        if (vehicleEntity.isEmpty()) return null;

        LinkPurchaseUserVehicleEntity newLink = purchaseLink.get();
        newLink.setVehicleEntity(vehicleEntity.get());
        return Either.right(linkUserVehiclePurchaseRepository.saveAndFlush(newLink));
    }

    public Either<GenericErrors, LinkPurchaseUserVehicleEntity> updateLinkUserById(UserModel user, Long purchaseId) {
        if (purchaseId == null || user == null) return Either.left(new GenericErrors(432, "Not found"));
        Optional<LinkPurchaseUserVehicleEntity> purchaseLink = linkUserVehiclePurchaseRepository.findByPurchase_Id(purchaseId);
        if (purchaseLink.isEmpty())
            return Either.left(new GenericErrors(433, "Impossible to retrieve purchase for the id: " + purchaseId));
        Optional<UserEntity> userEntity = usersRepository.findById(user.getId());
        if (userEntity.isEmpty()) return Either.left(new UserError.UserIsEmpty());

        LinkPurchaseUserVehicleEntity newLink = purchaseLink.get();
        newLink.setUserEntity(userEntity.get());
        return Either.right(linkUserVehiclePurchaseRepository.saveAndFlush(newLink));
    }

    public Either<GenericErrors, PurchaseResponseDTO> getSinglePurchase(UserModel user, Long purchaseId) {
        if (purchaseId == null || user == null) return Either.left(new GenericErrors(432, "Not found"));
        if (user.getRole() == Role.ADMIN)
            try {
                Optional<PurchaseEntity> purchase = purchaseRepository.findById(purchaseId);
                if (purchase.isPresent()) {
                    PurchaseModel model = PurchaseModel.entityToModel(purchase.get());
                    return Either.right(PurchaseModel.modelToDTO(model));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return null;
    }
//
//    public Either<GenericErrors, List<PurchaseResponseDTO>> getAllByUserRole(UserModel user) {
//        if (user != null) {
//            try {
//                if (user.getRole() == Role.ADMIN || user.getRole() == Role.SALESMAN) {
//                    List<PurchaseEntity> purchase = purchaseRepository.findAll();
//                    return extractPurchaseResponse(purchase);
//                } else if(user.getRole() == Role.CUSTOMER) {
//                    List<LinkPurchaseUserVehicleEntity> customerPurchase = linkUserVehiclePurchaseRepository.findByUser_Id(user.getId()).stream().toList();
//                    List<PurchaseEntity> purchases = customerPurchase.stream().map(LinkPurchaseUserVehicleEntity::getPurchaseEntity).toList();
//                    return extractPurchaseResponse(purchases);
//                } else {
//                    return Either.left(new UserError.DefaultUser());
//                }
//            } catch (Exception e) {
//                return Either.left(new GenericErrors(433, "Error during all rentals retrieving"));
//            }
//        }else{
//            return Either.left(new UserError.UserIsEmpty());
//        }
//    }

    private Either<GenericErrors, List<PurchaseResponseDTO>> extractPurchaseResponse(List<PurchaseEntity> purchase) {
        if (purchase.isEmpty()) {
            return Either.left(new GenericErrors(434, "Impossible to retrieve, the list of purchases is empty"));
        }
        return Either.right(purchase.stream()
                .map(PurchaseModel::entityToModel)
                .map(PurchaseModel::modelToDTO)
                .toList());
    }
}

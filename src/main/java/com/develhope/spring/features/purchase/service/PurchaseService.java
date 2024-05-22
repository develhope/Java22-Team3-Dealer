package com.develhope.spring.features.purchase.service;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.purchase.repository.LinkUserVehiclePurchRepository;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.features.vehicle.entity.VehicleEntity;
import com.develhope.spring.features.vehicle.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    LinkUserVehiclePurchRepository linkUserVehiclePurchRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserDetails userDetails;
    @Autowired
    Logger logger = LoggerFactory.getLogger(PurchaseService.class);
    private BaseEntityData baseEntityData;

    public boolean deletePurchaseById(UserEntity userEntity, Long PurchaseId) {
        UserEntity user = (UserEntity) userDetails.getAuthorities();
        if (userEntity != null & user != null) {
            try {
                if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER) {
                    Optional<PurchaseEntity> purchase = purchaseRepository.findById(PurchaseId);
                    if (purchase.isPresent()) {
                        logger.info("Purchase info deleting started");
                        purchaseRepository.delete(purchase.get());
                        Optional<LinkPurchaseUserVehicleEntity> link = linkUserVehiclePurchRepository
                                .findByVehicle_Id(PurchaseId);
                        link.ifPresent(linkPurchaseUserVehicleEntity -> linkUserVehiclePurchRepository
                                .delete(linkPurchaseUserVehicleEntity));
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception caught, no result for your research has been retrieved");
                return false;
            }
        }
        logger.info("Deleting process completed at:{}", baseEntityData.getDeletedAt());
        return true;
    }

    public LinkPurchaseUserVehicleEntity updateLinkPurchaseById(UserModel userModel, Long id, PurchaseRequestDTO request) {
        if (id == null) return null;
        Optional<LinkPurchaseUserVehicleEntity> purchaseUserVehicle = linkUserVehiclePurchRepository.findByPurchase_Id(id);
        if (purchaseUserVehicle.isEmpty()) return null;
        Optional<UserEntity> userEntity = usersRepository.findById(id);
        if (userEntity.isEmpty()) return null;

        LinkPurchaseUserVehicleEntity newLink = purchaseUserVehicle.get();
        newLink.setUserEntity(userEntity.get());
        return linkUserVehiclePurchRepository.saveAndFlush(newLink);
    }

    public PurchaseResponseDTO getSinglePurchase(UserEntity userEntity, Long id) {
        Optional<PurchaseEntity> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            PurchaseModel model = PurchaseModel.entityToModel(purchase.get());
            return PurchaseModel.modelToDTO(model);
        } else {
            return null;
        }
    }

    public List<PurchaseResponseDTO> getAllForUser_id(UserEntity user) {
        List<PurchaseEntity> purchaseEntities = purchaseRepository.findAll();
        if (purchaseEntities.isEmpty()) {
            return null;
        }
        return purchaseEntities.stream()
                .map(PurchaseModel::entityToModel)
                .map(PurchaseModel::modelToDTO)
                .toList();
    }

    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO request, UserEntity userEntity, Long vehicleId) {
        if (userEntity == null) return null;
        if (vehicleId == null) return null;
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isEmpty()) return null;
        UserEntity user = (UserEntity) userDetails.getAuthorities();
        if (user.getRole() == Role.SALESMAN || user.getRole() == Role.ADMIN || user.getRole() == Role.CUSTOMER)
            logger.info("Creation of new purchase started");
        PurchaseModel model = PurchaseModel.dtoToModel(request);
        PurchaseEntity entity = PurchaseModel.modelToEntity(model);
        PurchaseEntity savedEntity = purchaseRepository.saveAndFlush(entity);
        linkUserVehiclePurchRepository.save(new LinkPurchaseUserVehicleEntity(userEntity, vehicle.get(), savedEntity));
        PurchaseModel savedModel = PurchaseModel.entityToModel(savedEntity);
        logger.info("Creation of new rental finished{}", baseEntityData.getCreatedAt());
        return PurchaseModel.modelToDTO(savedModel);
    }
}

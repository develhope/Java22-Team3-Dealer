package com.develhope.spring.features.purchase.service;

import com.develhope.spring.BaseEntityData;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.LinkPurchaseUserVehicleEntity;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.purchase.repository.LinkUserVehiclePurchRepository;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import com.develhope.spring.features.rent.entities.RentEntity;
import com.develhope.spring.features.rent.model.RentModel;
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

    public PurchaseResponseDTO updateLinkPurchaseById(UserModel userModel, Long id, PurchaseRequestDTO request) {
        PurchaseModel model = null;
        if (userModel != null) {
            try {
                if (userModel.getRole() == Role.SALESMAN || userModel.getRole() == Role.ADMIN || userModel.getRole() == Role.CUSTOMER) {
                    Optional<PurchaseEntity> purchase = purchaseRepository.findById(id);
                    if (purchase.isPresent()) {
                        logger.info("Updating purchase process started at:{}", baseEntityData.getUpdatedAt());
                        purchase.get().setPurchaseDate(request.getOrderDate()== null ? purchase.get().getPurchaseDate() : request.getOrderDate());
                        purchase.get().setPurchaseDeposit(request.getPurchaseDeposit()== null ? purchase.get().getPurchaseDeposit() : request.getPurchaseDeposit());
                        purchase.get().setIsPayed(request.getIsPayed() == null ? purchase.get().getIsPayed() : request.getIsPayed());
                        PurchaseEntity entity = purchaseRepository.saveAndFlush(purchase.get());
                        model = PurchaseModel.entityToModel(entity);
                        logger.info("Purchase updating process finished at:{}", baseEntityData.getUpdatedAt());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception in RENT_SERVICE thrown");
            }
        }
        assert model != null;
        return PurchaseModel.modelToDTO(model);
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
=======

    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public ArrayList<PurchaseResponse> getAllPurchase() throws Exception {
        List<PurchaseEntity> purchaseList = purchaseRepository.findAll();
        if (purchaseList.isEmpty()) {
            throw new Exception("No active purchase");
        }

        ArrayList<PurchaseResponse> purchaseResponses = new ArrayList<>();
        for (PurchaseEntity purchase : purchaseList) {
            PurchaseResponse purchaseResponse = PurchaseModel.entityToDto(purchase);
            purchaseResponses.add(purchaseResponse);
        }
        return purchaseResponses;
    }

    public PurchaseResponse createPurchase(PurchaseRequest purchaseRequest) {
        PurchaseEntity purchase = PurchaseModel.dtoToEntity(purchaseRequest);
        PurchaseEntity purchased = purchaseRepository.save(purchase);
        return PurchaseModel.entityToDto(purchased);

    }

    public PurchaseResponse findById(Long purchaseId) {
        PurchaseEntity purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not associated with id: " + purchaseId));
        return PurchaseModel.entityToDto(purchase);
    }
    public PurchaseResponse updatePurchase(Long purchaseId, PurchaseRequest request) {
        PurchaseEntity purchaseToUpdate = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not associated with id:: " + purchaseId));

//        purchaseToUpdate.setDeposit(request.getDeposit());
//        purchaseToUpdate.setPayed(request.isPayed());
//        if (request.getStatus() != null) {
//            purchaseToUpdate.setStatus(OrderStatus.convertStringToStatus(request.getStatus()));
//        }
        PurchaseEntity purchased = purchaseRepository.save(purchaseToUpdate);

        return PurchaseModel.entityToDto(purchased);
    }

    public void deleteOrderById(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}

package com.develhope.spring.features.purchase.service;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.purchase.DTO.PurchaseRequestDTO;
import com.develhope.spring.features.purchase.DTO.PurchaseResponseDTO;
import com.develhope.spring.features.purchase.entity.PurchaseEntity;
import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.model.UserModel;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Either<GenericErrors, PurchaseResponseDTO> createPurchase(UserModel user, PurchaseRequestDTO request) {
        if (user == null) {
            return Either.left(new UserError.UserNotFound());
        }

        // Controlla se l'utente ha il ruolo appropriato per creare un acquisto
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.CUSTOMER) {
            return Either.left(new GenericErrors(403, "Forbidden: Only admin and customer can create purchases"));
        }

        try {
            PurchaseModel model = PurchaseModel.dtoToModel(request);
            PurchaseEntity entity = PurchaseModel.modelToEntity(model);
            PurchaseEntity savedEntity = purchaseRepository.saveAndFlush(entity);
            PurchaseModel savedModel = PurchaseModel.entityToModel(savedEntity);
            return Either.right(PurchaseModel.modelToDTO(savedModel));
        } catch (Exception e) {
            return Either.left(new GenericErrors(435, "Impossible to save: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, Boolean> deletePurchaseById(UserModel user, Long purchaseId) {
        if (user == null) {
            return Either.left(new UserError.UserNotFound());
        }

        // Controlla se l'utente ha il ruolo appropriato per eliminare un acquisto
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.CUSTOMER) {
            return Either.left(new GenericErrors(403, "Forbidden: Only admin and customer can delete purchases"));
        }

        if (purchaseId == null) {
            return Either.left(new GenericErrors(400, "Invalid purchase ID"));
        }

        try {
            Optional<PurchaseEntity> purchase = purchaseRepository.findById(purchaseId);
            if (purchase.isPresent()) {
                purchaseRepository.delete(purchase.get());
                return Either.right(true);
            } else {
                return Either.left(new GenericErrors(404, "Purchase not found"));
            }
        } catch (Exception e) {
            return Either.left(new GenericErrors(500, "Error deleting purchase: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, PurchaseResponseDTO> updatePurchaseById(UserModel user, Long purchaseId, PurchaseRequestDTO request) {
        if (user == null) {
            return Either.left(new UserError.UserNotFound());
        }

        // Controlla se l'utente ha il ruolo appropriato per aggiornare un acquisto
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.CUSTOMER) {
            return Either.left(new GenericErrors(403, "Forbidden: Only admin and customer can update purchases"));
        }

        if (purchaseId == null) {
            return Either.left(new GenericErrors(400, "Invalid purchase ID"));
        }

        try {
            Optional<PurchaseEntity> purchaseOptional = purchaseRepository.findById(purchaseId);
            if (purchaseOptional.isPresent()) {
                PurchaseEntity purchase = purchaseOptional.get();
                purchase.setPurchaseDeposit(request.getPurchaseDeposit());
                purchase.setIsPayed(request.getIsPayed());
                purchase.setPurchaseDate(request.getPurchaseDate());
                PurchaseEntity updatedPurchase = purchaseRepository.save(purchase);
                PurchaseModel updatedModel = PurchaseModel.entityToModel(updatedPurchase);
                return Either.right(PurchaseModel.modelToDTO(updatedModel));
            } else {
                return Either.left(new GenericErrors(404, "Purchase not found"));
            }
        } catch (Exception e) {
            return Either.left(new GenericErrors(500, "Error updating purchase: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, PurchaseResponseDTO> getPurchaseById(UserModel user, Long purchaseId) {
        if (user == null) {
            return Either.left(new UserError.UserNotFound());
        }

        // Controlla se l'utente ha il ruolo appropriato per ottenere un acquisto
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.CUSTOMER) {
            return Either.left(new GenericErrors(403, "Forbidden: Only admin and customer can get purchases"));
        }

        if (purchaseId == null) {
            return Either.left(new GenericErrors(400, "Invalid purchase ID"));
        }

        try {
            Optional<PurchaseEntity> purchaseOptional = purchaseRepository.findById(purchaseId);
            if (purchaseOptional.isPresent()) {
                PurchaseModel model = PurchaseModel.entityToModel(purchaseOptional.get());
                return Either.right(PurchaseModel.modelToDTO(model));
            } else {
                return Either.left(new GenericErrors(404, "Purchase not found"));
            }
        } catch (Exception e) {
            return Either.left(new GenericErrors(500, "Error retrieving purchase: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, List<PurchaseResponseDTO>> getAllPurchases(UserModel user) {
        if (user == null) {
            return Either.left(new UserError.UserNotFound());
        }

        // Controlla se l'utente ha il ruolo appropriato per ottenere tutti gli acquisti
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.CUSTOMER) {
            return Either.left(new GenericErrors(403, "Forbidden: Only admin and customer can get purchases"));
        }

        try {
            List<PurchaseEntity> purchases = purchaseRepository.findAll();
            List<PurchaseModel> models = purchases.stream()
                    .map(PurchaseModel::entityToModel)
                    .collect(Collectors.toList());
            List<PurchaseResponseDTO> dtos = models.stream()
                    .map(PurchaseModel::modelToDTO)
                    .collect(Collectors.toList());
            return Either.right(dtos);
        } catch (Exception e) {
            return Either.left(new GenericErrors(500, "Error retrieving purchases: " + e.getMessage()));
        }
    }
}




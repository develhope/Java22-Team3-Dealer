package com.develhope.spring.features.purchase.service;


import com.develhope.spring.features.orders.entity.OrderStatus;

import com.develhope.spring.features.purchase.DTO.PurchaseRequest;
import com.develhope.spring.features.purchase.DTO.PurchaseResponse;
import com.develhope.spring.features.purchase.entity.Purchase;

import com.develhope.spring.features.purchase.model.PurchaseModel;
import com.develhope.spring.features.purchase.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public ArrayList<PurchaseResponse> getAllPurchase() throws Exception {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        if (purchaseList.isEmpty()) {
            throw new Exception("No active purchase");
        }

        ArrayList<PurchaseResponse> purchaseResponses = new ArrayList<>();
        for (Purchase purchase : purchaseList) {
            PurchaseResponse purchaseResponse = PurchaseModel.entityToDto(purchase);
            purchaseResponses.add(purchaseResponse);
        }
        return purchaseResponses;
    }

    public PurchaseResponse createPurchase(PurchaseRequest purchaseRequest) {
        Purchase purchase = PurchaseModel.dtoToEntity(purchaseRequest);
        Purchase purchased = purchaseRepository.save(purchase);
        return PurchaseModel.entityToDto(purchased);

    }

    public PurchaseResponse findById(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not associated with id: " + purchaseId));
        return PurchaseModel.entityToDto(purchase);
    }
    public PurchaseResponse updatePurchase(Long purchaseId, PurchaseRequest request) {
        Purchase purchaseToUpdate = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase not associated with id:: " + purchaseId));

        purchaseToUpdate.setDeposit(request.getDeposit());
        purchaseToUpdate.setPayed(request.isPayed());
        if (request.getStatus() != null) {
            purchaseToUpdate.setStatus(OrderStatus.convertStringToStatus(request.getStatus()));
        }
        Purchase purchased = purchaseRepository.save(purchaseToUpdate);

        return PurchaseModel.entityToDto(purchased);
    }

    public void deleteOrderById(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}

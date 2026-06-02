package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.restaurant.FreshStoreRequest;
import com.foodwagon.backend.entity.FreshStore;
import com.foodwagon.backend.repository.FreshStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreshStoreService {

    @Autowired
    private FreshStoreRepository freshStoreRepository;

    public FreshStore createStore(FreshStoreRequest request) {
        FreshStore store = new FreshStore();
        store.setName(request.getName());
        store.setImage(request.getImage());
        store.setRating(request.getRating());
        store.setTime(request.getTime());
        store.setDistance(request.getDistance());
        store.setItems(request.getItems() != null ? request.getItems() : new ArrayList<>());

        return freshStoreRepository.save(store);
    }

    public List<FreshStore> getAllStores() {
        return freshStoreRepository.findAll();
    }

    public FreshStore getStoreById(Long id) {
        return freshStoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public FreshStore updateStore(Long id, FreshStoreRequest request) {
        FreshStore store = getStoreById(id);
        store.setName(request.getName());
        store.setImage(request.getImage());
        store.setRating(request.getRating());
        store.setTime(request.getTime());
        store.setDistance(request.getDistance());
        store.setItems(request.getItems());

        return freshStoreRepository.save(store);
    }

    public List<FreshStore> getTopRatedStores() {
        return freshStoreRepository.getTopRatedStores();
    }

    public void deleteStore(Long id) {
        freshStoreRepository.deleteById(id);
    }
}
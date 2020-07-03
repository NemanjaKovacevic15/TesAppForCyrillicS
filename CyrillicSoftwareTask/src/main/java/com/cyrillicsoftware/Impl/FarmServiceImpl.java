package com.cyrillicsoftware.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyrillicsoftware.model.Farm;
import com.cyrillicsoftware.repository.FarmRepository;
import com.cyrillicsoftware.service.FarmService;

@Service
@Transactional
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public Farm findOne(Long id) {
        return farmRepository.findById(id).orElse(null);
    }

    @Override
    public List<Farm> findAll() {
        return farmRepository.findAll();
    }

    @Override
    public Farm save(Farm toSave) {
        return farmRepository.save(toSave);
    }

    @Override
    public Farm delete(Long id) {
        Farm toDelete = farmRepository.getOne(id);
        farmRepository.delete(toDelete);
        return toDelete;
    }

    @Override
    public List<Farm> findByAccountId(Long id) {
        return farmRepository.findByAccountId(id);
    }

}

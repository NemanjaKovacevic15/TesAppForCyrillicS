package com.cyrillicsoftware.service;

import java.util.List;

import com.cyrillicsoftware.model.Farm;

public interface FarmService {

    Farm findOne(Long id);

    List<Farm> findAll();

    Farm save(Farm toSave);

    Farm delete(Long id);

    List<Farm> findByAccountId(Long id);

}

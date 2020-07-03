package com.cyrillicsoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyrillicsoftware.model.Farm;
import com.cyrillicsoftware.service.FarmService;
import com.cyrillicsoftware.converter.FarmDtoToFarm;
import com.cyrillicsoftware.converter.FarmToFarmDTO;
import com.cyrillicsoftware.dto.FarmDTO;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;
    @Autowired
    private FarmToFarmDTO toDto;
    @Autowired
    private FarmDtoToFarm toUser;

    // Get All Farms
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<FarmDTO>> getAll() {

        List<Farm> farms = farmService.findAll();

        return new ResponseEntity<>(toDto.convert(farms), HttpStatus.OK);
    }

    // Get one Farm
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<FarmDTO> getOne(@PathVariable Long id) {

        Farm farm = farmService.findOne(id);
        if (farm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(farm), HttpStatus.OK);
    }

    // Add Farm
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<FarmDTO> add(@RequestBody FarmDTO toSave) {

        Farm saved = farmService.save(toUser.convert(toSave));

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }

    // Delete Farm
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<FarmDTO> delete(@PathVariable Long id) {

        Farm deleted = farmService.delete(id);

        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
    }

    // Edit Farm
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<FarmDTO> edit(@PathVariable Long id, @RequestBody FarmDTO toEdit) {

        if (!Objects.equals(id, toEdit.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Farm edited = farmService.save(toUser.convert(toEdit));

        return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
    }
}

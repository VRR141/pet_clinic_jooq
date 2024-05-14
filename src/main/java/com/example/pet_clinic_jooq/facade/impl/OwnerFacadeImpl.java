package com.example.pet_clinic_jooq.facade.impl;

import com.example.pet_clinic_jooq.domain.PlainOwner;
import com.example.pet_clinic_jooq.dto.OwnerDto;
import com.example.pet_clinic_jooq.dto.request.CreateOwnerDto;
import com.example.pet_clinic_jooq.dto.request.UpdateOwnerDto;
import com.example.pet_clinic_jooq.facade.OwnerFacade;
import com.example.pet_clinic_jooq.mapper.OwnerMapper;
import com.example.pet_clinic_jooq.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerFacadeImpl implements OwnerFacade {

    private final OwnerService ownerService;

    private final OwnerMapper mapper;

    @Override
    public OwnerDto getOwner(Long id, boolean fetchPets) {
        final PlainOwner plainOwner;
        final OwnerDto ownerDto;
        if (fetchPets) {
            plainOwner = ownerService.getOwnerByIdWithRelatedPets(id);
            ownerDto = mapper.map(plainOwner);
        } else {
            plainOwner = ownerService.getOwnerById(id);
            ownerDto = mapper.mapIgnorePets(plainOwner);
        }
        return ownerDto;
    }

    @Override
    public Long createOwner(CreateOwnerDto dto) {
        var owner = mapper.map(dto);
        owner = ownerService.createOwner(owner);
        return owner.getId();
    }

    @Override
    public OwnerDto updateOwner(UpdateOwnerDto dto) {
        var owner = mapper.map(dto);
        owner = ownerService.updateOwner(owner);
        return mapper.mapIgnorePets(owner);
    }
}

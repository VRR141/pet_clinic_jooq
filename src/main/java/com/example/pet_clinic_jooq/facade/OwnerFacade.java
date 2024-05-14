package com.example.pet_clinic_jooq.facade;

import com.example.pet_clinic_jooq.dto.OwnerDto;
import com.example.pet_clinic_jooq.dto.request.CreateOwnerDto;
import com.example.pet_clinic_jooq.dto.request.UpdateOwnerDto;

public interface OwnerFacade {

    OwnerDto getOwner(Long id, boolean fetchPets);

    Long createOwner(CreateOwnerDto dto);

    OwnerDto updateOwner(UpdateOwnerDto dto);
}

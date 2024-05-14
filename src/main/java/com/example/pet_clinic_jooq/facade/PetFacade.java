package com.example.pet_clinic_jooq.facade;

import com.example.pet_clinic_jooq.dto.PetDto;
import com.example.pet_clinic_jooq.dto.request.CreatePetDto;
import com.example.pet_clinic_jooq.dto.request.UpdatePetDto;

import java.util.Collection;

public interface PetFacade {

    PetDto getPet(Long id);

    Long createPet(CreatePetDto dto);

    PetDto updatePet(UpdatePetDto dto);

    Collection<PetDto> getPets(Collection<Long> ids);
}

package com.example.pet_clinic_jooq.facade;

import com.example.pet_clinic_jooq.dto.VisitDto;
import com.example.pet_clinic_jooq.dto.request.CreateVisitDto;

import java.util.Collection;

public interface VisitFacade {

    Collection<VisitDto> getByPetIdentifier(Long id);

    Long createVisit(CreateVisitDto dto);
}

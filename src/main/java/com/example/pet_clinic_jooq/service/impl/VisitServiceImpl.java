package com.example.pet_clinic_jooq.service.impl;

import com.example.pet_clinic_jooq.domain.PlainVisit;
import com.example.pet_clinic_jooq.repo.VisitRepository;
import com.example.pet_clinic_jooq.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Collection<PlainVisit> getVisitsByPetIdentifier(Long id) {
        return repository.getVisitByPetIdentifier(id);
    }

    @Override
    @Transactional
    public PlainVisit createVisit(PlainVisit plainVisit) {
        return repository.save(plainVisit);
    }
}

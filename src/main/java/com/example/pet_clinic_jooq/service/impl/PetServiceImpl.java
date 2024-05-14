package com.example.pet_clinic_jooq.service.impl;

import com.example.pet_clinic_jooq.domain.PlainPet;
import com.example.pet_clinic_jooq.exception.EntityNotExistException;
import com.example.pet_clinic_jooq.repo.PetRepository;
import com.example.pet_clinic_jooq.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Override
    @Transactional(readOnly = true)
    public PlainPet getById(Long id) {
        return repository.findByIdWithFetchedVisits(id).orElseThrow(() -> new EntityNotExistException(PlainPet.class, id));
    }

    @Override
    @Transactional
    public PlainPet createPet(PlainPet plainPet) {
        return repository.save(plainPet);
    }

    @Override
    @Transactional
    public PlainPet updatePet(PlainPet plainPet) {
        var persisted = repository.findById(plainPet.getId()).orElseThrow(() -> new EntityNotExistException(PlainPet.class, plainPet.getId()));

        if (!Objects.equals(persisted.getName(), plainPet.getName()) && Objects.nonNull(plainPet.getName())) {
            persisted.setName(plainPet.getName());
        }

        if (!Objects.equals(persisted.getBirthDate(), plainPet.getBirthDate()) && Objects.nonNull(plainPet.getBirthDate())) {
            persisted.setBirthDate(plainPet.getBirthDate());
        }

        return repository.update(persisted);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PlainPet> getPetsByIdentifiers(Collection<Long> ids) {
        return repository.getByIdentifiers(ids);
    }
}

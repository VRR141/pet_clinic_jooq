package com.example.pet_clinic_jooq.service.impl;

import com.example.pet_clinic_jooq.domain.PlainOwner;
import com.example.pet_clinic_jooq.exception.EntityNotExistException;
import com.example.pet_clinic_jooq.repo.OwnerRepository;
import com.example.pet_clinic_jooq.repo.PetRepository;
import com.example.pet_clinic_jooq.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;
    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public PlainOwner getOwnerById(Long id) {
        return repository.getOwnerById(id).orElseThrow(() -> new EntityNotExistException(PlainOwner.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public PlainOwner getOwnerByIdWithRelatedPets(Long id) {
        return repository.getOwnerByIdWithRelatedPets(id).orElseThrow(() -> new EntityNotExistException(PlainOwner.class, id));
    }

    @Override
    @Transactional
    public PlainOwner createOwner(PlainOwner plainOwner) {
        var persistedOwner = repository.save(plainOwner);
        if (Objects.nonNull(plainOwner.getPets()) && !plainOwner.getPets().isEmpty()) {
            var persistedPets = petRepository.saveAll(plainOwner.getPets());
            persistedOwner.setPets(persistedPets);
        }
        return persistedOwner;
    }

    @Override
    @Transactional
    public PlainOwner updateOwner(PlainOwner plainOwner) {
        var persistedOwner = repository
                .getOwnerById(plainOwner.getId())
                .orElseThrow(() -> new EntityNotExistException(PlainOwner.class, plainOwner.getId()));

        if (!Objects.equals(plainOwner.getSurname(), persistedOwner.getSurname()) && Objects.nonNull(plainOwner.getSurname())) {
            persistedOwner.setSurname(plainOwner.getSurname());
        }

        if (!Objects.equals(plainOwner.getName(), persistedOwner.getName()) && Objects.nonNull(plainOwner.getName())) {
            persistedOwner.setSurname(plainOwner.getName());
        }

        if (!Objects.equals(plainOwner.getAddress(), persistedOwner.getAddress()) && Objects.nonNull(plainOwner.getAddress())) {
            persistedOwner.setSurname(plainOwner.getAddress());
        }

        if (!Objects.equals(plainOwner.getMobilePhone(), persistedOwner.getMobilePhone()) && Objects.nonNull(plainOwner.getMobilePhone())) {
            persistedOwner.setSurname(plainOwner.getMobilePhone());
        }

        return repository.update(persistedOwner);
    }
}

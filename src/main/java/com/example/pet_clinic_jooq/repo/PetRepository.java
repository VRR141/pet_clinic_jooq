package com.example.pet_clinic_jooq.repo;


import com.example.pet_clinic_jooq.domain.PlainPet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Optional<PlainPet> findByIdWithFetchedVisits(Long id);

    Optional<PlainPet> findById(Long id);

    PlainPet save(PlainPet plainPet);

    List<PlainPet> saveAll(Collection<PlainPet> pets);

    PlainPet update(PlainPet plainPet);

    Collection<PlainPet> getByIdentifiers(Collection<Long> identifiers);
}

package com.example.pet_clinic_jooq.service;


import com.example.pet_clinic_jooq.domain.PlainPet;

import java.util.Collection;

public interface PetService {

    PlainPet getById(Long id);

    PlainPet createPet(PlainPet plainPet);

    PlainPet updatePet(PlainPet plainPet);

    Collection<PlainPet> getPetsByIdentifiers(Collection<Long> ids);
}

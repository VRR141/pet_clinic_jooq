package com.example.pet_clinic_jooq.service;


import com.example.pet_clinic_jooq.domain.PlainPet;

public interface PetService {

    PlainPet getById(Long id);

    PlainPet createPet(PlainPet plainPet);

    PlainPet updatePet(PlainPet plainPet);
}

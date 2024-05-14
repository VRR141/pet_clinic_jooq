package com.example.pet_clinic_jooq.service;


import com.example.pet_clinic_jooq.domain.PlainOwner;

public interface OwnerService {

    PlainOwner getOwnerById(Long id);

    PlainOwner getOwnerByIdWithRelatedPets(Long id);

    PlainOwner createOwner(PlainOwner plainOwner);

    PlainOwner updateOwner(PlainOwner plainOwner);
}

package com.example.pet_clinic_jooq.repo;

import com.example.pet_clinic_jooq.domain.PlainOwner;

import java.util.Optional;

public interface OwnerRepository {

    //    @Query("""
//            FROM Owner owner
//            WHERE owner.id = :id
//            """)
    Optional<PlainOwner> getOwnerById(Long id);

    //    @Query("""
//            FROM Owner owner
//            LEFT JOIN FETCH owner.pets
//            WHERE owner.id = :id
//            """)
    Optional<PlainOwner> getOwnerByIdWithRelatedPets(Long id);

    PlainOwner save(PlainOwner plainOwner);

    PlainOwner update(PlainOwner plainOwner);
}

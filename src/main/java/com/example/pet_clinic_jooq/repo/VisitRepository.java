package com.example.pet_clinic_jooq.repo;


import com.example.pet_clinic_jooq.domain.PlainVisit;

import java.util.List;

public interface VisitRepository {

    //    @Query("""
//                FROM Visit visit
//                WHERE visit.pet.id = :id
//            """)
    List<PlainVisit> getVisitByPetIdentifier(Long id);

    PlainVisit save(PlainVisit plainVisit);
}

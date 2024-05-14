package com.example.pet_clinic_jooq.service;


import com.example.pet_clinic_jooq.domain.PlainVisit;

import java.util.Collection;

public interface VisitService {

    Collection<PlainVisit> getVisitsByPetIdentifier(Long id);

    PlainVisit createVisit(PlainVisit plainVisit);
}

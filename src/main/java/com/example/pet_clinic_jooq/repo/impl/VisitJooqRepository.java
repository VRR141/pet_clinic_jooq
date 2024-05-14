package com.example.pet_clinic_jooq.repo.impl;

import com.example.pet_clinic_jooq.domain.PlainVisit;
import com.example.pet_clinic_jooq.jooq.Tables;
import com.example.pet_clinic_jooq.jooq.tables.Visit;
import com.example.pet_clinic_jooq.jooq.tables.records.VisitRecord;
import com.example.pet_clinic_jooq.repo.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitJooqRepository implements VisitRepository {

    private final DSLContext dslContext;

    @Override
    public List<PlainVisit> getVisitByPetIdentifier(Long id) {
        return dslContext.select()
                .from(Tables.VISIT)
                .where(Visit.VISIT.PET_ID.eq(id))
                .fetchStreamInto(Tables.VISIT)
                .map(this::map)
                .toList();
    }

    @Override
    @Transactional
    public PlainVisit save(PlainVisit plainVisit) {
        var visit = dslContext.insertInto(Tables.VISIT)
                .set(Visit.VISIT.VISIT_TIMESTAMP, plainVisit.getVisitTimestamp())
                .set(Visit.VISIT.PET_ID, plainVisit.getPet().getId())
                .set(Visit.VISIT.DESCRIPTION, plainVisit.getDescription())
                .returning(Visit.VISIT.ID)
                .fetchOne();
        var visitId = visit.getId();
        plainVisit.setId(visitId);
        return plainVisit;

    }

    private PlainVisit map(VisitRecord record) {
        return PlainVisit.builder()
                .id(record.getId())
                .description(record.getDescription())
                .visitTimestamp(record.getVisitTimestamp())
                .build();
    }
}

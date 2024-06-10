package com.example.pet_clinic_jooq.repo.impl;

import com.example.pet_clinic_jooq.domain.PlainOwner;
import com.example.pet_clinic_jooq.domain.PlainPet;
import com.example.pet_clinic_jooq.domain.PlainVisit;
import com.example.pet_clinic_jooq.jooq.Tables;
import com.example.pet_clinic_jooq.jooq.tables.Pet;
import com.example.pet_clinic_jooq.jooq.tables.Visit;
import com.example.pet_clinic_jooq.jooq.tables.records.PetRecord;
import com.example.pet_clinic_jooq.repo.PetRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetJooqRepository implements PetRepository {

    private final DSLContext dslContext;

    @Override
    public Optional<PlainPet> findByIdWithFetchedVisits(Long id) {
        var map = dslContext
                .select()
                .from(Tables.PET)
                .leftJoin(Tables.VISIT)
                .on(Pet.PET.ID.eq(Visit.VISIT.PET_ID))
                .where(Pet.PET.ID.eq(id))
                .fetchGroups(Pet.PET);

        if (map.isEmpty()) {
            return Optional.empty();
        }

        final var pet = new PlainPet();

        map.forEach((k, v) -> {
            pet.setId(k.getId());
            pet.setName(k.getName());
            pet.setBirthDate(pet.getBirthDate());
            var visits = v.stream()
                    .map(el -> PlainVisit.builder()
                            .id(el.get(Visit.VISIT.ID))
                            .visitTimestamp(el.get(Visit.VISIT.VISIT_TIMESTAMP))
                            .description(el.get(Visit.VISIT.DESCRIPTION))
                            .build())
                    .toList();
            pet.setVisits(visits);
        });

        return Optional.of(pet);
    }

    @Override
    public Optional<PlainPet> findById(Long id) {
        var petRecord = dslContext
                .select()
                .from(Tables.PET)
                .where(Pet.PET.ID.eq(id))
                .fetchOneInto(PetRecord.class);
        return Optional.ofNullable(map(petRecord));
    }

    @Override
    @Transactional
    public PlainPet save(PlainPet entity) {
        var record = map(entity);
        var pet = dslContext.insertInto(Tables.PET)
                .set(record)
                .returning(Pet.PET.ID)
                .fetchOne();
        var id = pet.getId();
        entity.setId(id);
        return entity;
    }

    @Override
    @Transactional
    public List<PlainPet> saveAll(Collection<PlainPet> pets) {
        return pets.stream().map(this::save).toList();
    }

    @Override
    @Transactional
    public PlainPet update(PlainPet plainPet) {
        var petRecord = dslContext.update(Tables.PET)
                .set(map(plainPet))
                .where(Pet.PET.ID.eq(plainPet.getId()))
                .returning()
                .fetchOneInto(PetRecord.class);
        return map(petRecord);
    }

    @Override
    public Collection<PlainPet> getByIdentifiers(Collection<Long> identifiers) {
        var records = dslContext.select()
                .from(Tables.PET)
                .where(Pet.PET.ID.in(identifiers))
                .fetchInto(PetRecord.class);
        return records.stream().map(this::map).toList();
    }

    private PetRecord map(PlainPet pet) {
        return new PetRecord(
                pet.getId(),
                pet.getName(),
                pet.getBirthDate(),
                pet.getOwner().getId(),
                pet.getPayload());
    }

    private PlainPet map(PetRecord record) {
        if (Objects.isNull(record)) {
            return null;
        }
        return PlainPet.builder()
                .id(record.getId())
                .birthDate(record.getBirthDate())
                .name(record.getName())
                .owner(PlainOwner.builder()
                        .id(record.getOwnerId())
                        .build())
                .payload(record.getPayload())
                .build();
    }
}

package com.example.pet_clinic_jooq.repo.impl;

import com.example.pet_clinic_jooq.domain.PlainOwner;
import com.example.pet_clinic_jooq.domain.PlainPet;
import com.example.pet_clinic_jooq.jooq.Tables;
import com.example.pet_clinic_jooq.jooq.tables.Owner;
import com.example.pet_clinic_jooq.jooq.tables.Pet;
import com.example.pet_clinic_jooq.jooq.tables.records.OwnerRecord;
import com.example.pet_clinic_jooq.repo.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerJooqRepository implements OwnerRepository {

    private final DSLContext dslContext;

    @Override
    public Optional<PlainOwner> getOwnerById(Long id) {
        var record = dslContext
                .select()
                .from(Tables.OWNER)
                .where(Owner.OWNER.ID.eq(id))
                .fetchOneInto(OwnerRecord.class);
        return Optional.ofNullable(map(record));
    }

    @Override
    public Optional<PlainOwner> getOwnerByIdWithRelatedPets(Long id) {
        var map = dslContext
                .select()
                .from(Tables.OWNER)
                .leftJoin(Tables.PET)
                .on(Owner.OWNER.ID.eq(Pet.PET.OWNER_ID))
                .where(Owner.OWNER.ID.eq(id))
                .fetchGroups(Owner.OWNER);

        if (map.isEmpty()) {
            return Optional.empty();
        }

        final var owner = new PlainOwner();

        map.forEach((k, v) -> {
            owner.setId(k.getId());
            owner.setName(k.getName());
            owner.setSurname(k.getSurname());
            owner.setAddress(k.getAddress());
            owner.setMobilePhone(k.getMobilePhone());
            var pets = v.stream()
                    .map(el -> PlainPet.builder()
                            .id(el.get(Pet.PET.ID))
                            .name(el.get(Pet.PET.NAME))
                            .birthDate(el.get(Pet.PET.BIRTH_DATE))
                            .build())
                    .toList();
            owner.setPets(pets);
        });

        return Optional.of(owner);
    }

    @Override
    @Transactional
    public PlainOwner save(PlainOwner entity) {
        var ownerRecord = map(entity);

        var record = dslContext.insertInto(Tables.OWNER)
                .set(ownerRecord)
                .returning(Owner.OWNER.ID)
                .fetchOne();

        var id = record.getId();
        entity.setId(id);
        return entity;
    }

    @Override
    @Transactional
    public PlainOwner update(PlainOwner plainOwner) {
        var ownerRecord = dslContext.update(Tables.OWNER)
                .set(map(plainOwner))
                .where(Owner.OWNER.ID.eq(plainOwner.getId()))
                .returning()
                .fetchOneInto(OwnerRecord.class);
        return map(ownerRecord);
    }

    private OwnerRecord map(PlainOwner entity) {
        return new OwnerRecord(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getAddress(),
                entity.getMobilePhone(),
                entity.getPayload());
    }

    private PlainOwner map(OwnerRecord rec) {
        if (Objects.isNull(rec)) {
            return null;
        }
        return PlainOwner.builder()
                .id(rec.getId())
                .name(rec.getName())
                .surname(rec.getSurname())
                .address(rec.getAddress())
                .mobilePhone(rec.getMobilePhone())
                .payload(rec.getPayload())
                .build();
    }
}

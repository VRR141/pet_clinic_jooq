package com.example.pet_clinic_jooq.mapper;

import com.example.pet_clinic_jooq.domain.PlainPet;
import com.example.pet_clinic_jooq.dto.PetDto;
import com.example.pet_clinic_jooq.dto.request.CreatePetDto;
import com.example.pet_clinic_jooq.dto.request.UpdatePetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = VisitMapper.class)
public interface PetMapper {

    @Mappings({
            @Mapping(target = "visits", source = "visits")
    })
    PetDto map(PlainPet entity);

    @Mappings({
            @Mapping(target = "visits", ignore = true)
    })
    PetDto mapIgnoreVisits(PlainPet entity);

    @Mappings({
            @Mapping(target = "owner.id", source = "ownerId"),
    })
    PlainPet map(CreatePetDto dto);

    PlainPet map(UpdatePetDto dto);
}

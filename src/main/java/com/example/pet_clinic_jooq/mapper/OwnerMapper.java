package com.example.pet_clinic_jooq.mapper;

import com.example.pet_clinic_jooq.domain.PlainOwner;
import com.example.pet_clinic_jooq.domain.PlainPet;
import com.example.pet_clinic_jooq.dto.OwnerDto;
import com.example.pet_clinic_jooq.dto.PetDto;
import com.example.pet_clinic_jooq.dto.request.CreateOwnerDto;
import com.example.pet_clinic_jooq.dto.request.UpdateOwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDto map(PlainOwner entity);

    @Mappings({
            @Mapping(target = "pets", ignore = true)
    })
    OwnerDto mapIgnorePets(PlainOwner entity);

    PlainOwner map(CreateOwnerDto dto);

    PlainOwner map(UpdateOwnerDto dto);

    @Mappings({
            @Mapping(target = "visits", ignore = true)
    })
    PetDto map(PlainPet plainPet);
}

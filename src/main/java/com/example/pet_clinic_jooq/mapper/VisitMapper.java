package com.example.pet_clinic_jooq.mapper;

import com.example.pet_clinic_jooq.domain.PlainVisit;
import com.example.pet_clinic_jooq.dto.VisitDto;
import com.example.pet_clinic_jooq.dto.request.CreateVisitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VisitMapper {

    @Mappings({
            @Mapping(target = "timestamp", source = "visitTimestamp"),
            @Mapping(target = "petId", source = "pet.id")
    })
    VisitDto map(PlainVisit dto);

    @Mappings({
            @Mapping(target = "pet.id", source = "petIdentifier"),
            @Mapping(target = "visitTimestamp", source = "visitDate")
    })
    PlainVisit map(CreateVisitDto dto);
}

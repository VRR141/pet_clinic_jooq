package com.example.pet_clinic_jooq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlainVisit {
    private Long id;
    private OffsetDateTime visitTimestamp;
    private String description;
    private PlainPet pet;
    private byte[] payload;
}

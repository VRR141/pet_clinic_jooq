package com.example.pet_clinic_jooq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlainPet {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private PlainOwner owner;
    private List<PlainVisit> visits;
    private byte[] payload;
}

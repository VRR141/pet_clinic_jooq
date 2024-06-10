package com.example.pet_clinic_jooq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlainOwner {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String mobilePhone;
    private List<PlainPet> pets;
    private byte[] payload;
}

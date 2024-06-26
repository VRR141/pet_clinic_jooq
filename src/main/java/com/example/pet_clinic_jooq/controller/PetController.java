package com.example.pet_clinic_jooq.controller;

import com.example.pet_clinic_jooq.dto.PetDto;
import com.example.pet_clinic_jooq.dto.request.CreatePetDto;
import com.example.pet_clinic_jooq.dto.request.UpdatePetDto;
import com.example.pet_clinic_jooq.facade.PetFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private final PetFacade petFacade;

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPet(@PathVariable("id") Long id) {
        log.debug("Start get pet {}", id);
        final var output = petFacade.getPet(id);
        log.debug("Get pet {}", output);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<Collection<PetDto>> getPets(@RequestParam Collection<Long> ids){
        log.debug("Start get pet {}", ids);
        if (ids.isEmpty()){
            return ResponseEntity.ok(null);
        }
        final var output = petFacade.getPets(ids);
        log.debug("Get pet {}", output);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<Long> createPet(@RequestBody CreatePetDto dto) {
        log.debug("Start create pet {}", dto);
        final var output = petFacade.createPet(dto);
        log.debug("Create pet {}", output);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @PatchMapping
    public ResponseEntity<PetDto> updatePet(@RequestBody UpdatePetDto dto) {
        log.debug("Start update pet {}", dto);
        final var output = petFacade.updatePet(dto);
        log.debug("Update pet pet {}", output);
        return ResponseEntity.ok(output);
    }
}

package com.example.themagicwand.controller;

import com.example.themagicwand.dto.MagicProviderRequestDTO;
import com.example.themagicwand.dto.MagicProviderResponseDTO;
import com.example.themagicwand.service.MagicProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class MagicProviderController {

    private final MagicProviderService providerService;

    @PostMapping
    public ResponseEntity<MagicProviderResponseDTO> create(@Valid @RequestBody MagicProviderRequestDTO dto) {
        MagicProviderResponseDTO created = providerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagicProviderResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagicProviderResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MagicProviderRequestDTO dto) {
        return ResponseEntity.ok(providerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        providerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

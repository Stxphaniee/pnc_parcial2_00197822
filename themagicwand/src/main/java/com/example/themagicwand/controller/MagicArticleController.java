package com.example.themagicwand.controller;

import com.example.themagicwand.dto.MagicArticleRequestDTO;
import com.example.themagicwand.dto.MagicArticleResponseDTO;
import com.example.themagicwand.enums.ArticleType;
import com.example.themagicwand.service.MagicArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/artefactos")
@RequiredArgsConstructor
public class MagicArticleController {

    private final MagicArticleService articleService;

    @PostMapping
    public ResponseEntity<MagicArticleResponseDTO> create(@Valid @RequestBody MagicArticleRequestDTO dto) {
        MagicArticleResponseDTO created = articleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<MagicArticleResponseDTO>> findAll(
            @RequestParam(required = false) ArticleType category,
            @RequestParam(required = false) Long provider,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(articleService.findAll(category, provider, maxPrice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagicArticleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagicArticleResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MagicArticleRequestDTO dto) {
        return ResponseEntity.ok(articleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.themagicwand.mapper;

import com.example.themagicwand.dto.MagicProviderRequestDTO;
import com.example.themagicwand.dto.MagicProviderResponseDTO;
import com.example.themagicwand.entity.MagicProvider;
import org.springframework.stereotype.Component;

@Component
public class MagicProviderMapper {

    public MagicProvider toEntity(MagicProviderRequestDTO dto) {
        return MagicProvider.builder()
                .name(dto.getName())
                .type(dto.getType())
                .build();
    }

    public MagicProviderResponseDTO toResponseDTO(MagicProvider entity) {
        return MagicProviderResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }

    public void updateEntityFromDTO(MagicProviderRequestDTO dto, MagicProvider entity) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
    }
}

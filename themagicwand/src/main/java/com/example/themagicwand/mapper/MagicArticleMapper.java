package com.example.themagicwand.mapper;

import com.example.themagicwand.dto.MagicArticleRequestDTO;
import com.example.themagicwand.dto.MagicArticleResponseDTO;
import com.example.themagicwand.entity.MagicArticle;
import com.example.themagicwand.entity.MagicProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MagicArticleMapper {

    private final MagicProviderMapper providerMapper;

    public MagicArticle toEntity(MagicArticleRequestDTO dto, MagicProvider provider) {
        return MagicArticle.builder()
                .name(dto.getName())
                .type(dto.getType())
                .price(dto.getPrice())
                .provider(provider)
                .build();
    }

    public MagicArticleResponseDTO toResponseDTO(MagicArticle entity) {
        return MagicArticleResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .price(entity.getPrice())
                .provider(providerMapper.toResponseDTO(entity.getProvider()))
                .build();
    }

    public void updateEntityFromDTO(MagicArticleRequestDTO dto, MagicArticle entity, MagicProvider provider) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setPrice(dto.getPrice());
        entity.setProvider(provider);
    }
}

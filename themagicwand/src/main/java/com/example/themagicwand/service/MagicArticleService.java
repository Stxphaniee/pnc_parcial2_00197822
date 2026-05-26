package com.example.themagicwand.service;

import com.example.themagicwand.dto.MagicArticleRequestDTO;
import com.example.themagicwand.dto.MagicArticleResponseDTO;
import com.example.themagicwand.entity.MagicArticle;
import com.example.themagicwand.entity.MagicProvider;
import com.example.themagicwand.enums.ArticleType;
import com.example.themagicwand.exception.BusinessRuleException;
import com.example.themagicwand.exception.ConflictException;
import com.example.themagicwand.exception.EntityNotFoundException;
import com.example.themagicwand.mapper.MagicArticleMapper;
import com.example.themagicwand.repository.MagicArticleRepository;
import com.example.themagicwand.repository.MagicArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MagicArticleService {

    private final MagicArticleRepository articleRepository;
    private final MagicProviderService providerService;
    private final MagicArticleMapper articleMapper;

    @Transactional
    public MagicArticleResponseDTO create(MagicArticleRequestDTO dto) {
        if (articleRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new ConflictException("An article with name '" + dto.getName() + "' already exists");
        }

        MagicProvider provider = providerService.getProviderOrThrow(dto.getProviderId());
        validateProviderType(dto.getType(), provider);

        MagicArticle article = articleMapper.toEntity(dto, provider);
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Transactional(readOnly = true)
    public List<MagicArticleResponseDTO> findAll(ArticleType category, Long providerId, BigDecimal maxPrice) {
        return articleRepository.findAll(
                MagicArticleSpecification.withFilters(category, providerId, maxPrice)
        ).stream()
                .map(articleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MagicArticleResponseDTO findById(Long id) {
        MagicArticle article = getArticleOrThrow(id);
        return articleMapper.toResponseDTO(article);
    }

    @Transactional
    public MagicArticleResponseDTO update(Long id, MagicArticleRequestDTO dto) {
        MagicArticle article = getArticleOrThrow(id);

        if (!article.getName().equalsIgnoreCase(dto.getName()) &&
                articleRepository.existsByNameIgnoreCaseAndIdNot(dto.getName(), id)) {
            throw new ConflictException("An article with name '" + dto.getName() + "' already exists");
        }

        MagicProvider provider = providerService.getProviderOrThrow(dto.getProviderId());
        validateProviderType(dto.getType(), provider);

        articleMapper.updateEntityFromDTO(dto, article, provider);
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Transactional
    public void delete(Long id) {
        MagicArticle article = getArticleOrThrow(id);
        articleRepository.delete(article);
    }

    private MagicArticle getArticleOrThrow(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article with id " + id + " not found"));
    }

    private void validateProviderType(ArticleType articleType, MagicProvider provider) {
        if (!provider.getType().equals(articleType)) {
            throw new BusinessRuleException(
                    "Provider '" + provider.getName() + "' can only supply articles of type " +
                    provider.getType() + ", but the article type is " + articleType
            );
        }
    }
}

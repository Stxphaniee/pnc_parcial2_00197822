package com.example.themagicwand.service;

import com.example.themagicwand.dto.MagicProviderRequestDTO;
import com.example.themagicwand.dto.MagicProviderResponseDTO;
import com.example.themagicwand.entity.MagicProvider;
import com.example.themagicwand.exception.ConflictException;
import com.example.themagicwand.exception.EntityNotFoundException;
import com.example.themagicwand.mapper.MagicProviderMapper;
import com.example.themagicwand.repository.MagicArticleRepository;
import com.example.themagicwand.repository.MagicProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MagicProviderService {

    private final MagicProviderRepository providerRepository;
    private final MagicArticleRepository articleRepository;
    private final MagicProviderMapper providerMapper;

    @Transactional
    public MagicProviderResponseDTO create(MagicProviderRequestDTO dto) {
        if (providerRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new ConflictException("A provider with name '" + dto.getName() + "' already exists");
        }
        MagicProvider provider = providerMapper.toEntity(dto);
        return providerMapper.toResponseDTO(providerRepository.save(provider));
    }

    @Transactional(readOnly = true)
    public MagicProviderResponseDTO findById(Long id) {
        MagicProvider provider = getProviderOrThrow(id);
        return providerMapper.toResponseDTO(provider);
    }

    @Transactional
    public MagicProviderResponseDTO update(Long id, MagicProviderRequestDTO dto) {
        MagicProvider provider = getProviderOrThrow(id);


        if (!provider.getName().equalsIgnoreCase(dto.getName()) &&
                providerRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new ConflictException("A provider with name '" + dto.getName() + "' already exists");
        }

        providerMapper.updateEntityFromDTO(dto, provider);
        return providerMapper.toResponseDTO(providerRepository.save(provider));
    }

    @Transactional
    public void delete(Long id) {
        MagicProvider provider = getProviderOrThrow(id);

        if (articleRepository.existsByProviderId(id)) {
            throw new ConflictException("Cannot delete provider '" + provider.getName() +
                    "' because it has associated articles. Remove or reassign them first");
        }

        providerRepository.delete(provider);
    }

    public MagicProvider getProviderOrThrow(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider with id " + id + " not found"));
    }
}

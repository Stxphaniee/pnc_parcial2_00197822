package com.example.themagicwand.dto;

import com.example.themagicwand.enums.ArticleType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicArticleResponseDTO {
    private Long id;
    private String name;
    private ArticleType type;
    private BigDecimal price;
    private MagicProviderResponseDTO provider;
}

package com.example.themagicwand.dto;

import com.example.themagicwand.enums.ArticleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicProviderResponseDTO {
    private Long id;
    private String name;
    private ArticleType type;
}

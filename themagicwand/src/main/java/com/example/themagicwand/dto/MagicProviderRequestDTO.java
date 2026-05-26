package com.example.themagicwand.dto;

import com.example.themagicwand.enums.ArticleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MagicProviderRequestDTO {

    @NotBlank(message = "Nombre de proveedor es requerido")
    private String name;

    @NotNull(message = "Tipo de proveedor es requerido")
    private ArticleType type;
}

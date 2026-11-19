package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Astronauta {

    private Long id;

    @NotBlank (message = "O nome é obrigatório.")
    private String nome;

    @NotBlank (message = "O país é obrigatório." )
    private String pais;

    @NotNull (message = "A idade é obrigatória.")
    @Min(value = 18, message = "A idade mínima é 18 anos.")
    private Integer idade;

    @NotNull (message = "Anos de experiência são obrigatórios.")
    private Integer anosExperiencia;

    @NotNull (message = "O status é obrigatório.")
    private StatusAstronauta status;

}

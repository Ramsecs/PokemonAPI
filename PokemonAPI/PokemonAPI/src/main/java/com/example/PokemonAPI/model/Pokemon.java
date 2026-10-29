package com.example.PokemonAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //La notacion NOTBLANK nos ayuda a que el atributo no entre vacio
    @NotBlank(message = "El nombre del pokemon es requerido")
    //La notacion SIZE nos ayuda a establecer el tamaño de los limites que ponemos dentro del atributo
    @Size(min = 2, max = 100, message = "El nombre debe de tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tripo de pokemon es obligatorio")
    private String tipo;

    //La notacion NOTNULL nos ayuda a que el atributo no tenga un valor de null
    @NotNull(message = "El nivel del pokemon es requerido")
    @Min(value = 0, message = "El nivel no puede ser negativo")
    @Max(value = 100, message = "El nivel no puede ser superior a 100")
    private Integer nivel;

    @NotNull(message = "El defensa del pokemon es requerido")
    @Min(value = 0, message = "La defensa no puede ser negativa")
    private Integer defensa;

    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer ataque;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(max = 255, message = "La descripcion no puede exceder los 255 caracteres")
    private String descripcion;

    @URL(message = "El formato de la imagen debe ser una URL")
    private String imagenURL;

    @Email(message = "El formato del correo no es valido")
    private String correoEntrenador;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDate fechaNacimiento;

    @PastOrPresent(message = "La fecha de captura no puede ser futura")
    private LocalDate fechaCaptura;

    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    @Pattern(regexp = "^\\d{10}$", message = "El formato del telefono no es valido")
    private String telefono;

    @AssertTrue(message = "El prokemon debe estar sano")
    private Boolean estaSano;

}

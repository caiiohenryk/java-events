package com.eqsunipe.events.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data @RequiredArgsConstructor
public class EventCreateDTO {
    @NotBlank(message = "O nome não deve estar vazio ou nulo.")
    @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres.")
    private String name;

    @NotNull(message = "A data do evento não pode ser nula.")
    @Future(message = "A data do evento deve ser uma data futura.")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

    @NotBlank(message = "O local do evento não deve estar vazio ou nulo.")
    @Size(min = 5, message = "O local do evento deve ter no mínimo 5 caracteres.")
    private String local;

    @NotBlank(message = "A cidade do evento não deve estar vazio ou nulo.")
    @Size(min = 5, message = "A cidade do evento deve ter no mínimo 5 caracteres.")
    private String city;

    @NotBlank(message = "A cidade do evento não deve estar vazio ou nulo.")
    private String uf;
}

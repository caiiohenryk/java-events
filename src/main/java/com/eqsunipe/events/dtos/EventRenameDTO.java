package com.eqsunipe.events.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EventRenameDTO {
  @NotBlank(message = "O nome não deve estar vazio ou nulo.")
  @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres.")
  private String name;
}

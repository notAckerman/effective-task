package org.example.effectivetask.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "Status of the error response", example = "error")
    private String status;

    @Schema(description = "Detailed error message", example = "Invalid username or password")
    private String message;
}

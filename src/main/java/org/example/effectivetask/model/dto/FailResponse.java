package org.example.effectivetask.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailResponse<T> {

    @Schema(description = "Status of the response", example = "fail")
    private String status;

    @Schema(description = "The data or error details of the response")
    private T data;
}

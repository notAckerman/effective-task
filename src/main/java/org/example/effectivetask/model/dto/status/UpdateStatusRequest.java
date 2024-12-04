package org.example.effectivetask.model.dto.status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusRequest {

    @NotNull(message = "Status cannot be null")
    @Schema(description = "The status to update the task to", example = "2")
    private Long status;
}

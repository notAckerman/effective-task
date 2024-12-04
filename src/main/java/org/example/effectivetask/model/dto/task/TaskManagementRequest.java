package org.example.effectivetask.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagementRequest extends TaskRequest {

    @NotNull(message = "Status cannot be null")
    @Schema(description = "The status of the task", example = "1")
    private Long status;

    @Schema(description = "The ID of the user assigned to the task", example = "100")
    private Long assignee;
}

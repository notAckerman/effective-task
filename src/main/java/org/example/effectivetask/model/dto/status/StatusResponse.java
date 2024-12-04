package org.example.effectivetask.model.dto.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.effectivetask.model.enums.TaskStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {

    @Schema(description = "Unique identifier of the status", example = "1")
    private Long id;

    @Schema(description = "The status of the task", example = "IN_PROGRESS")
    private TaskStatus status;
}

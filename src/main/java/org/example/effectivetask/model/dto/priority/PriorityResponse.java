package org.example.effectivetask.model.dto.priority;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.effectivetask.model.enums.TaskPriority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityResponse {

    @Schema(description = "Unique identifier of the priority", example = "1")
    private Long id;

    @Schema(description = "The priority of the task", example = "HIGH")
    private TaskPriority priority;
}

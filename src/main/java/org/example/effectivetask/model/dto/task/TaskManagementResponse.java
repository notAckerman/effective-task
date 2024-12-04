package org.example.effectivetask.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagementResponse extends TaskResponse {

    @Schema(description = "Timestamp when the task was created", example = "2024-12-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the task was last updated", example = "2024-12-01T12:00:00")
    private LocalDateTime updatedAt;
}

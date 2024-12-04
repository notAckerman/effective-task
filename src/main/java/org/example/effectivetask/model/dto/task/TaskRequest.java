package org.example.effectivetask.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.effectivetask.model.entity.Priority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @NotNull(message = "Priority cannot be null")
    @Schema(description = "Priority of the task", example = "1")
    private Long priority;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Schema(description = "Description of the task", example = "This task involves fixing bugs in the system.", minLength = 10, maxLength = 500)
    private String description;
}

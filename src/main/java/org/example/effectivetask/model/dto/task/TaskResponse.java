package org.example.effectivetask.model.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.effectivetask.model.dto.priority.PriorityResponse;
import org.example.effectivetask.model.dto.status.StatusResponse;
import org.example.effectivetask.model.dto.user.UserResponse;
import org.example.effectivetask.model.entity.Priority;
import org.example.effectivetask.model.entity.Status;
import org.example.effectivetask.model.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    @Schema(description = "Unique identifier of the task", example = "123")
    private Long id;

    @Schema(description = "Description of the task", example = "Fix bugs in the system")
    private String description;

    @Schema(description = "Priority of the task", implementation = PriorityResponse.class)
    private PriorityResponse priority;

    @Schema(description = "Status of the task", implementation = StatusResponse.class)
    private StatusResponse status;

    @Schema(description = "Author of the task", implementation = UserResponse.class)
    private UserResponse author;

    @Schema(description = "Assignee of the task", implementation = UserResponse.class)
    private UserResponse assignee;
}

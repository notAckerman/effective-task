package org.example.effectivetask.model.dto.comment;

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
public class CommentManagementResponse extends CommentResponse {

    @Schema(description = "Timestamp when the comment was created", example = "2024-12-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the comment was last updated", example = "2024-12-01T12:00:00")
    private LocalDateTime updatedAt;
}

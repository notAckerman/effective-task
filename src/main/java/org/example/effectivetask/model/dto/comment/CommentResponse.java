package org.example.effectivetask.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.effectivetask.model.dto.user.UserResponse;
import org.example.effectivetask.model.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    @Schema(description = "Unique identifier of the comment", example = "1")
    private Long id;

    @Schema(description = "The content of the comment", example = "This is a comment.")
    private String text;

    @Schema(description = "The author of the comment", implementation = UserResponse.class)
    private UserResponse author;
}

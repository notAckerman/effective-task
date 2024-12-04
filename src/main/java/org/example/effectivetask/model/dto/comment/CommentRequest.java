package org.example.effectivetask.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "Text cannot be blank")
    @Size(min = 1, max = 500, message = "Text must be between 1 and 500 characters")
    @Schema(description = "The content of the comment", example = "This is a comment.", minLength = 1, maxLength = 500)
    private String text;
}

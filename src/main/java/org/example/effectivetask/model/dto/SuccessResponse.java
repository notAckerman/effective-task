package org.example.effectivetask.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.effectivetask.model.entity.Task;
import org.springframework.web.bind.annotation.GetMapping;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {

    @Schema(description = "Status of the response", example = "success")
    private String status;

    @Schema(description = "The data returned by the request")
    private T data;
}
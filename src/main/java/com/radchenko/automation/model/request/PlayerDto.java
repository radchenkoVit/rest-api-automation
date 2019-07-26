package com.radchenko.automation.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDto {
    private Long id;
    private String fullName;
    private String position;
    private String teamName;
}

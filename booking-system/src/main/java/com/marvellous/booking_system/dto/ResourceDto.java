package com.marvellous.booking_system.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ResourceDto {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Integer capacity;
    private Boolean active;
}


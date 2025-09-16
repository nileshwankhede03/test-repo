package com.marvellous.booking_system.dto;

import com.marvellous.booking_system.entity.ReservationStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationDto 
{
    private Long id;
    private Long resourceId;
    private Long userId;
    private ReservationStatus status;
    private BigDecimal price;
    private Instant startTime;
    private Instant endTime;
    private Instant createdAt;
    private Instant updatedAt;
}


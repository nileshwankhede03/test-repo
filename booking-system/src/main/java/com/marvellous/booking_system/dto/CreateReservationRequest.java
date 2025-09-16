package com.marvellous.booking_system.dto;

import com.marvellous.booking_system.entity.ReservationStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateReservationRequest {
    @NotNull
    private Long resourceId;

    // userId will be ignored for ROLE_USER (derived from JWT) but optional for admin
    private Long userId;

    @NotNull
    private ReservationStatus status;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    private Instant startTime;

    @NotNull
    private Instant endTime;
}

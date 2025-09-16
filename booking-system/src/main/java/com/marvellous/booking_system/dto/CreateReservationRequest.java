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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public CreateReservationRequest(@NotNull Long resourceId, Long userId, @NotNull ReservationStatus status,
			@NotNull @DecimalMin("0.0") BigDecimal price, @NotNull Instant startTime, @NotNull Instant endTime) {
		super();
		this.resourceId = resourceId;
		this.userId = userId;
		this.status = status;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public CreateReservationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CreateReservationRequest [resourceId=" + resourceId + ", userId=" + userId + ", status=" + status
				+ ", price=" + price + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
    
}

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
    
	public ReservationDto(Long id, Long resourceId, Long userId, ReservationStatus status, BigDecimal price,
			Instant startTime, Instant endTime, Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.resourceId = resourceId;
		this.userId = userId;
		this.status = status;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public Instant getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "ReservationDto [id=" + id + ", resourceId=" + resourceId + ", userId=" + userId + ", status=" + status
				+ ", price=" + price + ", startTime=" + startTime + ", endTime=" + endTime + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

    
    
}


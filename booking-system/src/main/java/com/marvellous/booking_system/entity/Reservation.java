package com.marvellous.booking_system.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "reservations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    // Use Instant for times
    private Instant startTime;
    private Instant endTime;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResourceEntity getResource() {
		return resource;
	}

	public void setResource(ResourceEntity resource) {
		this.resource = resource;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
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
		return "Reservation [id=" + id + ", resource=" + resource + ", user=" + user + ", status=" + status + ", price="
				+ price + ", startTime=" + startTime + ", endTime=" + endTime + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id, ResourceEntity resource, AppUser user, ReservationStatus status, BigDecimal price,
			Instant startTime, Instant endTime, Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.resource = resource;
		this.user = user;
		this.status = status;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    
    
}


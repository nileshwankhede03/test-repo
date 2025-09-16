package com.marvellous.booking_system.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resources")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ResourceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String type;
    private String description;
    private Integer capacity;
    private boolean active = true;
	public ResourceEntity(Long id, String name, String type, String description, Integer capacity, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.capacity = capacity;
		this.active = active;
	}
	public ResourceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "ResourceEntity [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description
				+ ", capacity=" + capacity + ", active=" + active + "]";
	}
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}


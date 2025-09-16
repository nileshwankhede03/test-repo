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
    
    
	public ResourceDto(Long id, String name, String type, String description, Integer capacity, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.capacity = capacity;
		this.active = active;
	}
	
	
	public ResourceDto() {
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "ResourceDto [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description
				+ ", capacity=" + capacity + ", active=" + active + "]";
	}
    
    
}


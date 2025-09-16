package com.marvellous.booking_system.repository;



import com.marvellous.booking_system.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> 
{
	
}


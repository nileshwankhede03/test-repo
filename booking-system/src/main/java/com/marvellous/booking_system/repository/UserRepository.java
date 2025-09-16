package com.marvellous.booking_system.repository;


import com.marvellous.booking_system.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> 
{
    Optional<AppUser> findByUsername(String username);
}


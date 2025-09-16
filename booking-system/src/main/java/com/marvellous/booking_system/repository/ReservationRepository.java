package com.marvellous.booking_system.repository;

import com.marvellous.booking_system.entity.Reservation;
import com.marvellous.booking_system.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> 
{

    // check overlapping CONFIRMED reservations for same resource
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.resource.id = :resourceId AND r.status = :status AND r.startTime < :end AND r.endTime > :start")
    boolean existsOverlap(@Param("resourceId") Long resourceId,
                          @Param("status") ReservationStatus status,
                          @Param("start") Instant start,
                          @Param("end") Instant end);
}


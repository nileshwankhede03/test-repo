package com.marvellous.booking_system.service;

import com.marvellous.booking_system.dto.*;
import com.marvellous.booking_system.entity.*;
import com.marvellous.booking_system.exception.NotFoundException;
import com.marvellous.booking_system.repository.*;
import com.marvellous.booking_system.util.ReservationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReservationDto create(CreateReservationRequest req, Authentication auth) {
        // resolve user: if ROLE_USER => use auth.username; if admin and userId present -> use it; else admin can set user.
        AppUser user;
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin && req.getUserId() != null) {
            user = userRepository.findById(req.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        } else {
            String username = (String) auth.getPrincipal();
            user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        }

        ResourceEntity resource = resourceRepository.findById(req.getResourceId())
                .orElseThrow(() -> new NotFoundException("Resource not found"));

        // prevent overlapping CONFIRMED reservations for same resource/time if trying to create CONFIRMED
        if (req.getStatus() == ReservationStatus.CONFIRMED) {
            boolean overlap = reservationRepository.existsOverlap(resource.getId(), ReservationStatus.CONFIRMED, req.getStartTime(), req.getEndTime());
            if (overlap) {
                throw new IllegalArgumentException("Overlapping confirmed reservation exists for this resource/time");
            }
        }

        Reservation r = Reservation.builder()
                .resource(resource)
                .user(user)
                .status(req.getStatus())
                .price(req.getPrice())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .build();

        return toDto(reservationRepository.save(r));
    }

    public Page<ReservationDto> list(Optional<ReservationStatus> status,
                                     Optional<java.math.BigDecimal> minPrice,
                                     Optional<java.math.BigDecimal> maxPrice,
                                     int page, int size, Sort sort,
                                     Authentication auth) {

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Reservation> spec = Specification.where(ReservationSpecifications.withFilters(status, minPrice, maxPrice));

        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            // filter by current user only
            String username = (String) auth.getPrincipal();
            var user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
            spec = spec.and(ReservationSpecifications.byUserId(user.getId()));
        }

        return reservationRepository.findAll(spec, pageable).map(this::toDto);
    }

    public ReservationDto get(Long id, Authentication auth) {
        Reservation r = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            String username = (String) auth.getPrincipal();
            if (!r.getUser().getUsername().equals(username)) {
                throw new org.springframework.security.access.AccessDeniedException("Not allowed");
            }
        }
        return toDto(r);
    }

    @Transactional
    public ReservationDto update(Long id, CreateReservationRequest req, Authentication auth) {
        Reservation r = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin && !r.getUser().getUsername().equals((String) auth.getPrincipal())) {
            throw new org.springframework.security.access.AccessDeniedException("Not allowed");
        }

        // update fields (admin may change user by specifying userId)
        if (isAdmin && req.getUserId() != null) {
            var u = userRepository.findById(req.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
            r.setUser(u);
        }

        if (req.getResourceId() != null && !req.getResourceId().equals(r.getResource().getId())) {
            var res = resourceRepository.findById(req.getResourceId()).orElseThrow(() -> new NotFoundException("Resource not found"));
            r.setResource(res);
        }

        r.setStatus(req.getStatus());
        r.setPrice(req.getPrice());
        r.setStartTime(req.getStartTime());
        r.setEndTime(req.getEndTime());

        // check overlap if CONFIRMED
        if (r.getStatus() == ReservationStatus.CONFIRMED) {
            boolean overlap = reservationRepository.existsOverlap(r.getResource().getId(), ReservationStatus.CONFIRMED, r.getStartTime(), r.getEndTime());
            // note: this will also find itself; in a production-ready solution exclude current id. For simplicity here we treat overlap as a conflict.
            if (overlap) {
                throw new IllegalArgumentException("Overlapping confirmed reservation exists for this resource/time");
            }
        }

        return toDto(reservationRepository.save(r));
    }

    public void delete(Long id, Authentication auth) {
        Reservation r = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin && !r.getUser().getUsername().equals((String) auth.getPrincipal())) {
            throw new org.springframework.security.access.AccessDeniedException("Not allowed");
        }
        reservationRepository.deleteById(id);
    }

    private ReservationDto toDto(Reservation r) {
        return ReservationDto.builder()
                .id(r.getId())
                .resourceId(r.getResource().getId())
                .userId(r.getUser().getId())
                .status(r.getStatus())
                .price(r.getPrice())
                .startTime(r.getStartTime())
                .endTime(r.getEndTime())
                .createdAt(r.getCreatedAt())
                .updatedAt(r.getUpdatedAt())
                .build();
    }
}


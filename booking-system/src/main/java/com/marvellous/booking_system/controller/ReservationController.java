package com.marvellous.booking_system.controller;

import com.marvellous.booking_system.dto.*;
import com.marvellous.booking_system.entity.ReservationStatus;
import com.marvellous.booking_system.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam Optional<ReservationStatus> status,
            @RequestParam Optional<BigDecimal> minPrice,
            @RequestParam Optional<BigDecimal> maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort,
            Authentication auth
    ) {
        Sort sortObj = Sort.by("createdAt").descending();
        if (sort != null && !sort.isBlank()) {
            String[] s = sort.split(",");
            sortObj = Sort.by("desc".equalsIgnoreCase(s.length > 1 ? s[1] : "asc") ? Sort.Direction.DESC : Sort.Direction.ASC, s[0]);
        }
        return ResponseEntity.ok(service.list(status, minPrice, maxPrice, page, size, sortObj, auth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> get(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(service.get(id, auth));
    }

    @PostMapping
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody CreateReservationRequest req, Authentication auth) {
        return ResponseEntity.ok(service.create(req, auth));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> update(@PathVariable Long id, @Valid @RequestBody CreateReservationRequest req, Authentication auth) {
        return ResponseEntity.ok(service.update(id, req, auth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        service.delete(id, auth);
        return ResponseEntity.noContent().build();
    }
}


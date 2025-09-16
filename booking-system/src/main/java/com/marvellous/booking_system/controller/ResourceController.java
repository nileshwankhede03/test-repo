package com.marvellous.booking_system.controller;

import com.marvellous.booking_system.dto.ResourceDto;
import com.marvellous.booking_system.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService service = null;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort // e.g. createdAt,desc
    ) {
        Sort sortObj = Sort.unsorted();
        if (sort != null && !sort.isBlank()) {
            String[] s = sort.split(",");
            sortObj = Sort.by("desc".equalsIgnoreCase(s.length > 1 ? s[1] : "asc") ? Sort.Direction.DESC : Sort.Direction.ASC, s[0]);
        }
        return ResponseEntity.ok(service.list(page, size, sortObj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResourceDto> create(@Valid @RequestBody ResourceDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResourceDto> update(@PathVariable Long id, @Valid @RequestBody ResourceDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


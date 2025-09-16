package com.marvellous.booking_system.service;

import com.marvellous.booking_system.dto.ResourceDto;
import com.marvellous.booking_system.entity.ResourceEntity;
import com.marvellous.booking_system.exception.NotFoundException;
import com.marvellous.booking_system.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final ResourceRepository repo = null;

    public Page<ResourceDto> list(int page, int size, Sort sort) {
        Pageable p = PageRequest.of(page, size, sort);
        return repo.findAll(p).map(this::toDto);
    }

    public ResourceDto get(Long id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    public ResourceDto create(ResourceDto dto) {
        ResourceEntity e = ResourceEntity.builder()
                .name(dto.getName())
                .type(dto.getType())
                .description(dto.getDescription())
                .capacity(dto.getCapacity())
                .active(dto.getActive() == null ? true : dto.getActive())
                .build();
        return toDto(repo.save(e));
    }

    public ResourceDto update(Long id, ResourceDto dto) {
        ResourceEntity e = repo.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
        e.setName(dto.getName());
        e.setType(dto.getType());
        e.setDescription(dto.getDescription());
        e.setCapacity(dto.getCapacity());
        e.setActive(dto.getActive() == null ? e.isActive() : dto.getActive());
        return toDto(repo.save(e));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Resource not found");
        repo.deleteById(id);
    }

    private ResourceDto toDto(ResourceEntity e) {
        return ResourceDto.builder()
                .id(e.getId())
                .name(e.getName())
                .type(e.getType())
                .description(e.getDescription())
                .capacity(e.getCapacity())
                .active(e.isActive())
                .build();
    }
}

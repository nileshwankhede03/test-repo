package com.marvellous.booking_system.util;

import com.marvellous.booking_system.entity.Reservation;
import com.marvellous.booking_system.entity.ReservationStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ReservationSpecifications {

    public static Specification<Reservation> withFilters(java.util.Optional<ReservationStatus> status,
                                                         java.util.Optional<BigDecimal> minPrice,
                                                         java.util.Optional<BigDecimal> maxPrice) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            status.ifPresent(s -> predicates.getExpressions().add(cb.equal(root.get("status"), s)));
            minPrice.ifPresent(min -> predicates.getExpressions().add(cb.greaterThanOrEqualTo(root.get("price"), min)));
            maxPrice.ifPresent(max -> predicates.getExpressions().add(cb.lessThanOrEqualTo(root.get("price"), max)));
            return predicates;
        };
    }

    public static Specification<Reservation> byUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }
}


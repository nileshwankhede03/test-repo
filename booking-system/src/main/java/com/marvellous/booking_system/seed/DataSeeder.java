package com.marvellous.booking_system.seed;

import com.marvellous.booking_system.entity.*;
import com.marvellous.booking_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner 
{
    private final UserRepository userRepository = null;
    private final ResourceRepository resourceRepository = null;
    private final ReservationRepository reservationRepository = null;
    private final PasswordEncoder passwordEncoder = null;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            var admin = AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .roles(Set.of(Role.ROLE_ADMIN))
                    .build();

            var user = AppUser.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .enabled(true)
                    .roles(Set.of(Role.ROLE_USER))
                    .build();

            userRepository.save(admin);
            userRepository.save(user);

            var r1 = ResourceEntity.builder().name("Conference Room A").type("Room").description("Large meeting room").capacity(10).active(true).build();
            var r2 = ResourceEntity.builder().name("Projector").type("Equipment").description("HD projector").capacity(1).active(true).build();
            resourceRepository.save(r1);
            resourceRepository.save(r2);

            // seed one reservation for user
            reservationRepository.save(Reservation.builder()
                    .resource(r1)
                    .user(user)
                    .status(ReservationStatus.PENDING)
                    .price(new BigDecimal("100.00"))
                    .startTime(Instant.now().plusSeconds(3600))
                    .endTime(Instant.now().plusSeconds(7200))
                    .build()
            );

            System.out.println("Seeded admin/admin123 and user/user123 and sample resources/reservation");
        }
    }
}


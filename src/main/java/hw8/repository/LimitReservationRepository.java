package hw8.repository;

import hw8.entity.LimitReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LimitReservationRepository extends JpaRepository<LimitReservation, UUID> {

    List<LimitReservation> findByCreateTimeIsLessThan(OffsetDateTime threshold);

}









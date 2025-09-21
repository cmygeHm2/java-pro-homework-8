package hw8.repository;

import hw8.entity.LimitReservationArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimitReservationArchiveRepository extends JpaRepository<LimitReservationArchive, UUID> {

}









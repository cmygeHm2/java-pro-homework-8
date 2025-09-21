package hw8.entity;

import hw8.enums.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "limit_reservation_archive")
@NoArgsConstructor
@Getter
@Setter
public class LimitReservationArchive {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "client_id")
    private Long clientId;

    @CreationTimestamp
    @Column(name = "create_time")
    private OffsetDateTime createTime;

    @Column(name = "finish_time")
    private OffsetDateTime finishTime;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum transactionStatus;
}

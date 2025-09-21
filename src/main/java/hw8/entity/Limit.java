package hw8.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "limits")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Limit {
    @Id
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_limit", precision = 18, scale = 2)
    private BigDecimal clientLimit;
}

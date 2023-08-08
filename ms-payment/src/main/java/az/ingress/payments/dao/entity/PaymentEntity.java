package az.ingress.payments.dao.entity;

import az.ingress.payments.model.enums.PaymentStatus;
import az.ingress.payments.model.enums.PaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    Long orderId;

    @Enumerated(STRING)
    PaymentType paymentType;
    String referenceNumber;

    @CreationTimestamp
    Instant createdAt;

    @Enumerated(STRING)
    PaymentStatus status;
    BigDecimal amount;

}

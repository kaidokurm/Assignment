package ee.kaido.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int volume;
    private BigDecimal totalPrice;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int employeeId;
    @OneToOne(cascade = CascadeType.ALL)
    private Share share;
}

package ee.kaido.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String shareName;
    @Column(unique = true, nullable = false)
    private String isinCode;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String economicActivity;
}

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rent")
public class RentEnrity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date fromDate;

    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private Double perDayRent;

    @Column(nullable = false)
    private Double advancePayment;
    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double deposit;

    @Column(nullable = false)
    private Boolean isReturn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Customer_Id", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Car_Id", nullable = false)
    private CarEntity carEntity;
}

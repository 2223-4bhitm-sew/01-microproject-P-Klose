package at.htl.entity;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(
                name = "Buss.findAll",
                query = "select b from Buss b"
        ),
        @NamedQuery(
                name = "Buss.findByFuelType",
                query="select b from Buss b where b.fuelType like :FIRST"
        )
})
@Entity
@Table(name = "MY_BUSS")
public class Buss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_ID")
    private Long id;

    @Column(name = "B_NUMBER_PLAT")
    private String numberPlate;

    @Column(name="B_SEATING_CAPACITY")
    private int seatingCapacity;

    @Column(name="B_STANDING_CAPACITY")
    private int standingCapacity;

    @Column(name="B_COMPANY_NAME")
    private String companyName;

    @Column(name="B_FUEL_TYPE")
    private String fuelType;


    public Buss() {
    }


    public Buss(String numberPlate, int seatingCapacity, int standingCapacity, String companyName, String fuelType) {
        this.numberPlate = numberPlate;
        this.seatingCapacity = seatingCapacity;
        this.standingCapacity = standingCapacity;
        this.companyName = companyName;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d %d", companyName, numberPlate, fuelType, seatingCapacity, standingCapacity);
    }
}

package uptc.pojos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VetVisit {
    private PetParent petParent;
    private Pet pet;
    private Vaccine vaccineUsed;
    private LocalDate day;
    private int numVaccinesUsed;

    @Override
    public String toString() {
        return "VetVisit{" +
                "petParent=" + petParent +
                ", pet=" + pet +
                ", vaccineUsed=" + vaccineUsed +
                ", day=" + day +
                ", vaccinesUsed=" + numVaccinesUsed +
                '}';
    }
}

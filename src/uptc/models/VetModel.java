package uptc.models;

import co.edu.uptc.interfaces.VetInterface;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.pojos.VetVisit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

@Getter
@Setter
public class VetModel implements VetInterface.Model {
    private ArrayList<VetVisit> visits;
    private TreeSet<Vaccine> vaccines;
    private VetInterface.Presenter presenter;

    public VetModel(){
        visits = new ArrayList<>();
        vaccines = new TreeSet<>();
    }


    @Override
    public void addVaccine(Vaccine vaccine) {
        if (vaccine != null) {
            vaccines.add(vaccine);
        }
    }

    @Override
    public void addVisit(VetVisit visit) {
        if (visit != null) {
            visits.add(visit);
        }
    }

@Override
public ArrayList<VetVisit> obtainVisitByCloseDueDate() {
    ArrayList<VetVisit> copyVisitsOrdered = new ArrayList<>(visits);
    copyVisitsOrdered.sort(Comparator.comparing(v -> {
        long daysSinceVisit = ChronoUnit.DAYS.between(v.getDay(), LocalDate.now()) + ChronoUnit.YEARS.between(v.getDay(), LocalDate.now()) * 365;
        long vaccineDueTime = v.getVaccineUsed().getDueTime().get(ChronoUnit.DAYS) + v.getVaccineUsed().getDueTime().get(ChronoUnit.YEARS) * 365;
        return Math.abs(daysSinceVisit - vaccineDueTime);
    }));
    return copyVisitsOrdered;
}

    @Override
    public ArrayList<VetVisit> obtainVisitByLaterDueDate() {
        ArrayList<VetVisit> copyVisitsOrdered = new ArrayList<>(visits);
        copyVisitsOrdered.sort(Comparator.comparing(v -> {
            long daysSinceVisit = ChronoUnit.DAYS.between(v.getDay(), LocalDate.now()) + ChronoUnit.YEARS.between(v.getDay(), LocalDate.now()) * 365;
            long vaccineDueTime = v.getVaccineUsed().getDueTime().get(ChronoUnit.DAYS) + v.getVaccineUsed().getDueTime().get(ChronoUnit.YEARS) * 365;
            return Math.abs(daysSinceVisit - vaccineDueTime);
        }));
        Collections.reverse(copyVisitsOrdered);
        return copyVisitsOrdered;
    }
    @Override
    public ArrayList<VetVisit> obtainVisitByPetParentPhoneNumber(Long phoneNumber) {
        ArrayList<VetVisit> visitsByPhoneNumber = new ArrayList<>();
        for (VetVisit visit : visits) {
            if (visit.getPetParent().getPhoneNumber().equals(phoneNumber)) {
                visitsByPhoneNumber.add(visit);
            }
        }
        return visitsByPhoneNumber;
    }

    @Override
    public ArrayList<VetVisit> obtainVisitByDate(LocalDate date) {
        ArrayList<VetVisit> visitsByDate = new ArrayList<>();
        for (VetVisit visit : visits) {
            if (visit.getDay().equals(date)) {
                visitsByDate.add(visit);
            }
        }
        return visitsByDate;
    }

    @Override
    public ArrayList<VetVisit> obtainVisitsUpSpecifiedWeight(int weight) {
        ArrayList<VetVisit> visitsUpSpecifiedWeight = new ArrayList<>();
        for (VetVisit visit : visits) {
            if (visit.getPet().getWeight() >= weight) {
                visitsUpSpecifiedWeight.add(visit);
            }
        }
        return visitsUpSpecifiedWeight;
    }

    @Override
    public ArrayList<VetVisit> obtainVisitsDownSpecifiedWeight(int weight) {
        ArrayList<VetVisit> visitsDownSpecifiedWeight = new ArrayList<>();
        for (VetVisit visit : visits) {
            if (visit.getPet().getWeight() < weight) {
                visitsDownSpecifiedWeight.add(visit);
            }
        }
        return visitsDownSpecifiedWeight;
    }

    @Override
    public void setPresenter(VetInterface.Presenter presenter) {
        this.presenter = presenter;
    }


}

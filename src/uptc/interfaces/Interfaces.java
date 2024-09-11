package uptc.interfaces;

import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.pojos.VetVisit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public interface Interfaces {
    interface Model {
        void addVaccine(Vaccine vaccine);
        void addVisit(VetVisit visit);
        ArrayList<VetVisit> obtainVisitByCloseDueDate();
        ArrayList<VetVisit> obtainVisitByLaterDueDate();
        ArrayList<VetVisit> obtainVisitByPetParentPhoneNumber(Long phoneNumber);
        ArrayList<VetVisit> obtainVisitByDate(LocalDate date);
        ArrayList<VetVisit> getVisits();
        TreeSet<Vaccine> getVaccines();

        ArrayList<VetVisit> obtainVisitsUpSpecifiedWeight(int weight);

        ArrayList<VetVisit> obtainVisitsDownSpecifiedWeight(int weight);

        void setPresenter(Presenter presenter);
        void setVisits(ArrayList<VetVisit> visits);
        void setVaccines(TreeSet<Vaccine> vaccines);
    }

    interface View {
        void start();

        void setPresenter(Presenter presenter);
    }


    interface Presenter {
        void start();
        void addVaccine(String[] vaccine);
        void addVisit(String[] visit);
        String[] obtainVaccinesName();
        Vaccine obtainVaccineByName(String vaccineName);
        Object[][] obtainVisits();
        Object[][] obtainVisitsByCloseDueDate();
        Object[][] obtainVisitsByPetParentPhoneNumber(Long phoneNumber);
        Object[][] obtainVisitsByDate(Date date);
        Object[][] obtainVisitsByLaterDueDate();
        void saveData();
        String[] obtainPetTypes();
        void getDataAndSetData();
        void setModel(Model model);
        void setView(View view);
        Object[][] obtainVisitsUpWeight(int i);
        Object[][] obtainVisitsDownWeight(int i);
    }
}
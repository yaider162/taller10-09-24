package interfaces;

public interface Interfaces {
    interface Model {

        void setPresenter(Presenter presenter);
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
        Object[][] obtainVisits();
        Object[][] obtainVisitsByCloseDueDate();
        Object[][] obtainVisitsByPetParentPhoneNumber(Long phoneNumber);
        Object[][] obtainVisitsByLaterDueDate();
        void getDataAndSetData();
        Object[][] obtainVisitsUpWeight(int i);
        Object[][] obtainVisitsDownWeight(int i);
        void registerAppointment(String[] strings);
    }
}
package uptc.presenter;

import interfaces.Interfaces;

public class Presenter implements Interfaces.Presenter {
    public Presenter() {
    }


    @Override
    public String[] obtainVaccinesName() {
        return new String[0];
    }

    @Override
    public Object[][] obtainVisits() {
        return new Object[0][];
    }

    @Override
    public Object[][] obtainVisitsByCloseDueDate() {
        return new Object[0][];
    }

    @Override
    public Object[][] obtainVisitsByPetParentPhoneNumber(Long phoneNumber) {
        return new Object[0][];
    }

    @Override
    public Object[][] obtainVisitsByLaterDueDate() {
        return new Object[0][];
    }

    @Override
    public void getDataAndSetData() {

    }

    @Override
    public Object[][] obtainVisitsUpWeight(int i) {
        return new Object[0][];
    }

    @Override
    public Object[][] obtainVisitsDownWeight(int i) {
        return new Object[0][];
    }

    @Override
    public void registerAppointment(String[] strings) {

    }
}

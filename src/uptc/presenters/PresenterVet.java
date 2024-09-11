package uptc.presenters;

import co.edu.uptc.interfaces.VetInterface;
import co.edu.uptc.models.assets.JSONManager;
import co.edu.uptc.pojos.*;
import co.edu.uptc.text.ManagerProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PresenterVet implements VetInterface.Presenter {
    private VetInterface.Model model;
    private final ManagerProperties managerProperties;
    private VetInterface.View view;

    public PresenterVet() throws IOException {
        managerProperties = new ManagerProperties();
        managerProperties.setFileName("files/vet.properties");
        managerProperties.load();
    }

    @Override
    public void start() {
        getDataAndSetData();
    }

    @Override
    public void addVaccine(String[] vaccine) {
        Vaccine vaccine1 = new Vaccine();
        vaccine1.setName(vaccine[0]);
        vaccine1.setDueTime(Period.parse(vaccine[1]));
        vaccine1.setPetType(PetType.fromDisplayName(vaccine[2]));
        System.out.println(vaccine1);
        model.addVaccine(vaccine1);
        saveData();
        model.getVaccines().forEach(System.out::println);
    }

    @Override
    public void addVisit(String[] visit) {
        VetVisit vetVisit = createVetVisit(visit);
        model.addVisit(vetVisit);
        saveData();
    }

    private VetVisit createVetVisit(String[] visit) {
        VetVisit vetVisit = new VetVisit();
        Pet pet = createPet(visit);
        PetParent petParent = createPetParent(visit);
        Vaccine vaccine = obtainVaccineByName(visit[4]);
        if (!pet.getPetType().equals(vaccine.getPetType())) {
            throw new IllegalArgumentException("La vacuna no es del tipo de la mascota");
        }
        vetVisit.setNumVaccinesUsed(Integer.parseInt(visit[1]));
        vetVisit.setDay(parseVisitDate(visit[5]));
        vetVisit.setPet(pet);
        vetVisit.setPetParent(petParent);
        vetVisit.setVaccineUsed(vaccine);
        return vetVisit;
    }

    private Pet createPet(String[] visit) {
        Pet pet = new Pet();
        pet.setName(visit[0]);
        pet.setPetType(PetType.fromDisplayName(visit[2]));
        pet.setWeight(Integer.parseInt(visit[9]));
        return pet;
    }

    private PetParent createPetParent(String[] visit) {
        PetParent petParent = new PetParent();
        petParent.setName(visit[3]);
        petParent.setPhoneNumber(Long.parseLong(visit[6]));
        petParent.setEmailAdress(visit[7]);
        petParent.setLastName(visit[8]);
        return petParent;
    }

    private LocalDate parseVisitDate(String visitDateStr) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(managerProperties.getValue("pattern") ,Locale.ENGLISH);
        return LocalDate.parse(visitDateStr, formatter);
    }

    @Override
    public String[] obtainVaccinesName(){
        TreeSet<Vaccine> vaccines = model.getVaccines();
        String[] vaccinesArray = new String[vaccines.size()];
        int i = 0;
        for (Vaccine vaccine : vaccines) {
            vaccinesArray[i] = vaccine.getName();
            i++;
        }
        return vaccinesArray;
    }

    @Override
    public Vaccine obtainVaccineByName(String vaccineName){
        TreeSet<Vaccine> vaccines = model.getVaccines();
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getName().equals(vaccineName)) {
                return vaccine;
            }
        }
        return null;
    }

    @Override
    public Object[][] obtainVisits() {
        return transformToMatrix(model.getVisits());
    }

    @Override
    public Object[][] obtainVisitsByCloseDueDate() {
        return transformToMatrix(model.obtainVisitByCloseDueDate());
    }

    @Override
    public Object[][] obtainVisitsByPetParentPhoneNumber(Long phoneNumber) {
        return transformToMatrix(model.obtainVisitByPetParentPhoneNumber(phoneNumber));
    }

    @Override
    public Object[][] obtainVisitsByDate(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(managerProperties.getValue("pattern"), Locale.ENGLISH);
        return transformToMatrix(model.obtainVisitByDate(LocalDate.parse(date.toString(), formatter)));
    }

    @Override
    public Object[][] obtainVisitsByLaterDueDate(){
        return transformToMatrix(model.obtainVisitByLaterDueDate());
    }

    @Override
    public void saveData() {
        JSONManager.createJSONFileByCollection(managerProperties.getValue("pathVetVisits"), model.getVisits());
        JSONManager.createJSONFileByCollection(managerProperties.getValue("pathVaccines"), model.getVaccines());
    }

    public void getDataAndSetData() {
        if (!Files.exists(Paths.get(managerProperties.getValue("pathVetVisits"))) && !Files.exists(Paths.get(managerProperties.getValue("pathVaccines")))){
            JSONManager.createJSONFileByCollection(managerProperties.getValue("pathVetVisits"),new ArrayList<>() );
            JSONManager.createJSONFileByCollection(managerProperties.getValue("pathVaccines"), new TreeSet<>());
        }
        Collection<?> collection = JSONManager.createCollectionByJSONFile(managerProperties.getValue("pathVetVisits"), VetVisit.class);
        Collection<?> collectionVaccines = JSONManager.createCollectionByJSONFile(managerProperties.getValue("pathVaccines"), Vaccine.class);
        if (collectionVaccines == null || collectionVaccines.isEmpty() || collection == null || collection.isEmpty()) {
            model.setVisits(new ArrayList<>());
            model.setVaccines(new TreeSet<>());
        }
        else{
            model.setVisits(new ArrayList<>(List.of(Arrays.copyOf(collection.toArray(), collection.size(), VetVisit[].class))));
            model.setVaccines(new TreeSet<>(List.of(Arrays.copyOf(collectionVaccines.toArray(), collectionVaccines.size(), Vaccine[].class))));
        }
    }

    @Override
    public String[] obtainPetTypes(){
        return new String[]{PetType.DOG.getDisplayName(), PetType.CAT.getDisplayName()};
    }

    @Override
    public void setModel(VetInterface.Model model) {
        this.model = model;
    }

    @Override
    public void setView(VetInterface.View view) {
        this.view = view;
    }

    @Override
    public Object[][] obtainVisitsUpWeight(int i) {
        return transformToMatrix(model.obtainVisitsUpSpecifiedWeight(i));
    }

    @Override
    public Object[][] obtainVisitsDownWeight(int i) {
        return transformToMatrix(model.obtainVisitsDownSpecifiedWeight(i));
    }

    private Object[][] transformToMatrix(ArrayList<VetVisit> visits) {
        Object[][] data = new Object[visits.size()][10];
        for (int i = 0; i < visits.size(); i++) {
            data[i] = fillRow(visits.get(i));
        }
        return data;
    }

    private Object[] fillRow(VetVisit visit) {
        Object[] row = new Object[11];
        row[0] = visit.getPetParent().getName();
        row[1] = visit.getPetParent().getLastName();
        row[2] = visit.getPetParent().getEmailAdress();
        row[3] = visit.getPetParent().getPhoneNumber();
        row[4] = visit.getPet().getName();
        row[5] = visit.getPet().getPetType().getDisplayName();
        row[6] = visit.getNumVaccinesUsed();
        row[7] = visit.getVaccineUsed().getName();
        row[8] = visit.getDay();
        row[9] = getVaccineDueDate(visit);
        row[10] = visit.getPet().getWeight();
        return row;
    }

    private String getVaccineDueDate(VetVisit visit) {
        LocalDate visitDate = visit.getDay();
        Period vaccineValidity = visit.getVaccineUsed().getDueTime();
        LocalDate vaccineExpiryDate = visitDate.plus(vaccineValidity);
        Period difference = Period.between(LocalDate.now(), vaccineExpiryDate);

        if (!difference.isNegative()) {
            return "Faltan " + difference.getYears() + " años, " + difference.getMonths() + " meses y " + difference.getDays() + " días";
        } else {
            difference = difference.negated();
            return "La vacuna expiró hace " + difference.getYears() + " años, " + difference.getMonths() + " meses y " + difference.getDays() + " días";
        }
    }
}

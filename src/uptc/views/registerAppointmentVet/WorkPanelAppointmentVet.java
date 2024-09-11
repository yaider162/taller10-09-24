package uptc.views.registerAppointmentVet;

import uptc.interfaces.Interfaces;
import lombok.Getter;
import lombok.Setter;
import uptc.views.wildCardClasses.CustomButton;
import uptc.views.wildCardClasses.LimitedTextField;
import uptc.views.wildCardClasses.NumericTextField;
import uptc.views.wildCardClasses.Global;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class WorkPanelAppointmentVet extends JPanel {
    private Interfaces.Presenter presenterVet;
    private LimitedTextField ownerName;
    private LimitedTextField petName;
    private NumericTextField phoneNumber;
    private LimitedTextField email;
    private LimitedTextField ownerLastName;
    private JDialog parent;
    private NumericTextField weight;
    private JComboBox<String> vaccinesNum;
    private JComboBox<String> petType;
    private JComboBox<String> vaccines;

    public WorkPanelAppointmentVet(JDialog parent, Interfaces.Presenter presenterVet){
        this.parent = parent;
        this.presenterVet = presenterVet;
    }

    public void buildPanel() {
        initWorkPanel();
        createLabelAndText();
        requestFocusInWindow();
        addButtons();
    }

    private void initWorkPanel() {
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        setLayout(new BorderLayout());
    }

    private void createLabelAndText(){
        JPanel gridPanel = new JPanel(new GridLayout(10, 3, 20, 20));
        addFirstAndSecondRow(gridPanel);
        createEmptySpace(gridPanel);
        addFourthRowAndFifth(gridPanel);
        createEmptySpace(gridPanel);
        addSeventhAndNinthRow(gridPanel);
        addWeightOption(gridPanel);
        int marginSize = 50;
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.add(gridPanel, BorderLayout.CENTER);
        marginPanel.setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));
        add(marginPanel);
    }

    private void addWeightOption(JPanel gridPanel) {
        JLabel label = new JLabel("Peso de la mascota: (en gramos)");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        gridPanel.add(new JLabel(""));
        gridPanel.add(new JLabel(""));
        weight = new NumericTextField("");
        gridPanel.add(weight);
    }

    private void addSeventhAndNinthRow(JPanel gridPanel) {
        JLabel label = new JLabel("Numero de telefono:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Correo Electronico:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Apellido del Responsable:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        phoneNumber = new NumericTextField("");
        gridPanel.add(phoneNumber);
        email = new LimitedTextField(35, "");
        gridPanel.add(email);
        ownerLastName = new LimitedTextField(12, "");
        gridPanel.add(ownerLastName);
    }

    private void addFirstAndSecondRow(JPanel gridPanel){
        JLabel label = new JLabel("Nombre de la mascota:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Numero de Vacunas a Colocar:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Tipo de Mascota:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        petName = new LimitedTextField(10, "");
        gridPanel.add(petName);
        vaccinesNum = new JComboBox<>(new String[]{"0", "1", "2", "3"});
        gridPanel.add(vaccinesNum);
        petType = new JComboBox<>(new String[]{"Perro", "Gato", "Otro"});
        gridPanel.add(petType);
    }

    private void createEmptySpace(JPanel gridPanel){
        for (int i = 0; i < 3; i++) {
            gridPanel.add(new JLabel(""));
        }
    }

    private void addFourthRowAndFifth(JPanel gridPanel){
        JLabel label = new JLabel("Nombre del Responsable:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Vacunas:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Fecha de la Cita: (dd/mm/yyyy)");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        ownerName = new LimitedTextField(10, "");
        gridPanel.add(ownerName);
        vaccines = new JComboBox<>(new String[]{"Vacuna1", "Vacuna2", "Vacuna3"});
        gridPanel.add(vaccines);
    }

    private void addButtons(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(createButtonRegister());
        buttonPanel.add(createButtonReturn());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButtonRegister(){
        CustomButton button = new CustomButton("Registrar Cita");
        button.addActionListener(e -> {
            if (presenterVet != null) {
                presenterVet.registerAppointment(returnData());
            }
        });
        return button;
    }

    private JButton createButtonReturn() {
        CustomButton button = new CustomButton("Volver");
        button.addActionListener(e -> parent.dispose());
        return button;
    }

    public String[] returnData() {
        return new String[]{
                petName.getText(),
                Objects.requireNonNull(vaccinesNum.getSelectedItem()).toString(),
                Objects.requireNonNull(petType.getSelectedItem()).toString(),
                ownerName.getText(),
                Objects.requireNonNull(vaccines.getSelectedItem()).toString(),
                phoneNumber.getText(),
                email.getText(),
                ownerLastName.getText(),
                weight.getText()
        };
    }
}
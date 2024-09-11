package uptc.views.registerAppointmentVet;

import co.edu.uptc.interfaces.VetInterface;
import co.edu.uptc.views.wildCardClasses.*;
import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
public class WorkPanelAppointmentVet extends JPanel {
    private VetInterface.Presenter presenterVet;
    private CustomJComboBox petType;
    private CustomJComboBox vaccinesNum;
    private CustomJComboBox vaccines;
    private LimitedTextField ownerName;
    private LimitedTextField petName;
    private JDateChooser dateChooser;
    private NumericTextField phoneNumber;
    private LimitedTextField email;
    private LimitedTextField ownerLastName;
    private JDialog parent;
    private NumericTextField weight;
    public WorkPanelAppointmentVet(JDialog parent, VetInterface.Presenter presenterVet){
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
        label = new JLabel("Numero de Vacunas a Colorcar:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        label = new JLabel("Tipo de Mascota:");
        label.setFont(Global.FONT_TEXTS);
        gridPanel.add(label);
        petName = new LimitedTextField(10, "");
        gridPanel.add(petName);
        gridPanel.add(vaccinesNum);
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
        gridPanel.add(vaccines);
        dateChooser = new JDateChooser();
        gridPanel.add(dateChooser);
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(dateChooser.getDate());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inputDate = LocalDate.parse(dateString, dtf);
            if (inputDate.isAfter(LocalDate.now())) {
                presenterVet.addVisit(returnData());
                parent.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "La fecha ingresada no puede ser anterior a la fecha actual.", "Error", JOptionPane.ERROR_MESSAGE);
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
                Objects.requireNonNull(dateChooser.getDate()).toString(),
                phoneNumber.getText(),
                email.getText(),
                ownerLastName.getText(),
                weight.getText()
        };
    }
}
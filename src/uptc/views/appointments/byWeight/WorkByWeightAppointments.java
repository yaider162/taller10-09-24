package uptc.views.appointments.byWeight;

import co.edu.uptc.interfaces.VetInterface;
import co.edu.uptc.views.appointments.Table;
import co.edu.uptc.views.wildCardClasses.CustomButton;
import co.edu.uptc.views.wildCardClasses.Global;
import co.edu.uptc.views.wildCardClasses.NumericTextField;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class WorkByWeightAppointments extends JPanel {
    private final VetInterface.Presenter presenter;
    private Table tableByDueDateVaccine;
    private CustomButton button;
    private NumericTextField numericTextField;

    public WorkByWeightAppointments(VetInterface.Presenter presenter){
        this.presenter = presenter;
        initWorkPanel();
    }
    private void initWorkPanel() {
        tableByDueDateVaccine = new Table();
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        createTitle();
        createTable();
    }
    private void createTable() {
        JScrollPane scrollPane = new JScrollPane(tableByDueDateVaccine.getTable());
        scrollPane.setPreferredSize(obtainSizeForTable());
        add(scrollPane, BorderLayout.CENTER);
    }
    private void createTitle(){
        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(Global.WORK_BACKGROUND_COLOR);
        subHeaderPanel.setLayout(new BoxLayout(subHeaderPanel, BoxLayout.X_AXIS));
        subHeaderPanel.setPreferredSize(obtainSizeForSubHeader());
        createTitle(subHeaderPanel);
        createButtonOrganize(subHeaderPanel);
        createNumericTextFieldForWeight(subHeaderPanel);
        add(subHeaderPanel, BorderLayout.NORTH);
    }
    private void createButtonOrganize(JPanel panel){
        button = new CustomButton("↑↓(Por encima o Igual)");
        button.addActionListener(e -> {
            if (button.getText().equals("↑↓(Por encima o Igual)")) {
                button.setText("↓↑(Por debajo)");
            }
            else {
                button.setText("↑↓(Por encima o Igual)");
            }
            setData();
        });
        panel.add(button);
    }
    private void createNumericTextFieldForWeight(JPanel panel){
        numericTextField = new NumericTextField("Ingrese el peso en gramos");
        numericTextField.setPreferredSize(new Dimension(100, 30));
        panel.add(numericTextField);
    }
    private void createTitle(JPanel panel){
        JLabel title = new JLabel("Citas por Proximas a Vacuna: ");
        title.setFont(Global.FONT_TITLE_BIG);
        title.setForeground(Global.WORK_TEXT_COLOR);
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(title);
    }
    private Dimension obtainSizeForTable(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.95;
        double height = screenSize.getHeight() * 0.5;
        return new Dimension((int)width, (int)height);
    }
    private Dimension obtainSizeForSubHeader(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.95;
        double height = screenSize.getHeight() * 0.1;
        return new Dimension((int)width, (int)height);
    }

    public void setData() {
        presenter.getDataAndSetData();
        Object[][] newData;
        if (button.getText().equals("↑↓(Por encima o Igual)")) {
            newData = presenter.obtainVisitsUpWeight(Integer.parseInt(numericTextField.getText()));
        } else {
            newData = presenter.obtainVisitsDownWeight(Integer.parseInt(numericTextField.getText()));
        }
        tableByDueDateVaccine.clearTable();
        for (Object[] datum : newData) {
            tableByDueDateVaccine.putData(datum);
        }
    }
}

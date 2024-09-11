package uptc.views.appointments.byParent;


import uptc.interfaces.Interfaces;
import lombok.Getter;
import uptc.views.appointments.Table;
import uptc.views.wildCardClasses.NumericTextField;
import uptc.views.wildCardClasses.Global;

import javax.swing.*;
import java.awt.*;

@Getter
public class WorkByParentAppointments extends JPanel {
    private final Interfaces.Presenter presenterVet;
    private Table tableByPhoneNumber;
    private NumericTextField numericTextField;

    public WorkByParentAppointments(Interfaces.Presenter presenterVet){
        this.presenterVet = presenterVet;
        initWorkPanel();
    }
    private void initWorkPanel() {
        tableByPhoneNumber = new Table();
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        createTitle();
        createTable();
    }
    private void createTable() {
        JScrollPane scrollPane = new JScrollPane(tableByPhoneNumber.getTable());
        scrollPane.setPreferredSize(obtainSizeForTable());
        add(scrollPane, BorderLayout.CENTER);
    }
    private void createTitle(){
        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(Global.WORK_BACKGROUND_COLOR);
        subHeaderPanel.setLayout(new BoxLayout(subHeaderPanel, BoxLayout.X_AXIS));
        subHeaderPanel.setPreferredSize(obtainSizeForSubHeader());
        createTitle(subHeaderPanel);
        createTextField(subHeaderPanel);
        add(subHeaderPanel, BorderLayout.NORTH);
    }
    private void createTextField(JPanel panel) {
        numericTextField = new NumericTextField("Numero telefonico del responsable:");
        numericTextField.addActionListener(e -> setData());
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
        for (Object[] object: presenterVet.obtainVisitsByPetParentPhoneNumber(Long.parseLong(numericTextField.getText()))){
            tableByPhoneNumber.putData(object);
        }
    }
}

package uptc.views.appointments;

import co.edu.uptc.views.wildCardClasses.Global;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@Getter
public class Table {
    private JTable table;

    public Table() {
        createTable();
    }
    private void createTable() {
        String[] columnNames = {
                "Nombre del Responsable", "Apellido", "Correo Electronico",
                "Numero de Telefono", "Nombre de la Mascota", "Tipo de Mascota", "Numero de Vacunas Usadas",
                "Vacunas Usadas", "Dia de la Cita", "Fecha de Vencimiento de la Vacuna", "Peso de la mascota"
        };
        DefaultTableModel model = new DefaultTableModel(null, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);

        table.setFont(Global.FONT_TEXTS_SMALL);
        table.setRowHeight(32);

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnNames.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(200);
        }
    }
    public void putData(Object[] data){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(data);
    }

    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

}

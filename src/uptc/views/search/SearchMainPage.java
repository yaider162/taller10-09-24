package uptc.views.search;

import uptc.interfaces.Interfaces;
import lombok.Setter;
import uptc.views.mainpage.MainPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Setter
public class SearchMainPage extends JDialog {
    private Interfaces.Presenter presenterVet;
    private MainPageFrame mainPageFrame;

    public SearchMainPage(MainPageFrame mainPageFrame, Interfaces.Presenter presenterVet){
        super(mainPageFrame, true);
        this.mainPageFrame = mainPageFrame;
        this.presenterVet = presenterVet;
        initComponents();
        createHeaderPanel();
        createWorkPanel();
        this.setOpacity(0);
        fadeIn();
    }
    private void initComponents(){
        this.setTitle("Registrar Cita");
        this.setSize(obtainSize());
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    private Dimension obtainSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() * 0.9;
        return new Dimension((int)width, (int)height);
    }
    private void createHeaderPanel(){
        SearchHeader headerPanel = new SearchHeader(this, mainPageFrame, presenterVet);
        this.add(headerPanel, BorderLayout.NORTH);
    }
    private void createWorkPanel(){
        WorkPanelSearch workPanelSearch = new WorkPanelSearch(this, presenterVet);
        workPanelSearch.buildPanel();
        add(workPanelSearch, BorderLayout.CENTER);
    }
    private void fadeIn() {
        Timer timer = new Timer(10, new ActionListener() {
            private float opacity = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.15f;
                setOpacity(Math.min(opacity, 1));
                if (opacity >= 1) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}

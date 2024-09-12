import uptc.interfaces.Interfaces;
import uptc.models.JSONReader;
import uptc.presenter.Presenter;
import uptc.views.mainpage.MainPageFrame;

public class App {
    public static void main(String[] args) {
//        MainPageFrame mainPageFrame = new MainPageFrame();
//        Interfaces.Presenter presenter = new Presenter();
//        mainPageFrame.setPresenter(presenter);
//        mainPageFrame.start();
        JSONReader.readJsonFromUrl("https://data.wa.gov/api/views/f6w7-q2d2/rows.json?accessType=DOWNLOAD", String.class);
    }
}

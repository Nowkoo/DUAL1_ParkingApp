import Model.Data;
import View.MainFrame;

public class ParkingAppMain {
    public static void main(String[] args) {
        Data.loadVehicles();
        new MainFrame();
    }
}

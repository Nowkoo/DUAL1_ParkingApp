package Model.Vehicles;

import java.util.ArrayList;

public class OfficialVehicle extends Vehicle {
    ArrayList<Stay> stays = new ArrayList<>();

    public OfficialVehicle(String licensePlateNumber) {
        super(licensePlateNumber);
        this.pricePerMinute = 0;
        dataStored = true;
    }

    public void leaves() {
        super.leaves();
        stays.add(super.currentStay);
    }

    @Override
    public float calculateStayImport() {
        return 0;
    }

    public void clearStays() {
        stays = new ArrayList<>();
    }
}

package Model.Vehicles;

public class NonResidentVehicle extends Vehicle {
    public NonResidentVehicle(String licensePlateNumber) {
        super(licensePlateNumber);
        pricePerMinute = 0.02f;
        dataStored = false;
    }

    @Override
    public float calculateStayImport() {
        return pricePerMinute * currentStay.minutesSpent();
    }
}

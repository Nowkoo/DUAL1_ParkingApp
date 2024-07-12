package Model.Vehicles;

public class ResidentVehicle extends Vehicle {
    int minutesThisMonth;

    public ResidentVehicle(String licensePlateNumber) {
        super(licensePlateNumber);
        pricePerMinute = 0.002f;
        dataStored = true;
    }

    public void leaves() {
        super.leaves();
        minutesThisMonth += currentStay.minutesSpent();
    }

    @Override
    public float calculateStayImport() {
        return 0;
    }

    public float calculateMonthlyImport() {
        return Math.round(minutesThisMonth * pricePerMinute);
    }

    public int getMinutesThisMonth() {
        return minutesThisMonth;
    }

    public void resetMinutesThisMont() {
        minutesThisMonth = 0;
    }
}

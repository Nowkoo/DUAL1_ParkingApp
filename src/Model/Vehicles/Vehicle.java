package Model.Vehicles;

import java.io.Serializable;
import java.util.Date;

public abstract class Vehicle implements Serializable {
    private String licensePlateNumber;
    protected boolean dataStored;
    protected float pricePerMinute;
    protected Stay currentStay = null;

    public Vehicle(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void arrives() {
        currentStay = new Stay(new Date());
    }

    public void leaves() {
        currentStay.setDepartureTime(new Date());
    }

    public abstract float calculateStayImport();

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public boolean isDataStored() {
        return dataStored;
    }

    public Stay getCurrentStay() {
        return currentStay;
    }

    public void setCurrentStay(Stay currentStay) {
        this.currentStay = currentStay;
    }
}

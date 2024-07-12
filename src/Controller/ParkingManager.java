package Controller;

import Model.Data;
import Model.Vehicles.NonResidentVehicle;
import Model.Vehicles.OfficialVehicle;
import Model.Vehicles.ResidentVehicle;
import Model.Vehicles.Vehicle;

import java.util.Date;

public class ParkingManager {

    public void onEntrance(String licensePlateNumber) {
        Vehicle vehicle = findVehicle(licensePlateNumber);
        if (vehicle == null) {
            vehicle = new NonResidentVehicle(licensePlateNumber);
            registerVehicle(vehicle);
        }
        vehicle.arrives();
    }

    public float onDeparture(Vehicle vehicle) {
        vehicle.leaves();
        if (!vehicle.isDataStored()) {
            removeVehicle(vehicle);
        }
        return vehicle.calculateStayImport();
    }

    public void registerVehicle(Vehicle vehicle) {
        Data.getVehicles().add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        Data.getVehicles().remove(vehicle);
    }

    public Vehicle findVehicle(String licensePlateNumber) {
        for (Vehicle currentVehicle : Data.getVehicles()) {
            if (currentVehicle.getLicensePlateNumber().equals(licensePlateNumber)) {
                return currentVehicle;
            }
        }
        return null;
    }

    public boolean isInParking(Vehicle vehicle) {
        if (vehicle == null || vehicle.getCurrentStay() == null) return false;

        Date departureTime = vehicle.getCurrentStay().getDepartureTime();
        if (departureTime != null && departureTime.getTime() < new Date().getTime()) return false;

        return true;
    }

    public void restartMonth() {
        for (Vehicle vehicle : Data.getVehicles()) {
            if (vehicle instanceof OfficialVehicle) {
                OfficialVehicle officialVehicle = (OfficialVehicle) vehicle;
                officialVehicle.clearStays();
            } else if (vehicle instanceof ResidentVehicle) {
                ResidentVehicle residentVehicle = (ResidentVehicle) vehicle;
                residentVehicle.resetMinutesThisMont();
            }
        }
    }
}

package Controller;

import Model.Vehicles.NonResidentVehicle;
import Model.Vehicles.Vehicle;

import javax.swing.*;

public class MenuController {

    ParkingManager parkingManager = new ParkingManager();

    public void arrival() {
        String licensePlate = DialogUtils.askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle vehicle = parkingManager.findVehicle(licensePlate);

        if (parkingManager.isInParking(vehicle)) {
            JOptionPane.showMessageDialog(null, "This vehicle has already entered the parking. It can't enter twice!");
            return;
        }

        parkingManager.onEntrance(licensePlate);
    }

    public void departure() {
        String licensePlate = DialogUtils.askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle vehicle = parkingManager.findVehicle(licensePlate);

        if (!parkingManager.isInParking(vehicle)) {
            JOptionPane.showMessageDialog(null, "This vehicle isn't in the parking. It can't leave it!");
            return;
        }

        float amountToPay = parkingManager.onDeparture(vehicle);
        JOptionPane.showMessageDialog(null, "Amount to charge: " + DialogUtils.to2DecimalString(amountToPay) + "€");
    }

    public void add() {
        String selectedItem = DialogUtils.pickVehicleType();
        String licensePlate = DialogUtils.askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle newVehicle = getVehicle(licensePlate, selectedItem);
        parkingManager.registerVehicle(newVehicle);
    }

    private Vehicle getVehicle(String licensePlate, String vehicleType) {
        Vehicle newVehicle = VehicleFactory.createVehicle(vehicleType, licensePlate);
        Vehicle oldVehicle = parkingManager.findVehicle(licensePlate);
        if (oldVehicle != null) {
            newVehicle.setCurrentStay(oldVehicle.getCurrentStay());
            parkingManager.removeVehicle(oldVehicle);
        }
        return newVehicle;
    }

    public void delete() {
        String licensePlate = DialogUtils.askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle vehicle = parkingManager.findVehicle(licensePlate);

        if (vehicle == null || !vehicle.isDataStored()) {
            JOptionPane.showMessageDialog(null, "Vehicle not found");
            return;
        }

        parkingManager.removeVehicle(vehicle);
        JOptionPane.showMessageDialog(null, "Deleted");
        if (vehicle.getCurrentStay() != null) replaceVehicle(vehicle);
    }

    private void replaceVehicle(Vehicle vehicle) {
        NonResidentVehicle newVehicle = new NonResidentVehicle(vehicle.getLicensePlateNumber());
        newVehicle.setCurrentStay(vehicle.getCurrentStay());
        parkingManager.registerVehicle(newVehicle);
    }

    public void printReport() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport();
        JOptionPane.showMessageDialog(null, "Report generated in the next directory:\n" + reportGenerator.getReportsPath());
    }

    public void newMonth() {
        printReport();
        parkingManager.restartMonth();
    }
}

package Controller;

import Model.Vehicles.NonResidentVehicle;
import Model.Vehicles.Vehicle;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuController {

    ParkingManager parkingManager = new ParkingManager();

    public void arrival() {
        String licensePlate = askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle vehicle = parkingManager.findVehicle(licensePlate);

        if (parkingManager.isInParking(vehicle)) {
            JOptionPane.showMessageDialog(null, "This vehicle has already entered the parking. It can't enter twice!");
            return;
        }

        parkingManager.onEntrance(licensePlate);
    }

    public void departure() {
        String licensePlate = askForLicensePlate();
        if (licensePlate == null) return;
        Vehicle vehicle = parkingManager.findVehicle(licensePlate);

        if (!parkingManager.isInParking(vehicle)) {
            JOptionPane.showMessageDialog(null, "This vehicle isn't in the parking. It can't leave it!");
            return;
        }

        float amountToPay = parkingManager.onDeparture(vehicle);
        JOptionPane.showMessageDialog(null, "Amount to charge: " + amountToPay);
    }

    public void add() {
        String selectedItem = pickVehicleType();
        String licensePlate = askForLicensePlate();
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

    private static String pickVehicleType() {
        String[] options = new String[] {"Official vehicle", "Resident vehicle"};
        JComboBox<String> combobox = new JComboBox<>(options);
        JOptionPane.showMessageDialog(null, combobox);
        String selectedItem = (String) combobox.getSelectedItem();
        return selectedItem;
    }

    public void delete() {
        String licensePlate = askForLicensePlate();
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

    public String askForLicensePlate() {
        String input = JOptionPane.showInputDialog("Insert vehicle's license number:");
        if (input == null) return null;
        if (!validLicensePlate(input)) {
            JOptionPane.showMessageDialog(null, "Invalid license plate format.");
            return null;
        }
        return input;
    }

    public boolean validLicensePlate(String input) {
        String regex = "^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPRSTVWXYZ]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}

package Controller;

import Model.Data;
import Model.Vehicles.ResidentVehicle;
import Model.Vehicles.Vehicle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportGenerator {
    File directory = new File("./resources/reports");
    String fileName = "report.txt";

    public void generateReport() {
        File filePath = new File(directory, fileName);
        if (!directory.exists()) directory.mkdir();
        PrintWriter f_out;
        try {
            f_out = new PrintWriter(new FileWriter(filePath));
            f_out.printf("%s\t%s\t%s", "Matrícula", "Tiempo estacionado (min.)", "Cantidad a pagar");

            for (Vehicle currentVehicle : Data.getVehicles()) {
                if (currentVehicle instanceof ResidentVehicle) {
                    ResidentVehicle vehicle = (ResidentVehicle) currentVehicle;
                    f_out.printf("\n%-9s\t%-25d\t%-16f", currentVehicle.getLicensePlateNumber(), vehicle.getMinutesThisMonth(), vehicle.calculateMonthlyImport());
                }
            }
            f_out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getReportsPath() {
        return directory.getAbsolutePath();
    }
}

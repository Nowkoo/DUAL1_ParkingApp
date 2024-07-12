package Model;

import Model.Vehicles.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class Data {
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static String directoryPath = "./resources/data";
    private static String fileName = "data.ser";

    public static void saveVehicles() {
        ensureDirExists();
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(directoryPath, fileName));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(vehicles);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadVehicles() {
        try {
            FileInputStream fileIn = new FileInputStream(new File(directoryPath, fileName));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            vehicles = (ArrayList<Vehicle>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ensureDirExists() {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
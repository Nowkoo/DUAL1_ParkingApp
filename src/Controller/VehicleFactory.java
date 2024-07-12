package Controller;

import Model.Vehicles.OfficialVehicle;
import Model.Vehicles.ResidentVehicle;
import Model.Vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleFactory {
    static Map<String, Class<? extends Vehicle>> vehicleMap = new HashMap<>();

    static {
        vehicleMap.put("Official vehicle", OfficialVehicle.class);
        vehicleMap.put("Resident vehicle", ResidentVehicle.class);
    }

    public static Vehicle createVehicle(String type, String licensePlate) {
        try {
            return vehicleMap.get(type).getConstructor(String.class).newInstance(licensePlate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

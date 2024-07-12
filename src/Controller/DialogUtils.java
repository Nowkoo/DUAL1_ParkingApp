package Controller;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogUtils {
    public static String askForLicensePlate() {
        String input = JOptionPane.showInputDialog("Insert vehicle's license number:");
        if (input == null) return null;
        if (!validLicensePlate(input)) {
            JOptionPane.showMessageDialog(null, "Invalid license plate format.");
            return null;
        }
        return input;
    }

    public static boolean validLicensePlate(String input) {
        String regex = "^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPRSTVWXYZ]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    static String pickVehicleType() {
        String[] options = new String[] {"Official vehicle", "Resident vehicle"};
        JComboBox<String> combobox = new JComboBox<>(options);
        JOptionPane.showMessageDialog(null, combobox);
        return (String) combobox.getSelectedItem();
    }

    static String to2DecimalString(float number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(number);
    }
}

package Model.Vehicles;

import java.io.Serializable;
import java.util.Date;

public class Stay implements Serializable {
    private Date arrivalTime;
    private Date departureTime;

    public Stay(Date date) {
        arrivalTime = date;
    }

    public long milisecondsSpent() {
        return departureTime.getTime() - arrivalTime.getTime();
    }
    public int minutesSpent() {
        long miliseconds = milisecondsSpent();
        return (int) miliseconds / (1000 * 60);
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}

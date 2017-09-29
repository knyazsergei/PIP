package ps.rip.Model;

import java.util.Date;

public class Car {
    private String name;
    private double speed;
    private Date date;

    public Car(String name, double speed, Date date) {
        this.name = name;
        this.speed = speed;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

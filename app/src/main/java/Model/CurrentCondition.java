package Model;

/**
 * Created by SK on 1/5/2018.
 */
public class CurrentCondition {

    private float tempreture;
    private float pressure;
    private float humidity;
    private int wheatherId;
    private String condition, description, icon;

    public float getTempreture() {
        return tempreture;
    }

    public void setTempreture(float tempreture) {
        this.tempreture = tempreture;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getWheatherId() {
        return wheatherId;
    }

    public void setWheatherId(int wheatherId) {
        this.wheatherId = wheatherId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

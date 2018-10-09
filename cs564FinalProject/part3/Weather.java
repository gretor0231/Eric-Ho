import java.io.Serializable;


public class Weather  implements Serializable {

    private final Double weather;

    Weather(Double weather) {
        this.weather = weather;

    }


    public String toString(){
        return "\nWeather Information\n" +
                "Weather: " + getdegree() +  "\n";
    }

    public Double getdegree() {
        return weather;
    }


}

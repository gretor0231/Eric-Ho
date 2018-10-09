import java.io.Serializable;


public class Location  implements Serializable {

    private final String Country;

    Location(String Country) {
        this.Country = Country;

    }


    public String toString(){
        return "\nLocation Information\n" +
                "Country: " + getCountry() +  "\n";
    }

    public String getCountry() {
        return Country;
    }


}

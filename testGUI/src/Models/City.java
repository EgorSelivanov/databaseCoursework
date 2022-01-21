package Models;

public class City extends BaseModel {
    private String country;
    private String name;

    public City(){};

    public City(long id, String country, String name)
    {
        super(id);
        this.country = country;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

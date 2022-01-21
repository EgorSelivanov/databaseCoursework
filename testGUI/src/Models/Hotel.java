package Models;

import java.math.BigDecimal;

public class Hotel extends BaseModel {
    private long cityID;
    private String nameOfHotel;
    private int numberOfStars;
    private BigDecimal priceForAccomadation;

    public Hotel() {
    }

    ;

    public Hotel(long hotelID, long cityID, String nameOfHotel, int numberOfStars, BigDecimal priceForAccomadation) {
        super(hotelID);
        this.cityID = cityID;
        this.nameOfHotel = nameOfHotel;
        this.numberOfStars = numberOfStars;
        this.priceForAccomadation = priceForAccomadation;
    }


    public long getCityID() {
        return cityID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }

    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public void setNameOfHotel(String nameOfHotel) {
        this.nameOfHotel = nameOfHotel;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public BigDecimal getPriceForAccomadation() {
        return priceForAccomadation;
    }

    public void setPriceForAccomadation(BigDecimal priceForAccomadation) {
        this.priceForAccomadation = priceForAccomadation;
    }
}

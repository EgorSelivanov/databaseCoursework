package Models;

import java.math.BigDecimal;

public class HotelsOfOrder extends BaseModel {
    private String cityName;
    private String nameOfHotel;
    private int numberOfStars;
    private BigDecimal priceForAccomadation;

    public HotelsOfOrder() {
    }

    public HotelsOfOrder(long hotelID, String cityName, String nameOfHotel, int numberOfStars, BigDecimal priceForAccomadation) {
        super(hotelID);
        this.cityName = cityName;
        this.nameOfHotel = nameOfHotel;
        this.numberOfStars = numberOfStars;
        this.priceForAccomadation = priceForAccomadation;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

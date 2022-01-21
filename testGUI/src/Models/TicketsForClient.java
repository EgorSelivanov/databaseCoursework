package Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketsForClient extends BaseModel{
    private LocalDateTime departureTime;
    private LocalDateTime arriveTime;
    private String transport;
    private BigDecimal ticketPrice;
    private int placeNumber;
    private String departureCity;
    private String arrivalCity;

    public TicketsForClient(){};

    public TicketsForClient(long id, LocalDateTime departureTime, LocalDateTime arriveTime, String transport, BigDecimal ticketPrice,
                  int placeNumber, String departureCity, String arrivalCity)
    {
        super(id);
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.transport = transport;
        this.ticketPrice = ticketPrice;
        this.placeNumber = placeNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}

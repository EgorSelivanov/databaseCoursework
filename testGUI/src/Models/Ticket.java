package Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket extends BaseModel{
    private LocalDateTime departureTime;
    private LocalDateTime arriveTime;
    private String transport;
    private BigDecimal ticketPrice;
    private int placeNumber;
    private long idDepartureCity;
    private long idArrivalCity;

    public Ticket(){};

    public Ticket(long id, LocalDateTime departureTime, LocalDateTime arriveTime, String transport, BigDecimal ticketPrice,
                  int placeNumber, long idDepartureCity, long idArrivalCity)
    {
        super(id);
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.transport = transport;
        this.ticketPrice = ticketPrice;
        this.placeNumber = placeNumber;
        this.idDepartureCity = idDepartureCity;
        this.idArrivalCity = idArrivalCity;
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

    public long getIdDepartureCity() {
        return idDepartureCity;
    }

    public void setIdDepartureCity(long idDepartureCity) {
        this.idDepartureCity = idDepartureCity;
    }

    public long getIdArrivalCity() {
        return idArrivalCity;
    }

    public void setIdArrivalCity(long idArrivalCity) {
        this.idArrivalCity = idArrivalCity;
    }
}

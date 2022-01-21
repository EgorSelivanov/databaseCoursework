package Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order extends BaseModel{
    private long idClient;
    private long idEmployee;
    private BigDecimal orderCost;
    private LocalDate dateOfOrdering;
    private long idTicketDeparture;
    private long idTicketArrival;
    private long idHotel;

    public Order() {};

    public Order(long id, long idClient, long idEmployee, BigDecimal orderCost, LocalDate dateOfOrdering,
                 long idTicketDeparture, long idTicketArrival, long idHotel)
    {
        super(id);
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.orderCost = orderCost;
        this.dateOfOrdering = dateOfOrdering;
        this.idTicketDeparture = idTicketDeparture;
        this.idTicketArrival = idTicketArrival;
        this.idHotel = idHotel;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public LocalDate getDateOfOrdering() {
        return dateOfOrdering;
    }

    public void setDateOfOrdering(LocalDate dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
    }

    public long getIdTicketDeparture() {
        return idTicketDeparture;
    }

    public void setIdTicketDeparture(long idTicketDeparture) {
        this.idTicketDeparture = idTicketDeparture;
    }

    public long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(long idHotel) {
        this.idHotel = idHotel;
    }

    public long getIdTicketArrival() {
        return idTicketArrival;
    }

    public void setIdTicketArrival(long idTicketArrival) {
        this.idTicketArrival = idTicketArrival;
    }
}

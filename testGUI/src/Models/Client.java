package Models;

import java.time.LocalDate;
import java.util.Date;

public class Client extends BaseModel{
    private String surname;
    private String name;
    private String patronymic;
    private String passportData;
    private String internationalPassport;
    private LocalDate dateOfBorn;
    private String phoneNumber;

    public Client() {};

    public Client(long id, String surname, String name, String patronymic,
                  String passportData, String internationalPassport, LocalDate dateOfBorn, String phoneNumber)
    {
        super(id);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.passportData = passportData;
        this.internationalPassport = internationalPassport;
        this.dateOfBorn = dateOfBorn;
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getInternationalPassport() {
        return internationalPassport;
    }

    public void setInternationalPassport(String internationalPassport) {
        this.internationalPassport = internationalPassport;
    }

    public LocalDate getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(LocalDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

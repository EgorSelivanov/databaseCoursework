package Models;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends BaseModel{
    private String surname;
    private String name;
    private String patronymic;
    private String passportData;
    private LocalDate dateOfBorn;
    private String phoneNumber;
    private String position;

    public Employee() {};

    public Employee(long id, String surname, String name, String patronymic,
                    String passportData, LocalDate dateOfBorn, String phoneNumber, String position)
    {
        super(id);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.passportData = passportData;
        this.dateOfBorn = dateOfBorn;
        this.phoneNumber = phoneNumber;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

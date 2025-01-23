package pro.sky.listspring;
import java.util.Objects;

public class Employee {
    private String name;
    private String lastname;

    private static int idCounter = 0;
    private final int idEmployee;

    // getter for name field
    public String getName() {
        return name;
    }

    // setter for name field
    public void setName(String name) {
        this.name = name;
    }


    // getter for lastname field
    public String getLastname() {
        return lastname;
    }

    // setter for lastname field
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // constructor for Employee class
    public Employee(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;

        this.idEmployee = idCounter++;
    }

    //to display information about an employee
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + idEmployee +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    // equals contract
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee otherEmp = (Employee) o;

        return Objects.equals(name, otherEmp.name) &&
                Objects.equals(lastname, otherEmp.lastname);
    }

    // hashCode contract
    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }


}
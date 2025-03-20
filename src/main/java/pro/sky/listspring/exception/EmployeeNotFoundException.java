package pro.sky.listspring.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(){
        super("Сотрудник не найден");
    }
}

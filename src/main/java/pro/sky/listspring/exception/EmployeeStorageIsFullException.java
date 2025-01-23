package pro.sky.listspring.exception;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Превышен лимит сотрудников в фирме");
    }
}

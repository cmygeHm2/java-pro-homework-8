package hw8.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(Long clientId) {
        super("Недостаточно средств у клиента " + clientId);
    }
}

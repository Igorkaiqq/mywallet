package unipar.integrador.mywallet.application.exception;

public class TransacaoNotFoundException extends RuntimeException {
    public TransacaoNotFoundException(String message) {
        super(message);
    }
}

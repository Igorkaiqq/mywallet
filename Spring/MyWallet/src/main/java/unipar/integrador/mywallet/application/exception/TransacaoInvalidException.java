package unipar.integrador.mywallet.application.exception;

public class TransacaoInvalidException extends RuntimeException {
    public TransacaoInvalidException(String message) {
        super(message);
    }
}

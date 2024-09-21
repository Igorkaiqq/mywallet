package unipar.integrador.mywallet.application.enums;

public enum MetodoPagamentoEnum {
    DINHEIRO(1),
    CARTAO_CREDITO(2),
    CARTAO_DEBITO(3),
    PIX(4);

    private final int code;

    MetodoPagamentoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MetodoPagamentoEnum fromCode(int code) {
        for (MetodoPagamentoEnum metodo : MetodoPagamentoEnum.values()) {
            if (metodo.getCode() == code) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Código de método de pagamento inválido: " + code);
    }
}


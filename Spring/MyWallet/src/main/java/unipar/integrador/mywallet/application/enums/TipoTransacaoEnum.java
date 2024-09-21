package unipar.integrador.mywallet.application.enums;

public enum TipoTransacaoEnum {
    RECEITA(1),
    DESPESA(2);

    private final int code;

    TipoTransacaoEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoTransacaoEnum fromCode(int code) {
        for (TipoTransacaoEnum tipo : TipoTransacaoEnum.values()) {
            if (tipo.getCode() == code) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de tipo de transação inválido: " + code);
    }
}


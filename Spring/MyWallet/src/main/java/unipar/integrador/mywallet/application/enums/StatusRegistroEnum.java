package unipar.integrador.mywallet.application.enums;

public enum StatusRegistroEnum {
    ATIVO(1),
    INATIVO(2),
    DELETADO(3);

    private final int code;

    StatusRegistroEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusRegistroEnum fromCode(int code) {
        for (StatusRegistroEnum status : StatusRegistroEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + code);
    }
}

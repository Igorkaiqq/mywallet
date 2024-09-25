package unipar.integrador.mywallet.application.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeneroEnum {
    MASCULINO(1),
    FEMININO(2),
    OUTRO(3);

    private final int code;

    GeneroEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GeneroEnum fromCode(int code) {
        for (GeneroEnum genero : GeneroEnum.values()) {
            if (genero.getCode() == code) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Código de gênero inválido: " + code);
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }

    @JsonCreator
    public static GeneroEnum fromString(String value) {
        return GeneroEnum.valueOf(value.toUpperCase());
    }

}


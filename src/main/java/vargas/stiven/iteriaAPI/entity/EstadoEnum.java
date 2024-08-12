package vargas.stiven.iteriaAPI.entity;

import lombok.Getter;

@Getter
public enum EstadoEnum {

    INACTIVO(0), ACTIVO(1);

    int statusId;

    private EstadoEnum(int i) {
    }

}

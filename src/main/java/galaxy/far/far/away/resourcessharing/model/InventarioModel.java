package galaxy.far.far.away.resourcessharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class InventarioModel {

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private int quantidade;
}

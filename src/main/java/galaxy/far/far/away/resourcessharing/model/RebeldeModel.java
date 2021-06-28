package galaxy.far.far.away.resourcessharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "RebeldeTable")
@NamedQueries({
        @NamedQuery(name = RebeldeModel.GET_INVENTARIO,
        query = "FROM RebeldeModel r"
        )
})
public class RebeldeModel {

    public static final String GET_INVENTARIO = "RebeldeModel:GetInventario";

    @Id
    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private String idade;

    @Column(name = "genero")
    private String genero;

    @Column(name = "localizacao")
    private LocalizacaoModel localizacao;

    @Column(name = "inventario")
    private List<InventarioModel> inventario;
}

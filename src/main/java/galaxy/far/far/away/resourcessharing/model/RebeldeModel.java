package galaxy.far.far.away.resourcessharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rebels")
@NamedQueries({
        @NamedQuery(name = RebeldeModel.FIND_ALL,
        query = "FROM RebeldeModel r"
        )
})
public class RebeldeModel {

    public static final String FIND_ALL = "RebeldeModel:FindAll";

    @Id
    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "genero")
    private String genero;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumns(value = {@JoinColumn(name = "nome_base", referencedColumnName = "nome_base")})
    private LocalizacaoModel localizacao;

    @Column(name = "arma")
    private int countArma;

    @Column(name = "municao")
    private int countMunicao;

    @Column(name = "agua")
    private int countAgua;

    @Column(name = "comida")
    private int countComida;

    @Column(name = "traidor")
    private int traitorCount = 0;

//    @Column(name = "inventario")
//    private List<InventarioModel> inventario;
}

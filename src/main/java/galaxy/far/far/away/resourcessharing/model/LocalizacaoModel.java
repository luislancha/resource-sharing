package galaxy.far.far.away.resourcessharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "localizacao")
public class LocalizacaoModel {

    @Id
    @Column(name = "nome_base")
    private String nome_base;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}

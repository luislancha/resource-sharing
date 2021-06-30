package galaxy.far.far.away.resourcessharing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class RebeldeDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("idade")
    private int idade;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("localizacao")
    private LocalizacaoDto localizacao;

    @JsonProperty("arma")
    private int countArma;

    @JsonProperty("municao")
    private int countMunicao;

    @JsonProperty("agua")
    private int countAgua;

    @JsonProperty("comida")
    private int countComida;

    @JsonIgnore
    private int traitorCount = 0;
}

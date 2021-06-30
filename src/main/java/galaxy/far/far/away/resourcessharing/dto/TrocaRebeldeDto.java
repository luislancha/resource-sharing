package galaxy.far.far.away.resourcessharing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrocaRebeldeDto {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("arma")
    private int countArma;

    @JsonProperty("municao")
    private int countMunicao;

    @JsonProperty("agua")
    private int countAgua;

    @JsonProperty("comida")
    private int countComida;
}

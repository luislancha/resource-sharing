package galaxy.far.far.away.resourcessharing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioDto {
    @JsonProperty("traidores")
    private int quantidadeTraidores;

    @JsonProperty("rebeldes")
    private int quantidadeRebeldes;

    @JsonProperty("arma")
    private int quantidadeMediaArma;

    @JsonProperty("municao")
    private int quantidadeMediaMunicao;

    @JsonProperty("agua")
    private int quantidadeMediaAgua;

    @JsonProperty("comida")
    private int quantidadeMediaComida;

    @JsonProperty("pontos_perdidos")
    private int pontosPerdidos;

}

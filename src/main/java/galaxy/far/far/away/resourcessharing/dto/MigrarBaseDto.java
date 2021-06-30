package galaxy.far.far.away.resourcessharing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MigrarBaseDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("localizacao")
    private LocalizacaoDto localizacaoDto;
}

package galaxy.far.far.away.resourcessharing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MensagemErro {

    @JsonProperty("mensagem")
    private String mensagem;
}

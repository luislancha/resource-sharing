package galaxy.far.far.away.resourcessharing.validation;

import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.dto.TrocaRebeldeDto;
import galaxy.far.far.away.resourcessharing.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RebeldeValidation {
    public void validate (RebeldeDto rebeldeDto) {

    }

    public void validate (List<TrocaRebeldeDto> trocaRebeldeDtoList) {
        if (trocaRebeldeDtoList.size() != 2) {
            throw new ValidationException();
        }
    }
}

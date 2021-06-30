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

    public void validateInventario (TrocaRebeldeDto trocaRebeldeDto, RebeldeDto rebeldeDto) {
        if (trocaRebeldeDto.getCountArma() > rebeldeDto.getCountArma() ||
                trocaRebeldeDto.getCountMunicao() > rebeldeDto.getCountMunicao() ||
                trocaRebeldeDto.getCountAgua() > rebeldeDto.getCountAgua() ||
                trocaRebeldeDto.getCountComida() > rebeldeDto.getCountComida()) {
            throw new ValidationException("Quantidade de itens nao disponivel no inventario");
        }
    }

    public void validateTroca (TrocaRebeldeDto firstTrocaRebeldeDto, TrocaRebeldeDto secondTrocaRebeldeDto) {
        int countFirstInventario = getSoma(firstTrocaRebeldeDto);
        int countSecondInventario = getSoma(secondTrocaRebeldeDto);

        if (countFirstInventario != countSecondInventario) {
            throw new ValidationException("Quantidade de pontos não valida");
        }
    }

    private int getSoma (TrocaRebeldeDto rebeldeDto) {
        return rebeldeDto.getCountArma() * 4
                + rebeldeDto.getCountMunicao() * 3
                + rebeldeDto.getCountAgua() * 2
                + rebeldeDto.getCountComida() * 1;
    }
}

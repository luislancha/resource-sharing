package galaxy.far.far.away.resourcessharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RebeldeDto {
    private String nome;
    private String idade;
    private String genero;
    private LocalizacaoDto localizacao;
    private List<InventarioDto> inventario;
}

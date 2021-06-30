package galaxy.far.far.away.resourcessharing.mapper;

import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.model.RebeldeModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LocalizacaoMapper.class})
public abstract class RebeldeMapper {
    public static final RebeldeMapper INSTANCE = Mappers.getMapper( RebeldeMapper.class );

    @Mapping(source= "nome", target= "nome")
    @Mapping(source= "idade", target= "idade")
    @Mapping(source= "genero", target= "genero")
    @Mapping(source= "localizacao", target= "localizacao")
    @Mapping(source= "countArma", target= "countArma")
    @Mapping(source= "countMunicao", target= "countMunicao")
    @Mapping(source= "countAgua", target= "countAgua")
    @Mapping(source= "countComida", target= "countComida")
    @Mapping(source= "traitorCount", target= "traitorCount")
    public abstract RebeldeDto mapFrom (RebeldeModel rebeldeModel);

    @Mapping(source= "nome", target= "nome")
    @Mapping(source= "idade", target= "idade")
    @Mapping(source= "genero", target= "genero")
    @Mapping(source= "localizacao", target= "localizacao")
    @Mapping(source= "countArma", target= "countArma")
    @Mapping(source= "countMunicao", target= "countMunicao")
    @Mapping(source= "countAgua", target= "countAgua")
    @Mapping(source= "countComida", target= "countComida")
    @Mapping(source= "traitorCount", target= "traitorCount")
    public abstract RebeldeModel mapFrom (RebeldeDto rebeldeDto);

    @IterableMapping(elementTargetType = RebeldeDto.class)
    public abstract List<RebeldeDto> toList (List<RebeldeModel> rebeldeModelList);
}

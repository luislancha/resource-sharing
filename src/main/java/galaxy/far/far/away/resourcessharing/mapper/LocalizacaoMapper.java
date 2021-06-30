package galaxy.far.far.away.resourcessharing.mapper;

import galaxy.far.far.away.resourcessharing.dto.LocalizacaoDto;
import galaxy.far.far.away.resourcessharing.model.LocalizacaoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LocalizacaoMapper {
    public static final LocalizacaoMapper INSTANCE = Mappers.getMapper( LocalizacaoMapper.class );

    @Mapping(source= "nome_base", target= "nome")
    @Mapping(source= "latitude", target= "latitude")
    @Mapping(source= "longitude", target= "longitude")
    public abstract LocalizacaoDto mapFrom (LocalizacaoModel localizacaoModel);

    @Mapping(source= "nome", target= "nome_base")
    @Mapping(source= "latitude", target= "latitude")
    @Mapping(source= "longitude", target= "longitude")
    public abstract LocalizacaoModel mapFrom (LocalizacaoDto localizacaoDto);
}

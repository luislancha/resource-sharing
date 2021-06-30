package galaxy.far.far.away.resourcessharing.repository;

import galaxy.far.far.away.resourcessharing.dto.LocalizacaoDto;
import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.exception.RebeldeNaoEncontradoException;
import galaxy.far.far.away.resourcessharing.model.LocalizacaoModel;
import galaxy.far.far.away.resourcessharing.model.RebeldeModel;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.assertj.core.api.ThrowableAssert.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class RebeldeRepositoryTest {

    @InjectMocks
    private RebeldeRepository rebeldeRepository;

    @Mock
    private EntityManager entityManager;

    private RebeldeDto rebeldeDto;
    private LocalizacaoDto localizacaoDto;
    private RebeldeModel rebeldeModel;
    private LocalizacaoModel localizacaoModel;

    private static final String nome = "Obi Wan Kinobi";
    private static final String genero = "Jedi";
    private static final int quantidadeArma = 1;
    private static final int quantidadeMunicao = 0;
    private static final int quantidadeAgua = 2;
    private static final int quantidadeComida = 3;
    private static final String nome_localizacao = "Aldebaran";
    private static final String latitude = "12W50E";
    private static final String longitude = "11C12E";

    @BeforeEach
    void setUp() {
        rebeldeDto = new RebeldeDto();
        rebeldeModel = new RebeldeModel();
        localizacaoDto = new LocalizacaoDto();
        localizacaoModel = new LocalizacaoModel();

        localizacaoDto.setNome(nome_localizacao);
        localizacaoDto.setLatitude(latitude);
        localizacaoDto.setLongitude(longitude);

        localizacaoModel.setNome_base(nome_localizacao);
        localizacaoModel.setLatitude(latitude);
        localizacaoModel.setLongitude(longitude);

        rebeldeDto.setNome(nome);
        rebeldeDto.setGenero(genero);
        rebeldeDto.setCountArma(quantidadeArma);
        rebeldeDto.setCountMunicao(quantidadeMunicao);
        rebeldeDto.setCountAgua(quantidadeAgua);
        rebeldeDto.setCountComida(quantidadeComida);
        rebeldeDto.setLocalizacao(localizacaoDto);

        rebeldeModel.setNome(nome);
        rebeldeModel.setGenero(genero);
        rebeldeModel.setCountArma(quantidadeArma);
        rebeldeModel.setCountMunicao(quantidadeMunicao);
        rebeldeModel.setCountAgua(quantidadeAgua);
        rebeldeModel.setCountComida(quantidadeComida);
        rebeldeModel.setLocalizacao(localizacaoModel);
    }

    @Test
    void testSaveRebeldeOk() {
        Mockito.doNothing().when(entityManager).persist(Mockito.any(RebeldeDto.class));

        final RebeldeDto rebeldeDto = rebeldeRepository.save(this.rebeldeDto);

        assertEquals(this.rebeldeDto.getNome(), rebeldeDto.getNome());
    }

    @Test
    void testSaveLocalizacaoOk() {
        Mockito.doNothing().when(entityManager).persist(Mockito.any(LocalizacaoDto.class));

        final LocalizacaoDto localizacaoDto = rebeldeRepository.save(this.localizacaoDto);

        assertEquals(this.localizacaoDto.getNome(), localizacaoDto.getNome());
    }

    @Test
    void testUpdateRebeldeOk() {
        Mockito.when(entityManager.merge(Mockito.any(RebeldeDto.class))).thenReturn(this.rebeldeDto);

        final RebeldeDto rebeldeDto = rebeldeRepository.update(this.rebeldeDto);

        assertEquals(this.rebeldeDto.getNome(), rebeldeDto.getNome());
    }

    @Test
    void testFindRebeldeOk() {
        TypedQuery mockedQuery = mock(TypedQuery.class);
        Mockito.when(mockedQuery.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getSingleResult()).thenReturn(this.rebeldeModel);
        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(mockedQuery);

        final RebeldeDto rebeldeDto = rebeldeRepository.findRebelde(this.rebeldeDto.getNome());

        assertEquals(this.rebeldeDto.getNome(), rebeldeDto.getNome());
    }

    @Test
    void testFindRebeldeThrowsNoResultException() {
        TypedQuery mockedQuery = mock(TypedQuery.class);
        Mockito.when(mockedQuery.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getSingleResult()).thenThrow(new NoResultException());
        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(mockedQuery);

        final RebeldeNaoEncontradoException rebeldeNaoEncontradoException = catchThrowableOfType(() -> rebeldeRepository.findRebelde(this.rebeldeDto.getNome()), RebeldeNaoEncontradoException.class);

        assertNotNull(rebeldeNaoEncontradoException);
    }

    @Test
    void testFindLocalizacaoOk() {
        Mockito.when(entityManager.find(Mockito.any(Class.class), Mockito.anyString())).thenReturn(localizacaoModel);

        final boolean localizacaoExists = rebeldeRepository.localizacaoExists(this.localizacaoDto.getNome());

        assertTrue(localizacaoExists);
    }

    @Test
    void testFindAllOk() {
        TypedQuery mockedQuery = mock(TypedQuery.class);
        Mockito.when(mockedQuery.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Arrays.asList(this.rebeldeModel));
        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(mockedQuery);

        final List<RebeldeDto> rebeldeDtoList = rebeldeRepository.findAll();

        assertEquals(1, rebeldeDtoList.size());
        assertEquals(this.rebeldeDto.getNome(), rebeldeDtoList.get(0).getNome());
    }

    @Test
    void testFindAllRebeldesOk() {
        TypedQuery mockedQuery = mock(TypedQuery.class);
        Mockito.when(mockedQuery.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Arrays.asList(this.rebeldeModel));
        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(mockedQuery);

        final List<RebeldeDto> rebeldeDtoList = rebeldeRepository.findAllRebeldes();

        assertEquals(1, rebeldeDtoList.size());
        assertEquals(this.rebeldeDto.getNome(), rebeldeDtoList.get(0).getNome());
    }

    @Test
    void testFindAllTraidoresOk() {
        TypedQuery mockedQuery = mock(TypedQuery.class);
        Mockito.when(mockedQuery.setParameter(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(Arrays.asList(this.rebeldeModel));
        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(mockedQuery);

        final List<RebeldeDto> rebeldeDtoList = rebeldeRepository.findAllTraidores();

        assertEquals(1, rebeldeDtoList.size());
        assertEquals(this.rebeldeDto.getNome(), rebeldeDtoList.get(0).getNome());
    }
}
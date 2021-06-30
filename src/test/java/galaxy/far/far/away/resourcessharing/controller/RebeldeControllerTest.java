package galaxy.far.far.away.resourcessharing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import galaxy.far.far.away.resourcessharing.dto.*;
import galaxy.far.far.away.resourcessharing.exception.RebeldeNaoEncontradoException;
import galaxy.far.far.away.resourcessharing.repository.RebeldeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RebeldeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RebeldeRepository rebeldeRepository;

    private RebeldeDto rebeldeDto;
    private MigrarBaseDto migrarBaseDto;
    private LocalizacaoDto localizacaoDto;
    private TrocaRebeldeDto trocaRebeldeDto;
    private TrocaRebeldeListDto trocaRebeldeListDto;

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
    public void setUp() {
        rebeldeDto = new RebeldeDto();
        migrarBaseDto = new MigrarBaseDto();
        localizacaoDto = new LocalizacaoDto();
        trocaRebeldeDto = new TrocaRebeldeDto();
        trocaRebeldeListDto = new TrocaRebeldeListDto();

        localizacaoDto.setNome(nome_localizacao);
        localizacaoDto.setLatitude(latitude);
        localizacaoDto.setLongitude(longitude);

        migrarBaseDto.setNome(nome);
        migrarBaseDto.setLocalizacaoDto(localizacaoDto);

        trocaRebeldeDto.setNome(nome);
        trocaRebeldeDto.setCountArma(quantidadeArma);
        trocaRebeldeDto.setCountMunicao(quantidadeMunicao);
        trocaRebeldeDto.setCountAgua(quantidadeAgua);
        trocaRebeldeDto.setCountComida(quantidadeComida);

        rebeldeDto.setNome(nome);
        rebeldeDto.setGenero(genero);
        rebeldeDto.setCountArma(quantidadeArma);
        rebeldeDto.setCountMunicao(quantidadeMunicao);
        rebeldeDto.setCountAgua(quantidadeAgua);
        rebeldeDto.setCountComida(quantidadeComida);
        rebeldeDto.setLocalizacao(localizacaoDto);

        trocaRebeldeListDto.setRebeldes(Arrays.asList(trocaRebeldeDto, trocaRebeldeDto));
    }

    @Test
    public void testJoinOk() throws Exception {
        when(rebeldeRepository.findRebelde(rebeldeDto.getNome())).thenThrow(new RebeldeNaoEncontradoException());

        this.mockMvc.perform(post("http://localhost:8080/resources-sharing/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rebeldeDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testJoinNok() throws Exception {
        when(rebeldeRepository.findRebelde(rebeldeDto.getNome())).thenReturn(rebeldeDto);

        this.mockMvc.perform(post("http://localhost:8080/resources-sharing/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rebeldeDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testUpdateOk() throws Exception {
        when(rebeldeRepository.findRebelde(rebeldeDto.getNome())).thenReturn(rebeldeDto);

        this.mockMvc.perform(patch("http://localhost:8080/resources-sharing/migrate-base")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(migrarBaseDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testReportOk() throws Exception {
        when(rebeldeRepository.findRebelde(rebeldeDto.getNome())).thenReturn(rebeldeDto);

        this.mockMvc.perform(patch("http://localhost:8080/resources-sharing/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(rebeldeDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testTradeOk() throws Exception {
        when(rebeldeRepository.findRebelde(rebeldeDto.getNome())).thenReturn(rebeldeDto);

        this.mockMvc.perform(put("http://localhost:8080/resources-sharing/trade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(trocaRebeldeListDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReportOk() throws Exception {
        when(rebeldeRepository.findAll()).thenReturn(Arrays.asList(rebeldeDto));
        when(rebeldeRepository.findAllRebeldes()).thenReturn(Arrays.asList(rebeldeDto));
        when(rebeldeRepository.findAllTraidores()).thenReturn(Arrays.asList());

        MvcResult result = this.mockMvc.perform(get("http://localhost:8080/resources-sharing/reports"))
                .andExpect(status().isOk())
                .andReturn();

        final RelatorioDto relatorioDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), RelatorioDto.class);

        assertEquals(1, relatorioDto.getQuantidadeRebeldes());
        assertEquals(0, relatorioDto.getQuantidadeTraidores());
    }
}
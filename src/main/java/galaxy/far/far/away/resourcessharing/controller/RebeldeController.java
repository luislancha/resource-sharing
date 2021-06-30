package galaxy.far.far.away.resourcessharing.controller;

import galaxy.far.far.away.resourcessharing.dto.*;
import galaxy.far.far.away.resourcessharing.exception.RebeldeNaoEncontradoException;
import galaxy.far.far.away.resourcessharing.exception.ValidationException;
import galaxy.far.far.away.resourcessharing.repository.RebeldeRepository;
import galaxy.far.far.away.resourcessharing.validation.RebeldeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("resources-sharing")
public class RebeldeController {

    @Autowired
    private RebeldeValidation rebeldeValidation;

    @Autowired
    private RebeldeRepository rebeldeRepository;

    @PostMapping("/join")
    public ResponseEntity create (@RequestBody RebeldeDto rebeldeDto) {
//        rebeldeValidation.validate(rebeldeDto);


        if (!rebeldeRepository.localizacaoExists(rebeldeDto.getLocalizacao().getNome())) {
            rebeldeRepository.save(rebeldeDto.getLocalizacao());
        }
        try {
            rebeldeRepository.findRebelde(rebeldeDto.getNome());
            return ResponseEntity.unprocessableEntity().build();
        } catch (RebeldeNaoEncontradoException e) {
            rebeldeRepository.save(rebeldeDto);
            return ResponseEntity.created(URI.create("/join")).build();
        }
    }

    @PatchMapping("/migrate-base")
    public ResponseEntity update (@RequestBody MigrarBaseDto migrarBaseDto) {
//        rebeldeValidation.validate(rebeldeDto);

        try {
            RebeldeDto rebeldeDto = rebeldeRepository.findRebelde(migrarBaseDto.getNome());
            rebeldeDto.setLocalizacao(migrarBaseDto.getLocalizacaoDto());

            if (!rebeldeRepository.localizacaoExists(migrarBaseDto.getLocalizacaoDto().getNome())) {
                rebeldeRepository.save(rebeldeDto.getLocalizacao());
            }

            rebeldeRepository.update(rebeldeDto);

            return ResponseEntity.ok().build();
        } catch (RebeldeNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/report")
    public ResponseEntity report (@RequestBody RebeldeDto rebeldeDtoRequest) {

        try {
            RebeldeDto rebeldeDto = rebeldeRepository.findRebelde(rebeldeDtoRequest.getNome());
            rebeldeDto.setTraitorCount(rebeldeDto.getTraitorCount() + 1);
            rebeldeRepository.update(rebeldeDto);

            return ResponseEntity.ok().build();
        } catch (RebeldeNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/trade")
    public ResponseEntity trade (@RequestBody TrocaRebeldeListDto rebeldeDtoList) {

        try {
            rebeldeValidation.validate(rebeldeDtoList.getRebeldes());

            final TrocaRebeldeDto firstTrocaRebeldeDto = rebeldeDtoList.getRebeldes().get(0);
            final TrocaRebeldeDto secondTrocaRebeldeDto = rebeldeDtoList.getRebeldes().get(1);

            rebeldeValidation.validateTroca(firstTrocaRebeldeDto, secondTrocaRebeldeDto);


            RebeldeDto firstRebeldeDto = rebeldeRepository.findRebelde(firstTrocaRebeldeDto.getNome());
            RebeldeDto secondRebeldeDto = rebeldeRepository.findRebelde(secondTrocaRebeldeDto.getNome());

            rebeldeValidation.validateInventario(firstTrocaRebeldeDto, firstRebeldeDto);
            rebeldeValidation.validateInventario(secondTrocaRebeldeDto, secondRebeldeDto);

            firstRebeldeDto = atualizaInventario(firstTrocaRebeldeDto, secondTrocaRebeldeDto, firstRebeldeDto);
            secondRebeldeDto = atualizaInventario(secondTrocaRebeldeDto, firstTrocaRebeldeDto, secondRebeldeDto);

            rebeldeRepository.update(firstRebeldeDto);
            rebeldeRepository.update(secondRebeldeDto);

            return ResponseEntity.ok().build();
        } catch (RebeldeNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(new MensagemErro(e.getMensagem()));
        }
    }

    @GetMapping("/reports")
    public ResponseEntity getReport () {
        RelatorioDto relatorioDto = new RelatorioDto();
        final List<RebeldeDto> totalList = rebeldeRepository.findAll();
        final List<RebeldeDto> rebeldesList = rebeldeRepository.findAllRebeldes();
        final List<RebeldeDto> traidoresList = rebeldeRepository.findAllTraidores();

        final long total = totalList.stream().count();
        final long totalRebeldes = rebeldesList.stream().count();
        final long totalTraidores = traidoresList.stream().count();

        relatorioDto.setQuantidadeRebeldes((totalRebeldes)/total);
        relatorioDto.setQuantidadeTraidores(totalTraidores/total);

        relatorioDto.setQuantidadeMediaArma(rebeldesList.stream().mapToInt(RebeldeDto::getCountArma).sum() / (int) total);
        relatorioDto.setQuantidadeMediaMunicao(rebeldesList.stream().mapToInt(RebeldeDto::getCountMunicao).sum() / (int) total);
        relatorioDto.setQuantidadeMediaAgua(rebeldesList.stream().mapToInt(RebeldeDto::getCountAgua).sum() / (int) total);
        relatorioDto.setQuantidadeMediaComida(rebeldesList.stream().mapToInt(RebeldeDto::getCountComida).sum() / (int) total);

        relatorioDto.setPontosPerdidos(
                (traidoresList.stream().mapToInt(RebeldeDto::getCountComida).sum() * 4 +
                        traidoresList.stream().mapToInt(RebeldeDto::getCountComida).sum() * 3 +
                        traidoresList.stream().mapToInt(RebeldeDto::getCountComida).sum() * 2+
                        traidoresList.stream().mapToInt(RebeldeDto::getCountComida).sum() * 1));

        return ResponseEntity.ok().body(relatorioDto);
    }

    private RebeldeDto atualizaInventario (final TrocaRebeldeDto firstTrocaRebeldeDto, final TrocaRebeldeDto secondTrocaRebeldeDto, RebeldeDto rebeldeDto) {
        rebeldeDto.setCountArma(rebeldeDto.getCountArma() - firstTrocaRebeldeDto.getCountArma() + secondTrocaRebeldeDto.getCountArma());
        rebeldeDto.setCountMunicao(rebeldeDto.getCountMunicao() - firstTrocaRebeldeDto.getCountMunicao() + secondTrocaRebeldeDto.getCountMunicao());
        rebeldeDto.setCountAgua(rebeldeDto.getCountAgua() - firstTrocaRebeldeDto.getCountAgua() + secondTrocaRebeldeDto.getCountAgua());
        rebeldeDto.setCountComida(rebeldeDto.getCountComida() - firstTrocaRebeldeDto.getCountComida() + secondTrocaRebeldeDto.getCountComida());

        return rebeldeDto;
    }
}

package galaxy.far.far.away.resourcessharing.controller;

import galaxy.far.far.away.resourcessharing.dto.MigrarBaseDto;
import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.dto.RelatorioDto;
import galaxy.far.far.away.resourcessharing.dto.TrocaRebeldeDto;
import galaxy.far.far.away.resourcessharing.model.RebeldeModel;
import galaxy.far.far.away.resourcessharing.repository.RebeldeRepository;
import galaxy.far.far.away.resourcessharing.validation.RebeldeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


        if (rebeldeRepository.localizacaoExists(rebeldeDto.getLocalizacao().getNome())) {
            rebeldeRepository.save(rebeldeDto.getLocalizacao());
        }
        rebeldeRepository.save(rebeldeDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/migrate-base")
    public ResponseEntity update (@RequestBody MigrarBaseDto migrarBaseDto) {
//        rebeldeValidation.validate(rebeldeDto);

        RebeldeDto rebeldeDto = rebeldeRepository.findRebelde(migrarBaseDto.getNome());

        rebeldeDto.setLocalizacao(migrarBaseDto.getLocalizacaoDto());

        if (rebeldeRepository.localizacaoExists(migrarBaseDto.getLocalizacaoDto().getNome())) {
            rebeldeRepository.save(rebeldeDto.getLocalizacao());
        }

        rebeldeRepository.update(rebeldeDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/report")
    public ResponseEntity report (@RequestBody String nome) {

        RebeldeDto rebeldeDto = rebeldeRepository.findRebelde(nome);
        rebeldeDto.setTraitorCount(rebeldeDto.getTraitorCount() + 1);
        rebeldeRepository.save(rebeldeDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/trade")
    public ResponseEntity trade (@RequestBody List<TrocaRebeldeDto> rebeldeDtoList) {
        rebeldeValidation.validate(rebeldeDtoList);

        RebeldeDto firstRebeldeDto = rebeldeRepository.findRebelde(rebeldeDtoList.get(0).getNome());
        RebeldeDto secondRebeldeDto = rebeldeRepository.findRebelde(rebeldeDtoList.get(1).getNome());
//
//        rebeldeRepository.update(rebeldeDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/reports")
    public ResponseEntity getReport () {
        RelatorioDto relatorioDto = new RelatorioDto();
        final List<RebeldeDto> rebeldeDtoList = rebeldeRepository.findAll();

        return ResponseEntity.ok().body(relatorioDto);
    }
}

package galaxy.far.far.away.resourcessharing.controller;

import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.repository.RebeldeRepository;
import galaxy.far.far.away.resourcessharing.validation.RebeldeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RebeldeController {

    @Autowired
    private RebeldeValidation rebeldeValidation;

    @Autowired
    private RebeldeRepository rebeldeRepository;

    @PostMapping
    public ResponseEntity create (@RequestBody RebeldeDto rebeldeDto) {
        rebeldeValidation.validate(rebeldeDto);

        rebeldeRepository.save(rebeldeDto);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity update () {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity report () {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity trade () {
        return ResponseEntity.ok().build();
    }
}

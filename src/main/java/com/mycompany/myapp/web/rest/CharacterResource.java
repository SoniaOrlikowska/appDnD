package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Character;
import com.mycompany.myapp.repository.CharacterRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing characters.
 */
@RestController
@RequestMapping("/api")
public class CharacterResource {

    private final Logger log = LoggerFactory.getLogger(CharacterResource.class);

    private static final String ENTITY_NAME = "character";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CharacterRepository characterRepository;

    public CharacterResource(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @PostMapping("/characters")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) throws URISyntaxException {
        log.debug("REST request to save Character : {}", character);
        if (character.getId() != null) {
            throw new BadRequestAlertException("A new character cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Character result = characterRepository.save(character);
        return ResponseEntity.created(new URI("/api/characters/" + result.getId())).body(result);
    }

    /**
     * PUT: Updates an existing character.
     */
    @PutMapping("/characters/{id}")
    public ResponseEntity<Character> updateCharacter(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Character character
    ) throws URISyntaxException {
        log.debug("REST request to update Character : {}, {}", id, character);
        if (character.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, character.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!characterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Character result = characterRepository.save(character);
        return ResponseEntity.ok().body(result);
    }

    /**
     * { GET  /characters} : get all the characters.
     *
     */
    @GetMapping("/characters")
    public List<Character> getAllCharacters() {
        log.debug("REST request to get all Characters");
        return characterRepository.findAll();
    }

    /**
     * { GET  /characters/:id} : get the "id" character.
     */
    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable String id) {
        log.debug("REST request to get Character : {}", id);
        Optional<Character> character = characterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(character);
    }

    /**
     *  DELETE  /characters/:id} : delete the "id" character.
     */
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        log.debug("REST request to delete Character : {}", id);
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}

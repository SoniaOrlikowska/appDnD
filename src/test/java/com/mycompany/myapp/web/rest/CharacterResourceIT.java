package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Character;
import com.mycompany.myapp.repository.CharacterRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link CharacterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CharacterResourceIT {

    private static final String DEFAULT_PLAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PLAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_CLASS = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_ARMOUR_CLASS = 1;
    private static final Integer UPDATED_ARMOUR_CLASS = 2;

    private static final Integer DEFAULT_INITIATIVE = 1;
    private static final Integer UPDATED_INITIATIVE = 2;

    private static final Integer DEFAULT_HIT_POINTS = 1;
    private static final Integer UPDATED_HIT_POINTS = 2;

    private static final Integer DEFAULT_STRENGTH = 1;
    private static final Integer UPDATED_STRENGTH = 2;

    private static final Integer DEFAULT_DEXTERITY = 1;
    private static final Integer UPDATED_DEXTERITY = 2;

    private static final Integer DEFAULT_CONSTITUTION = 1;
    private static final Integer UPDATED_CONSTITUTION = 2;

    private static final Integer DEFAULT_INTELLIGENCE = 1;
    private static final Integer UPDATED_INTELLIGENCE = 2;

    private static final Integer DEFAULT_WISDOM = 1;
    private static final Integer UPDATED_WISDOM = 2;

    private static final Integer DEFAULT_CHARISMA = 1;
    private static final Integer UPDATED_CHARISMA = 2;

    private static final String DEFAULT_PERSONALITY = "AAAAAAAAAA";
    private static final String UPDATED_PERSONALITY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/characters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MockMvc restCharacterMockMvc;

    private Character character;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Character createEntity() {
        Character character = new Character()
            .playerName(DEFAULT_PLAYER_NAME)
            .characterName(DEFAULT_CHARACTER_NAME)
            .characterClass(DEFAULT_CHARACTER_CLASS)
            .level(DEFAULT_LEVEL)
            .armourClass(DEFAULT_ARMOUR_CLASS)
            .initiative(DEFAULT_INITIATIVE)
            .hitPoints(DEFAULT_HIT_POINTS)
            .strength(DEFAULT_STRENGTH)
            .dexterity(DEFAULT_DEXTERITY)
            .constitution(DEFAULT_CONSTITUTION)
            .intelligence(DEFAULT_INTELLIGENCE)
            .wisdom(DEFAULT_WISDOM)
            .charisma(DEFAULT_CHARISMA)
            .personality(DEFAULT_PERSONALITY);
        return character;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Character createUpdatedEntity() {
        Character character = new Character()
            .playerName(UPDATED_PLAYER_NAME)
            .characterName(UPDATED_CHARACTER_NAME)
            .characterClass(UPDATED_CHARACTER_CLASS)
            .level(UPDATED_LEVEL)
            .armourClass(UPDATED_ARMOUR_CLASS)
            .initiative(UPDATED_INITIATIVE)
            .hitPoints(UPDATED_HIT_POINTS)
            .strength(UPDATED_STRENGTH)
            .dexterity(UPDATED_DEXTERITY)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .wisdom(UPDATED_WISDOM)
            .charisma(UPDATED_CHARISMA)
            .personality(UPDATED_PERSONALITY);
        return character;
    }

    @BeforeEach
    public void initTest() {
        characterRepository.deleteAll();
        character = createEntity();
    }

    @Test
    void createCharacter() throws Exception {
        int databaseSizeBeforeCreate = characterRepository.findAll().size();
        // Create the Character
        restCharacterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isCreated());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeCreate + 1);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getPlayerName()).isEqualTo(DEFAULT_PLAYER_NAME);
        assertThat(testCharacter.getCharacterName()).isEqualTo(DEFAULT_CHARACTER_NAME);
        assertThat(testCharacter.getCharacterClass()).isEqualTo(DEFAULT_CHARACTER_CLASS);
        assertThat(testCharacter.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCharacter.getArmourClass()).isEqualTo(DEFAULT_ARMOUR_CLASS);
        assertThat(testCharacter.getInitiative()).isEqualTo(DEFAULT_INITIATIVE);
        assertThat(testCharacter.getHitPoints()).isEqualTo(DEFAULT_HIT_POINTS);
        assertThat(testCharacter.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testCharacter.getDexterity()).isEqualTo(DEFAULT_DEXTERITY);
        assertThat(testCharacter.getConstitution()).isEqualTo(DEFAULT_CONSTITUTION);
        assertThat(testCharacter.getIntelligence()).isEqualTo(DEFAULT_INTELLIGENCE);
        assertThat(testCharacter.getWisdom()).isEqualTo(DEFAULT_WISDOM);
        assertThat(testCharacter.getCharisma()).isEqualTo(DEFAULT_CHARISMA);
        assertThat(testCharacter.getPersonality()).isEqualTo(DEFAULT_PERSONALITY);
    }

    @Test
    void createCharacterWithExistingId() throws Exception {
        // Create the Character with an existing ID
        character.setId("existing_id");

        int databaseSizeBeforeCreate = characterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCharacterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCharacters() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        // Get all the characterList
        restCharacterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(character.getId())))
            .andExpect(jsonPath("$.[*].playerName").value(hasItem(DEFAULT_PLAYER_NAME)))
            .andExpect(jsonPath("$.[*].characterName").value(hasItem(DEFAULT_CHARACTER_NAME)))
            .andExpect(jsonPath("$.[*].characterClass").value(hasItem(DEFAULT_CHARACTER_CLASS)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].armourClass").value(hasItem(DEFAULT_ARMOUR_CLASS)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].hitPoints").value(hasItem(DEFAULT_HIT_POINTS)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].dexterity").value(hasItem(DEFAULT_DEXTERITY)))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].wisdom").value(hasItem(DEFAULT_WISDOM)))
            .andExpect(jsonPath("$.[*].charisma").value(hasItem(DEFAULT_CHARISMA)))
            .andExpect(jsonPath("$.[*].personality").value(hasItem(DEFAULT_PERSONALITY.toString())));
    }

    @Test
    void getCharacter() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        // Get the character
        restCharacterMockMvc
            .perform(get(ENTITY_API_URL_ID, character.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(character.getId()))
            .andExpect(jsonPath("$.playerName").value(DEFAULT_PLAYER_NAME))
            .andExpect(jsonPath("$.characterName").value(DEFAULT_CHARACTER_NAME))
            .andExpect(jsonPath("$.characterClass").value(DEFAULT_CHARACTER_CLASS))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.armourClass").value(DEFAULT_ARMOUR_CLASS))
            .andExpect(jsonPath("$.initiative").value(DEFAULT_INITIATIVE))
            .andExpect(jsonPath("$.hitPoints").value(DEFAULT_HIT_POINTS))
            .andExpect(jsonPath("$.strength").value(DEFAULT_STRENGTH))
            .andExpect(jsonPath("$.dexterity").value(DEFAULT_DEXTERITY))
            .andExpect(jsonPath("$.constitution").value(DEFAULT_CONSTITUTION))
            .andExpect(jsonPath("$.intelligence").value(DEFAULT_INTELLIGENCE))
            .andExpect(jsonPath("$.wisdom").value(DEFAULT_WISDOM))
            .andExpect(jsonPath("$.charisma").value(DEFAULT_CHARISMA))
            .andExpect(jsonPath("$.personality").value(DEFAULT_PERSONALITY.toString()));
    }

    @Test
    void getNonExistingCharacter() throws Exception {
        // Get the character
        restCharacterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewCharacter() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character
        Character updatedCharacter = characterRepository.findById(character.getId()).get();
        updatedCharacter
            .playerName(UPDATED_PLAYER_NAME)
            .characterName(UPDATED_CHARACTER_NAME)
            .characterClass(UPDATED_CHARACTER_CLASS)
            .level(UPDATED_LEVEL)
            .armourClass(UPDATED_ARMOUR_CLASS)
            .initiative(UPDATED_INITIATIVE)
            .hitPoints(UPDATED_HIT_POINTS)
            .strength(UPDATED_STRENGTH)
            .dexterity(UPDATED_DEXTERITY)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .wisdom(UPDATED_WISDOM)
            .charisma(UPDATED_CHARISMA)
            .personality(UPDATED_PERSONALITY);

        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCharacter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getPlayerName()).isEqualTo(UPDATED_PLAYER_NAME);
        assertThat(testCharacter.getCharacterName()).isEqualTo(UPDATED_CHARACTER_NAME);
        assertThat(testCharacter.getCharacterClass()).isEqualTo(UPDATED_CHARACTER_CLASS);
        assertThat(testCharacter.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCharacter.getArmourClass()).isEqualTo(UPDATED_ARMOUR_CLASS);
        assertThat(testCharacter.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testCharacter.getHitPoints()).isEqualTo(UPDATED_HIT_POINTS);
        assertThat(testCharacter.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacter.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacter.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getWisdom()).isEqualTo(UPDATED_WISDOM);
        assertThat(testCharacter.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacter.getPersonality()).isEqualTo(UPDATED_PERSONALITY);
    }

    @Test
    void putNonExistingCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, character.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCharacterWithPatch() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character using partial update
        Character partialUpdatedCharacter = new Character();
        partialUpdatedCharacter.setId(character.getId());

        partialUpdatedCharacter
            .characterClass(UPDATED_CHARACTER_CLASS)
            .initiative(UPDATED_INITIATIVE)
            .strength(UPDATED_STRENGTH)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .charisma(UPDATED_CHARISMA);

        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharacter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getPlayerName()).isEqualTo(DEFAULT_PLAYER_NAME);
        assertThat(testCharacter.getCharacterName()).isEqualTo(DEFAULT_CHARACTER_NAME);
        assertThat(testCharacter.getCharacterClass()).isEqualTo(UPDATED_CHARACTER_CLASS);
        assertThat(testCharacter.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCharacter.getArmourClass()).isEqualTo(DEFAULT_ARMOUR_CLASS);
        assertThat(testCharacter.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testCharacter.getHitPoints()).isEqualTo(DEFAULT_HIT_POINTS);
        assertThat(testCharacter.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacter.getDexterity()).isEqualTo(DEFAULT_DEXTERITY);
        assertThat(testCharacter.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getWisdom()).isEqualTo(DEFAULT_WISDOM);
        assertThat(testCharacter.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacter.getPersonality()).isEqualTo(DEFAULT_PERSONALITY);
    }

    @Test
    void fullUpdateCharacterWithPatch() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character using partial update
        Character partialUpdatedCharacter = new Character();
        partialUpdatedCharacter.setId(character.getId());

        partialUpdatedCharacter
            .playerName(UPDATED_PLAYER_NAME)
            .characterName(UPDATED_CHARACTER_NAME)
            .characterClass(UPDATED_CHARACTER_CLASS)
            .level(UPDATED_LEVEL)
            .armourClass(UPDATED_ARMOUR_CLASS)
            .initiative(UPDATED_INITIATIVE)
            .hitPoints(UPDATED_HIT_POINTS)
            .strength(UPDATED_STRENGTH)
            .dexterity(UPDATED_DEXTERITY)
            .constitution(UPDATED_CONSTITUTION)
            .intelligence(UPDATED_INTELLIGENCE)
            .wisdom(UPDATED_WISDOM)
            .charisma(UPDATED_CHARISMA)
            .personality(UPDATED_PERSONALITY);

        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharacter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getPlayerName()).isEqualTo(UPDATED_PLAYER_NAME);
        assertThat(testCharacter.getCharacterName()).isEqualTo(UPDATED_CHARACTER_NAME);
        assertThat(testCharacter.getCharacterClass()).isEqualTo(UPDATED_CHARACTER_CLASS);
        assertThat(testCharacter.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCharacter.getArmourClass()).isEqualTo(UPDATED_ARMOUR_CLASS);
        assertThat(testCharacter.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testCharacter.getHitPoints()).isEqualTo(UPDATED_HIT_POINTS);
        assertThat(testCharacter.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacter.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacter.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getWisdom()).isEqualTo(UPDATED_WISDOM);
        assertThat(testCharacter.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacter.getPersonality()).isEqualTo(UPDATED_PERSONALITY);
    }

    @Test
    void patchNonExistingCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, character.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCharacter() throws Exception {
        // Initialize the database
        characterRepository.save(character);

        int databaseSizeBeforeDelete = characterRepository.findAll().size();

        // Delete the character
        restCharacterMockMvc
            .perform(delete(ENTITY_API_URL_ID, character.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

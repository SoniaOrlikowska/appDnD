package com.mycompany.myapp.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Character.
 */
@Document(collection = "character")
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("player_name")
    private String playerName;

    @Field("character_name")
    private String characterName;

    @Field("character_class")
    private String characterClass;

    @Field("level")
    private Integer level;

    @Field("armour_class")
    private Integer armourClass;

    @Field("initiative")
    private Integer initiative;

    @Field("hit_points")
    private Integer hitPoints;

    @Field("strength")
    private Integer strength;

    @Field("dexterity")
    private Integer dexterity;

    @Field("constitution")
    private Integer constitution;

    @Field("intelligence")
    private Integer intelligence;

    @Field("wisdom")
    private Integer wisdom;

    @Field("charisma")
    private Integer charisma;

    @Field("personality")
    private String personality;

    @DBRef
    @Field("user")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character id(String id) {
        this.id = id;
        return this;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Character playerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public Character characterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterClass() {
        return this.characterClass;
    }

    public Character characterClass(String characterClass) {
        this.characterClass = characterClass;
        return this;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Character level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getArmourClass() {
        return this.armourClass;
    }

    public Character armourClass(Integer armourClass) {
        this.armourClass = armourClass;
        return this;
    }

    public void setArmourClass(Integer armourClass) {
        this.armourClass = armourClass;
    }

    public Integer getInitiative() {
        return this.initiative;
    }

    public Character initiative(Integer initiative) {
        this.initiative = initiative;
        return this;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getHitPoints() {
        return this.hitPoints;
    }

    public Character hitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getStrength() {
        return this.strength;
    }

    public Character strength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return this.dexterity;
    }

    public Character dexterity(Integer dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getConstitution() {
        return this.constitution;
    }

    public Character constitution(Integer constitution) {
        this.constitution = constitution;
        return this;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getIntelligence() {
        return this.intelligence;
    }

    public Character intelligence(Integer intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getWisdom() {
        return this.wisdom;
    }

    public Character wisdom(Integer wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCharisma() {
        return this.charisma;
    }

    public Character charisma(Integer charisma) {
        this.charisma = charisma;
        return this;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public String getPersonality() {
        return this.personality;
    }

    public Character personality(String personality) {
        this.personality = personality;
        return this;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public User getUser() {
        return this.user;
    }

    public Character user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Character)) {
            return false;
        }
        return id != null && id.equals(((Character) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Character{" +
            "id=" + getId() +
            ", playerName='" + getPlayerName() + "'" +
            ", characterName='" + getCharacterName() + "'" +
            ", characterClass='" + getCharacterClass() + "'" +
            ", level=" + getLevel() +
            ", armourClass=" + getArmourClass() +
            ", initiative=" + getInitiative() +
            ", hitPoints=" + getHitPoints() +
            ", strength=" + getStrength() +
            ", dexterity=" + getDexterity() +
            ", constitution=" + getConstitution() +
            ", intelligence=" + getIntelligence() +
            ", wisdom=" + getWisdom() +
            ", charisma=" + getCharisma() +
            ", personality='" + getPersonality() + "'" +
            "}";
    }
}

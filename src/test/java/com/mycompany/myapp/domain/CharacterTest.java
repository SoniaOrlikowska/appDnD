package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CharacterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Character.class);
        Character character1 = new Character();
        character1.setId("id1");
        Character character2 = new Character();
        character2.setId(character1.getId());
        assertThat(character1).isEqualTo(character2);
        character2.setId("id2");
        assertThat(character1).isNotEqualTo(character2);
        character1.setId(null);
        assertThat(character1).isNotEqualTo(character2);
    }
}

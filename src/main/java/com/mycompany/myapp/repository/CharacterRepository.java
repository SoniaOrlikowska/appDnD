package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Character;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Character entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {}

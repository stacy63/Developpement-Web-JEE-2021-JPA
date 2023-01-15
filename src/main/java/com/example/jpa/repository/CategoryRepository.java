package com.example.jpa.repository;

import com.example.jpa.model.Category;
import org.springframework.data.repository.CrudRepository;

//Grâce à Spring, il n'est pas nécessaire d'écrire plus. Les méthodes vont être implémentées automatiquement.
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);
}

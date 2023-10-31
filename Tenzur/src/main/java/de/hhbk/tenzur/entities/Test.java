package de.hhbk.tenzur.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author lars
 */
@Entity
public class Test {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

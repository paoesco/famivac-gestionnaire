package fr.famivac.gestionnaire.commons.entity;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/** @author paoesco */
@MappedSuperclass
public abstract class Entity {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Entity other = (Entity) obj;
    return Objects.equals(id, other.id);
  }
}

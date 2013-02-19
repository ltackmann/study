package org.randompage;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "entity")
public class MyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String someProperty;

    public String getSomeProperty() {
        return someProperty;
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }

    public MyEntity() {
    }

    public MyEntity(String someProperty) {
        this.someProperty = someProperty;
    }

    @Override
    public String toString() {
        return "jpatest.entities.MyEntity[id=" + id + "]";
    }
}
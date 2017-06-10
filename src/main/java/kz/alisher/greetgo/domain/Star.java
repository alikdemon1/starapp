package kz.alisher.greetgo.domain;

import java.io.Serializable;

/**
 * Created by alisher
 */
public class Star implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double coordX;
    private Double coordY;
    private Dictionary dictionary;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                ", dictionary=" + dictionary +
                ", user=" + user +
                '}';
    }
}

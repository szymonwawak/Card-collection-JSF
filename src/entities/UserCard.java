package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_card", schema = "cardcollection", catalog = "")
public class UserCard {
    private Integer id;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCard userCard = (UserCard) o;
        return Objects.equals(id, userCard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

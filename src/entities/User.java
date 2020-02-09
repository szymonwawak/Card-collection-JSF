package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String rememberToken;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer cardscraps;
    private Collection<Card> cards;
    private Collection<Role> roles;
    private Collection<CardProposition> cardPropositions;
    private Collection<Stats> stats;

    public User() {

    }

    public User(String name, String email, String password, Collection<Role> roles, Collection<Card> cards) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.cardscraps = 5000;
        this.roles = roles;
        this.cards = cards;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 191)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 191)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 191)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "remember_token", nullable = true, length = 100)
    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "cardscraps", nullable = false)
    public Integer getCardscraps() {
        return cardscraps;
    }

    public void setCardscraps(Integer cardscraps) {
        this.cardscraps = cardscraps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(rememberToken, user.rememberToken) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt) &&
                Objects.equals(cardscraps, user.cardscraps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, rememberToken, createdAt, updatedAt, cardscraps);
    }

    @ManyToMany
    @JoinTable(name = "user_card", catalog = "", schema = "cardcollection", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id", nullable = false))
    public Collection<Card> getCards() {
        return cards;
    }

    public void setCards(Collection<Card> cards) {
        this.cards = cards;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", catalog = "", schema = "cardcollection", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @OneToMany
    public Collection<CardProposition> getCardPropositions() {
        return cardPropositions;
    }

    public void setCardPropositions(Collection<CardProposition> cardPropositions) {
        this.cardPropositions = cardPropositions;
    }

    @OneToMany
    public Collection<Stats> getStats() {
        return stats;
    }

    public void setStats(Collection<Stats> stats) {
        this.stats = stats;
    }
}

package entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Stats {
    private Long id;
    private Double winPercentage;
    private Integer gamesPlayed;
    private Integer scrapsSpent;
    private Double hoursPlayed;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "win_percentage", nullable = false, precision = 0)
    public Double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(Double winPercentage) {
        this.winPercentage = winPercentage;
    }

    @Basic
    @Column(name = "games_played", nullable = false)
    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Basic
    @Column(name = "scraps_spent", nullable = false)
    public Integer getScrapsSpent() {
        return scrapsSpent;
    }

    public void setScrapsSpent(Integer scrapsSpent) {
        this.scrapsSpent = scrapsSpent;
    }

    @Basic
    @Column(name = "hours_played", nullable = false, precision = 0)
    public Double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(Double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return Objects.equals(id, stats.id) &&
                Objects.equals(winPercentage, stats.winPercentage) &&
                Objects.equals(gamesPlayed, stats.gamesPlayed) &&
                Objects.equals(scrapsSpent, stats.scrapsSpent) &&
                Objects.equals(hoursPlayed, stats.hoursPlayed) &&
                Objects.equals(createdAt, stats.createdAt) &&
                Objects.equals(updatedAt, stats.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winPercentage, gamesPlayed, scrapsSpent, hoursPlayed, createdAt, updatedAt);
    }
}

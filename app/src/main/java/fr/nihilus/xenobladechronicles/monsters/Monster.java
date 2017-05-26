package fr.nihilus.xenobladechronicles.monsters;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(tableName = "monster", indices = @Index("name"))
public class Monster {

    static final int LEVEL_DANGER = 5;
    static final int LEVEL_STRONG = 4;
    static final int LEVEL_EQUAL = 3;
    static final int LEVEL_WEAK = 2;
    static final int LEVEL_EASY = 1;
    static final int LEVEL_DEFEATED = 0;

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private int level = 5;
    private String name = "";

    @ColumnInfo(name = "area_code", typeAffinity = ColumnInfo.INTEGER)
    private Area area = Area.NONE;
    private String location;
    private String kind;

    @ColumnInfo(name = "is_defeated")
    private boolean isDefeated = false;

    /**
     * Calcule le niveau de danger de ce monstre par rapport au niveau du leader de l'équipe.
     * Si le monstre est vaincu, cette méthode retourne {@link #LEVEL_DEFEATED}.
     *
     * @return constante définissant le niveau de danger représenté par ce monstre
     */
    @DangerLevel
    int getDangerLevel(int playerLevel) {
        if (isDefeated) return LEVEL_DEFEATED;
        else {
            int levelDiff = level - playerLevel;
            if (levelDiff > 5) return LEVEL_DANGER;
            else if (levelDiff > 2) return LEVEL_STRONG;
            else if (levelDiff >= -2) return LEVEL_EQUAL;
            else if (levelDiff >= -5) return LEVEL_WEAK;
            else return LEVEL_EASY;
        }
    }

    @Override
    public String toString() {
        return "Monster{" + "id=" + id +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", location='" + location + '\'' +
                ", kind='" + kind + '\'' +
                ", isDefeated=" + isDefeated +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(@IntRange(from = 5, to = 120) int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(@NonNull Area area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LEVEL_DEFEATED, LEVEL_EASY, LEVEL_WEAK, LEVEL_EQUAL, LEVEL_STRONG, LEVEL_DANGER})
    @interface DangerLevel {
    }
}

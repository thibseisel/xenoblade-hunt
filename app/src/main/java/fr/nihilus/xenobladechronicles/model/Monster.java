package fr.nihilus.xenobladechronicles.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;

@Entity(tableName = "monster", indices = @Index("name"))
public class Monster implements Comparable<Monster> {

    public static final int LEVEL_DANGER = 5;
    public static final int LEVEL_STRONG = 4;
    public static final int LEVEL_EQUAL = 3;
    public static final int LEVEL_WEAK = 2;
    public static final int LEVEL_EASY = 1;
    public static final int LEVEL_DEFEATED = 0;

    public static final int ORDERING_NAME = 0;
    public static final int ORDERING_LEVEL = 1;
    public static final int ORDERING_AREA = 2;

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private int level = 5;
    private String name = "";

    @ColumnInfo(name = "area_code", typeAffinity = ColumnInfo.TEXT)
    private Area area;
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
    public int getDangerLevel(int playerLevel) {
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
        if (level < 5 || level > 120)
            throw new IllegalArgumentException("Level must be in 5..120");
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

    public void setLocation(@Nullable String location) {
        this.location = location;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(@Nullable String kind) {
        this.kind = kind;
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    @Override
    public int compareTo(@NonNull Monster o) {
        return ORDER_BY_NAME.compare(this, o);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LEVEL_DEFEATED, LEVEL_EASY, LEVEL_WEAK, LEVEL_EQUAL, LEVEL_STRONG, LEVEL_DANGER})
    public @interface DangerLevel {}

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ORDERING_NAME, ORDERING_LEVEL, ORDERING_AREA})
    public @interface Ordering {}

    private static final Comparator<Monster> ORDER_BY_NAME = new Comparator<Monster>() {
        @Override public int compare(Monster m1, Monster m2) {
            return m1.name.compareTo(m2.name);
        }
    };

    private static final Comparator<Monster> ORDER_BY_LEVEL = new Comparator<Monster>() {
        @Override public int compare(Monster m1, Monster m2) {
            return m1.level - m2.level;
        }
    };

    private static final Comparator<Monster> ORDER_BY_AREA = new Comparator<Monster>() {
        @Override
        public int compare(Monster m1, Monster m2) {
            int areaDiff = 100 * m1.area.compareTo(m2.area);
            int levelDiff = ORDER_BY_LEVEL.compare(m1, m2);
            return areaDiff + levelDiff;
        }
    };

    public static Comparator<Monster> comparator(@Ordering int orderStrategy) {
        switch (orderStrategy) {
            case ORDERING_NAME:
                return ORDER_BY_NAME;
            case ORDERING_LEVEL:
                return ORDER_BY_LEVEL;
            case ORDERING_AREA:
                return ORDER_BY_AREA;
            default:
                throw new IllegalArgumentException("Unhandled ordering");
        }
    }
}

package fr.nihilus.xenobladechronicles.datastore;

import android.arch.persistence.room.TypeConverter;

import fr.nihilus.xenobladechronicles.model.Area;

class Converters {

    @TypeConverter
    public static Area fromName(String value) {
        return value == null ? null : Area.valueOf(Area.class, value);
    }

    @TypeConverter
    public static String areaToName(Area area) {
        return area == null ? null : area.name();
    }
}

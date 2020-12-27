package fr.nihilus.xenobladechronicles.datastore

import androidx.room.TypeConverter
import fr.nihilus.xenobladechronicles.model.Area

internal class Converters {

    @TypeConverter
    fun fromName(areaCodeName: String?): Area? = areaCodeName?.let(Area::valueOf)

    @TypeConverter
    fun areaToName(area: Area?): String? = area?.name
}

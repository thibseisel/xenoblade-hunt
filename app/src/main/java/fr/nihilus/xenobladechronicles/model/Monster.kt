package fr.nihilus.xenobladechronicles.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "monster",
    indices = [Index("name")]
)
data class Monster(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "level")
    val level: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "kind")
    val kind: String,

    @ColumnInfo(name = "area_code", typeAffinity = ColumnInfo.TEXT)
    val area: Area,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "is_defeated")
    val isDefeated: Boolean = false
) {
    init {
        require(level in 5..120)
    }
}

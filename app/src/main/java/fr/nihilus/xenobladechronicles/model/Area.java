package fr.nihilus.xenobladechronicles.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import fr.nihilus.xenobladechronicles.R;

public enum Area {
    COLONY_9(R.string.area_colony_9),
    TEPHRA_CAVES(R.string.area_tephra_cave),
    BIONIS_LEG(R.string.area_bionis_leg),
    COLONY_6(R.string.area_colony_6),
    SATORL_MARSH(R.string.area_satorl_marsh),
    BIONIS_INTERIOR(R.string.area_bionis_interior),
    MAKNA_FOREST(R.string.area_makna_forest),
    ERYTH_SEA(R.string.area_eryth_sea),
    ALCAMOTH(R.string.area_alcamoth),
    HIGH_ENTIA_TOMB(R.string.area_high_entia_tomb),
    VALAK_MOUNTAIN(R.string.area_valak_mountain),
    SWORD_VALLEY(R.string.area_sword_valley),
    GALAHAD_FORTRESS(R.string.area_galahad_fortress),
    FALLEN_ARM(R.string.area_fallen_arm),
    MECHONIS_FIELD(R.string.area_mechonis_field),
    CENTRAL_FACTORY(R.string.area_central_factory),
    AGNIRATHA(R.string.area_agniratha),
    PRISON_ISLAND(R.string.area_prison_island);

    final @StringRes int nameId;

    Area(@StringRes int areaNameId) {
        nameId = areaNameId;
    }

    public String getName(@NonNull Context context) {
        return context.getString(nameId);
    }
}

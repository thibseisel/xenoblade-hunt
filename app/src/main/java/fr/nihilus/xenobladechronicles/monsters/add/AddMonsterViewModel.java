package fr.nihilus.xenobladechronicles.monsters.add;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import fr.nihilus.xenobladechronicles.model.Area;
import fr.nihilus.xenobladechronicles.model.Monster;
import fr.nihilus.xenobladechronicles.monsters.MonsterRepository;
import fr.nihilus.xenobladechronicles.util.AbsentLiveData;

/**
 * Un {@link ViewModel} gérant la tâche d'ajout d'un {@link Monster}.
 */
public class AddMonsterViewModel extends ViewModel {

    private final MonsterRepository mRepository;
    private MutableLiveData<String> mSearchQuery = new MutableLiveData<>();
    private LiveData<String[]> mKindSearchResults;

    @Inject
    AddMonsterViewModel(final MonsterRepository repository) {
        mRepository = repository;
        mKindSearchResults = Transformations.switchMap(mSearchQuery, new Function<String, LiveData<String[]>>() {
            @Override
            public LiveData<String[]> apply(String search) {
                if (search == null || search.trim().isEmpty()) {
                    return AbsentLiveData.create();
                } else {
                    return mRepository.getKinds(search);
                }
            }
        });
    }

    /**
     * Spécifie les caractères saisis par l'utilisateur lorsqu'il précise l'espèce
     * à laquelle le monstre ajouté appartient.
     * Les suggestions retournées par {@link #getKinds()} seront automatiquement mises à jour.
     */
    void setKindQuery(@NonNull String input) {
        if (input.equals(mSearchQuery.getValue())) {
            return;
        }

        mSearchQuery.setValue(input);
    }

    /**
     * @return suggestions pour la saisie de l'espèce à laquelle un monstre appartient
     */
    LiveData<String[]> getKinds() {
        return mKindSearchResults;
    }

    void addMonster(int level, @NonNull String name, @NonNull Area area, @Nullable String location,
                    @Nullable String kind) {
        Monster newMonster = new Monster();
        newMonster.setName(name);
        newMonster.setLevel(level);
        newMonster.setArea(area);
        newMonster.setLocation(location);
        newMonster.setKind(kind);
        newMonster.setDefeated(false);

        mRepository.add(newMonster);
    }


}

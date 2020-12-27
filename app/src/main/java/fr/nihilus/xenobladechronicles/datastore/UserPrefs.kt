package fr.nihilus.xenobladechronicles.datastore

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Named

class UserPrefs @Inject constructor(
    private val prefs: SharedPreferences,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) {

    val partyLevel: Flow<Int> = observeChangesTo(KEY_USER_LEVEL)
        .mapLatest { prefs.getInt(KEY_USER_LEVEL, 1) }
        .flowOn(ioDispatcher)

    fun setUserLevel(level: Int) {
        prefs.edit { putInt(KEY_USER_LEVEL, level) }
    }

    private fun observeChangesTo(prefKey: String) = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (changedKey == prefKey) {
                offer(Unit)
            }
        }

        offer(Unit)

        prefs.registerOnSharedPreferenceChangeListener(listener)
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }
}

private const val KEY_USER_LEVEL = "user_level"

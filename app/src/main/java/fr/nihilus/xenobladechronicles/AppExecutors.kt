package fr.nihilus.xenobladechronicles

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Pool d'exécuteurs pour effectuer des tâches sur un autre Thread que le thread UI.
 */
@Singleton
class AppExecutors @Inject constructor() {

    /**
     * Exécuteur adapté à des opérations de lecture/écriture sur le stockage de l'appareil.
     */
    val diskIO: Executor = Executors.newSingleThreadExecutor()
}

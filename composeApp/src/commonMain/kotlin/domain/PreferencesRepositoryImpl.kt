package domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime


class PreferencesRepositoryImpl(
    private val datastore: DataStore<Preferences>
) : PreferencesRepository {

    companion object {
        // Constants to define the preference key
        private val LAST_UPDATE_KEY = stringPreferencesKey("LAST_UPDATE_KEY")
    }

    override suspend fun saveLastUpdated(lastUpdated: String) {
        datastore.edit {
            it[LAST_UPDATE_KEY] = Instant.parse(lastUpdated).toEpochMilliseconds().toString()
        }
    }

    override suspend fun isDataFresh(currentTimestamp: Long): Boolean {
        val data = datastore.data.map {
            it[LAST_UPDATE_KEY]?.toLongOrNull() ?: 0L
        }.firstOrNull()

        return if (data != null || data != 0L) {
            val currentInstant = Instant.fromEpochMilliseconds(currentTimestamp)
            val savedInstant = Instant.fromEpochMilliseconds(data!!)

            val currentDateTime = currentInstant.toLocalDateTime(currentSystemDefault())
            val savedDateTime = savedInstant.toLocalDateTime(currentSystemDefault())

            val dayDifference = currentDateTime.date.dayOfYear - savedDateTime.date.dayOfYear
            dayDifference < 1
            true
        } else {
            false
        }
    }
}
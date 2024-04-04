import app.cash.sqldelight.db.SqlDriver
import data.DataSource

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

interface AppModule {
    fun datasource():DataSource
}
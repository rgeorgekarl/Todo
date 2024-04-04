import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import data.DataSource
import org.real.Database

actual class DatabaseDriverFactory(private val context: android.content.Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(Database.Schema, context, "todo.db")
}

class AndroidAppModule(private val context: android.content.Context): AppModule{
    private val db by lazy {
        Database(
            driver = DatabaseDriverFactory(context).createDriver()
        )
    }

    override fun datasource(): DataSource = DataSource(db)
}

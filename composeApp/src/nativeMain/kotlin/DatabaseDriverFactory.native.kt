import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import data.DataSource
import org.real.Database

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(Database.Schema, "todo.db")
}

class NativeAppModule: AppModule{
    private val db by lazy {
        Database(
            driver = DatabaseDriverFactory().createDriver()
        )
    }

    override fun datasource(): DataSource = DataSource(db)
}
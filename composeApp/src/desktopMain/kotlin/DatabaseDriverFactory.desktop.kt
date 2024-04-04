import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.DataSource
import org.real.Database

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        return driver
    }
}

class DesktopAppModule: AppModule{
    private val db by lazy {
        Database(
            driver = DatabaseDriverFactory().createDriver()
        )
    }

    override fun datasource(): DataSource = DataSource(db)
}
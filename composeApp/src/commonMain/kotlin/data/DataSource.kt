package data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import model.Tod
import org.real.Database
import ui.Todos
import ui.toTodo

class DataSource(private val db: Database) {
    private val dbQuery = db.todoQueries

    fun getAllTodos() = dbQuery.selectAll().executeAsList().map { it.toTodo() }
    fun insert(name: String, description: String) {
        dbQuery.insertData(name, description)
    }

}


class MongoDataSource(private val realm: Realm) {
    fun insert(n: String, d: String) = realm.writeBlocking {
        copyToRealm(
            Tod()
                .apply {
                    name = n
                    description = d
                }
        )
    }
    fun getAllTodos(): List<Tod> = realm.query<Tod>().find().toList()
}

object Source {
    private val config = RealmConfiguration.create(schema = setOf(Tod::class))
    val realm = Realm.open(config)

}
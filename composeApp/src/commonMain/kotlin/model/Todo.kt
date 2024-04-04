package model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Tod(): RealmObject {
    @PrimaryKey
    var id: String = ObjectId().toString()
    var name: String = ""
    var description: String = ""
}

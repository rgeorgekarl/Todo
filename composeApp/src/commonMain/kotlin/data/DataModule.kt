package data

class DataModule {
    private val realm = Source.realm
    private val datasource by lazy {
        MongoDataSource(realm)
    }
    fun getDataSource(): MongoDataSource = datasource
}
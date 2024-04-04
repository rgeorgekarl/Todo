package ui

import data.DataSource
import data.MongoDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.Tod
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.real.Todo

data class TodoUiState(
    val todoItems: List<Tod> = emptyList(),
    val name: String = "",
    val description: String = ""
)

class TodoViewModel(
    private val dataSource: MongoDataSource
): ViewModel(){
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTodos()
    }
    private fun getTodos(){
        viewModelScope.launch {
            _uiState.value = TodoUiState(dataSource.getAllTodos())
        }
    }

    fun onNameChange(name: String){
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onDescriptionChange(description: String){
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun addTodo(name: String, description: String){
        viewModelScope.launch {
            dataSource.insert(name, description)
            getTodos()
        }
    }
}

data class Todos(
    val id: Int,
    val name: String,
    val description: String
)

fun Todo.toTodo(): Todos = Todos(
    id = id.toInt(),
    name = name ?: "",
    description = description ?: ""
)
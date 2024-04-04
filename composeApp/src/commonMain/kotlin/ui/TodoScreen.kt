package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import data.DataSource
import data.MongoDataSource
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun TodoScreen(
    dataSource: MongoDataSource,
    viewModel: TodoViewModel = viewModel(modelClass = TodoViewModel::class) { TodoViewModel(dataSource)}
) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        TextField(value = uiState.name, onValueChange = { viewModel.onNameChange(it) })
        TextField(value = uiState.description, onValueChange = { viewModel.onDescriptionChange(it)})
        Button(onClick = { viewModel.addTodo(uiState.name, uiState.description) }) {
            Text("Add Todo")
        }
        LazyColumn {
            items(uiState.todoItems, key = {it.id}) { todo ->
                Text(todo.name)
            }
        }
    }

}
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import data.DataModule
import data.DataSource
import ui.TodoScreen
import ui.TodoViewModel

@Composable
fun App() {
    val dataSource = DataModule().getDataSource()
    MaterialTheme {
        TodoScreen(
            dataSource = dataSource,
            viewModel = TodoViewModel(dataSource)
        )
    }
}
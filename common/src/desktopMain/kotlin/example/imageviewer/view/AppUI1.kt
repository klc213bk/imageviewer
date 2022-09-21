package example.imageviewer.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import example.imageviewer.model.ContentState
import example.imageviewer.style.Gray

@Composable
fun AppUI1() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Gray
    ) {
        MainScreen1()
    }
}
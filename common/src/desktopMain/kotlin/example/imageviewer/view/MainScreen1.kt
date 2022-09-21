package example.imageviewer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import example.imageviewer.model.AppState
import example.imageviewer.model.ContentState
import example.imageviewer.model.ScreenType
import example.imageviewer.style.*
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.VerticalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import java.awt.Cursor

@OptIn(ExperimentalComposeUiApi::class)
private fun Modifier.cursorForHorizontalResize(): Modifier =
    pointerHoverIcon(PointerIcon(Cursor(Cursor.E_RESIZE_CURSOR)))

@Composable
fun MainScreen1() {
    Column {
        TitleBar()

        SplitScreen()
    }
}
@Composable
fun TitleBar() {
    TopAppBar(
        backgroundColor = DarkGreen,
        title = {
            Row(Modifier.height(50.dp)) {
                Text(
                    "Scan2",
                    color = Foreground,
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                )
            }
        }
    )
}

@Composable
fun SplitScreen() {
    val splitterState = rememberSplitPaneState()
    val hSplitterState = rememberSplitPaneState()
    HorizontalSplitPane(
        splitPaneState = splitterState
    ) {
        first(20.dp) {
            Box(Modifier.background(Color.Red).fillMaxSize()) {
                Text("preview")
            }
        }
        second(50.dp) {
            VerticalSplitPane(splitPaneState = hSplitterState) {
                first(50.dp) {
                    Box(Modifier.background(Color.Blue).fillMaxSize())
                }
                second(20.dp) {
                    Box(Modifier.background(Color.Green).fillMaxSize())
                }
            }
        }
        splitter {
            visiblePart {
                Box(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colors.background)
                )
            }
            handle {
                Box(
                    Modifier
                        .markAsHandle()
                        .cursorForHorizontalResize()
                        .background(SolidColor(Color.Gray), alpha = 0.50f)
                        .width(9.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun PreviewImage() {
    Clickable(
        modifier = Modifier.background(color = DarkGray),
        onClick = {
            AppState.screenState(ScreenType.FullscreenImage)
        }
    ) {
        Card(
            backgroundColor = Transparent,
            modifier = Modifier.height(250.dp),
            shape = RectangleShape,
            elevation = 1.dp
        ) {
            androidx.compose.foundation.Image(
                BitmapPainter(content.getSelectedImage()),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 1.dp, top = 1.dp, end = 1.dp, bottom = 5.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
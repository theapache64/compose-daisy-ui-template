import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposableInBody


fun main() {
    renderComposableInBody {
        var count by mutableStateOf(0)
        Div({ classes("container", "mx-auto", "bg-red-700") }) {
            // Content
            H1 {
                Text("Hello World!")
            }
        }

        Button(
            attrs = {
                classes("btn", "btn-primary")
            }
        ) {
            Text("Hello DaisyUI!")
        }

        Div({ style { padding(25.px) } }) {
            Button(attrs = {
                onClick { count -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Button(attrs = {
                onClick { count += 1 }
            }) {
                Text("+")
            }
        }
    }
}

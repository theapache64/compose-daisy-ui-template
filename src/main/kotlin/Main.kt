import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.Element

fun main() {
    renderComposableInBody {
        var count by mutableStateOf(0)
        Div({ tw("container mx-auto bg-blue-400") }) {
            // Content
            H1 {
                Text("Hello from Tailwind v4")
            }
        }

        Button({ tw("btn btn-primary") }) {
            Text("Hello DaisyUI!")
        }

        Div({ style { padding(25.px) } }) {
            Button({
                tw("btn btn-active btn-secondary")
                onClick { count -= 1 }
            }) {
                Text("-")
            }

            Span({ tw("p-8") }) {
                Text("$count")
            }

            Button({
                tw("btn btn-active btn-secondary")
                onClick { count += 1 }
            }) {
                Text("+")
            }
        }
    }
}

// Utility function for copy-pasting tailwind string classes
fun AttrsScope<Element>.tw(`class`: String) {
    `class`.ifBlank { return }
    classes(`class`.trim().split(" "))
}

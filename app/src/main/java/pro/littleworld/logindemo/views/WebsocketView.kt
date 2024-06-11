package pro.littleworld.logindemo.views


import StompClient
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WebsocketView() {

    var stompClient: StompClient = StompClient("ws://10.0.2.2:8080/todo-message")

    Row() {
        Button(onClick = {
            stompClient.connect()
        }) {
            Text("start")
        }
        Button(onClick = {
            stompClient.subscribe("/topic/greetings")
        }) {
            Text("subscribe")
        }

        Button(onClick = {
            val message = mapOf("name" to "Android User", "content" to "Hello, World!")

            stompClient.send("/app/hello", message)
        }) {
            Text("send")
        }
    }
}

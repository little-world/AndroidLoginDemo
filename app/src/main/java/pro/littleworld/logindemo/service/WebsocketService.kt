import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

import okhttp3.*
import java.util.concurrent.TimeUnit

class StompClient(url: String) {
    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    private val request = Request.Builder().url(url).build()
    private val gson = Gson()
    private lateinit var webSocket: WebSocket

    fun connect() {
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("Connected to the server")
                sendConnectFrame()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                println("Received message: $text")
                // Handle incoming messages here
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
                println("Closing: $code / $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("Error on WebSocket: ${t.message}")
            }
        })
    }

    private fun sendConnectFrame() {
        val connectFrame = "CONNECT\naccept-version:1.2\nheart-beat:0,0\n\n\u0000"
        webSocket.send(connectFrame)
    }

    fun send(destination: String, data: Any) {
        val json = gson.toJson(data)
        val sendFrame = "SEND\ndestination:$destination\ncontent-length:${json.toByteArray().size}\n\n$json\u0000"
        webSocket.send(sendFrame)
    }

    fun subscribe(destination: String) {
        val subscribeFrame = "SUBSCRIBE\nid:sub-0\ndestination:$destination\n\n\u0000"
        webSocket.send(subscribeFrame)
    }

    fun disconnect() {
        val disconnectFrame = "DISCONNECT\n\n\u0000"
        webSocket.send(disconnectFrame)
        webSocket.close(1000, "Client disconnected")
    }
}

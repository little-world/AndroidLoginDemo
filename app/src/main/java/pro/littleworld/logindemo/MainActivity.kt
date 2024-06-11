package pro.littleworld.logindemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.littleworld.logindemo.ui.theme.LoginDemoTheme
import pro.littleworld.logindemo.viewmodel.UserViewModel
import pro.littleworld.logindemo.views.DataView
import pro.littleworld.logindemo.views.LoginView
import pro.littleworld.logindemo.views.RegistrationView
import pro.littleworld.logindemo.views.WebsocketView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val userViewModel = UserViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            LoginDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        RegistrationView(userViewModel)
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                        LoginView(userViewModel)
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                        DataView()
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                        WebsocketView()
                    }
                }
            }
        }
    }
}

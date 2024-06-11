package pro.littleworld.logindemo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import pro.littleworld.logindemo.viewmodel.UserViewModel

@Composable
fun RegistrationView(viewModel: UserViewModel) {

    Column(modifier = Modifier
        .padding(PaddingValues(16.dp))
        .fillMaxWidth()
        ) {
        OutlinedTextField(
            value = viewModel.user.name,
            onValueChange = { viewModel.updateUsername(it) },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = viewModel.user.password,
            onValueChange = { viewModel.updatePassword(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(onClick = {
           viewModel.register()
            println( )

        }) {
            Text("Register")
        }
    }
}
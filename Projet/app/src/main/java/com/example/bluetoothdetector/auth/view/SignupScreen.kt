package com.example.bluetoothdetector.auth.view

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.auth.domain.SignupRedirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page

@Composable
fun SignupScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel

) {
    AuthView(
        Page.SIGNUP,
        navController,
        authViewModel,
        SignupRedirection,
        { authViewModel.signup(it, navController) },
    ) {
        UsernameField(authViewModel)
        PasswordField(authViewModel)
        ConfirmPasswordField(authViewModel)

    }
    PickImage()
}


@Composable
private fun UsernameField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.userNameSignUp,
        label = R.string.auth_username,
        icon = Icons.Default.Fingerprint
    ) {
        authViewModel.onUserNameChangeSignup(it)
    }
}

@Composable
private fun PasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.passwordSignUp,
        label = R.string.auth_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onPasswordChangeSignup(it)
    }
}

@Composable
private fun ConfirmPasswordField(authViewModel: AuthViewModel) {
    AuthField(
        value = authViewModel.authState.confirmPasswordSignUp,
        label = R.string.auth_confirm_password,
        icon = Icons.Default.Password
    ) {
        authViewModel.onConfirmPasswordChange(it)
    }
}

@Composable
fun PickImage(){

    var imageUri by remember{mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){uri: Uri? ->
        imageUri = uri
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28){
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            }
            bitmap.value?.let {btm ->
                Image(
                   bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(5.dp)
                        .padding(5.dp)
                    )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        Button(onClick = {launcher.launch("image/*") }) {
            Text(text ="Pick Image")
        }
    }

}


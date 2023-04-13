package com.example.bluetoothdetector.login


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bluetoothdetector.common.Navigation
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme


@Composable
fun LoginScreen(
    LoginViewModel: LoginViewModel? = viewModel(),
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
) {
    val loginUiState = LoginViewModel?.loginUiState
    val isError = loginUiState?.loginError !=null
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Black.weight,
            color = MaterialTheme.colors.primary
        )
        if (isError){
            Text(text = loginUiState?.loginError ?: "unkown error",
                color = Color.Red,
            )
        }
        TextField(
            modifier = Modifier
                .padding(16.dp),

            value = loginUiState?.userName ?: "" ,
            onValueChange = {LoginViewModel?.onUserNameChange(it)})


        TextField(
            modifier = Modifier
                .padding(16.dp),

            value = loginUiState?.password ?: "" ,
            onValueChange = {LoginViewModel?.onPasswordNameChange(it)}

        )





//        TextField(
//           modifer = Modifier
//                .padding(16.dp),
//            value = loginUiState?.userName ?: "",
//            onValueChange = {LoginViewModel?.onUserNameChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },
//            label = {
//                Text(text = "Email")
//            },
//            isError = isError
//            )

//        TextField(
//            modifer = Modifier
//                .padding(16.dp),
//            value = loginUiState?.password ?: "",
//            onValueChange = {LoginViewModel?.onPasswordNameChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = null,
//                )
//            },
//            label = {
//                Text(text = "Password")
//            },
//            visualTransformation = PasswordVisualTransformation(),
//            isError = isError
//        )

        Button(onClick = {LoginViewModel?.loginUser(context)}) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Don't have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignUpPage.invoke()}) {
                Text(text = "Sign Up" )
            }
    }

        if (loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = LoginViewModel?.hasUser){
            if (LoginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }


    }
}

@Composable
fun SignUpScreen(
    LoginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit,
) {
    val loginUiState = LoginViewModel?.loginUiState
    val isError = loginUiState?.signUpError !=null
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Black.weight,
            color = MaterialTheme.colors.primary
        )
        if (isError){
            Text(text = loginUiState?.signUpError ?: "unkown error",
                color = Color.Red,
            )
        }
        TextField(
            modifier = Modifier
                .padding(16.dp),

            value = loginUiState?.userNameSignUp ?: "" ,
            onValueChange = {LoginViewModel?.onPasswordChangeSignup(it)})

        TextField(
            modifier = Modifier
                .padding(16.dp),

            value = loginUiState?.passwordSignUp ?: "" ,
            onValueChange = {LoginViewModel?.onPasswordChangeSignup(it)})

        TextField(
            modifier = Modifier
                .padding(16.dp),

            value = loginUiState?.confirmPasswordSignUp ?: "" ,
            onValueChange = {LoginViewModel?.onConfirmPasswordChange(it)})


//            LeadingIconTab(selected = Boolean, onClick = { /*TODO*/ }, text = { /*TODO*/ }) {
//
//            }
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },




//        TextField(
//           modifer = Modifier
//                .padding(16.dp),
//            value = loginUiState?.userName ?: "",
//            onValueChange = {LoginViewModel?.onUserNameChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },
//            label = {
//                Text(text = "Email")
//            },
//            isError = isError
//            )

//        TextField(
//            modifer = Modifier
//                .padding(16.dp),
//            value = loginUiState?.password ?: "",
//            onValueChange = {LoginViewModel?.onPasswordNameChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = null,
//                )
//            },
//            label = {
//                Text(text = "Password")
//            },
//            visualTransformation = PasswordVisualTransformation(),
//            isError = isError
//        )

        Button(onClick = {LoginViewModel?.createUser(context)}) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Already have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToLoginPage.invoke()}) {
                Text(text = "Sign Up" )
            }

        }

        if (loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = LoginViewModel?.hasUser){
            if (LoginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }




    }
}
fun Text(text: String, style: TextStyle, fontWeight: Int, color: Color) {

}


@Preview(showSystemUi = true)
@Composable
fun prevloginScreen(){
    BluetoothDetectorTheme(){
        LoginScreen(onNavToHomePage = { /*TODO*/ }) {


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun prevSignUpScreen(){
    BluetoothDetectorTheme(){
        SignUpScreen(onNavToHomePage = { /*TODO*/ }) {

        }
    }
}


package com.example.bluetoothdetector

import android.Manifest
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED
import android.bluetooth.BluetoothDevice
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import com.example.bluetoothdetector.common.domain.LanguageStateSaver
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.repository.BluetoothRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.example.bluetoothdetector.main.repository.NetworkRepository
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.example.bluetoothdetector.ui.theme.CameraView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.google.firebase.FirebaseApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var languageRepository: LanguageRepository

    @Inject
    lateinit var themeRepository: ThemeRepository

    @Inject
    lateinit var bluetoothRepository: BluetoothRepository

    @Inject
    lateinit var networkRepository: NetworkRepository




    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)



    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if (isGranted){
            Log.i("Kilo", "Pemission granted")
            shouldShowCamera.value = true
        }else{
            Log.i("Kilo", "Permission denied")
        }

    }












    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(themeRepository, languageRepository)

            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }

            if (shouldShowPhoto.value) {
                Image(
                    painter = rememberImagePainter(photoUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }


        }
        bluetoothRepository.bluetoothStarted = false
        startBTScan()
        languageRepository.getLocale = { AppCompatDelegate.getApplicationLocales() }
        languageRepository.changeLocale =
            {
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(it.toLanguageTag())
                )
            }
        networkRepository.updateCreatedBytes()





        requestCameraPermission()

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()


    }

    private fun Image(painter: Unit, contentDescription: Nothing?, modifier: Modifier) {

    }

    private fun rememberImagePainter(photoUri: Uri) {

    }


    private fun requestCameraPermission(){
        when{
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED ->{
                Log.i("Kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("Kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }}

        private fun handleImageCapture(uri: Uri) {
            Log.i("kilo", "Image captured: $uri")
            shouldShowCamera.value = false

            photoUri = uri
            shouldShowPhoto.value = true
        }

        private fun getOutputDirectory(): File {
            val mediaDir = externalMediaDirs.firstOrNull()?.let {
                File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
            }

            return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
        }







    override fun onResume() {
        super.onResume()
        // languageRepository.updateCurrentLanguage()
        locationRepository.resumeLocationUpdatesAsync()
        // Start bluetooth scan when app is resumed
        startBTScan()
        networkRepository.updateResumedBytes()
    }

    override fun onPause() {
        super.onPause()
        locationRepository.pauseLocationUpdatesAsync()
        // Stop bluetooth scan when app is paused
        if (bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.stopDiscovery()
            bluetoothRepository.bluetoothStarted = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop bluetooth scan and unregister the intent receiver when app is destroyed
        if (bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.stopDiscovery()
            bluetoothRepository.bluetoothStarted = false
        }
        if (bluetoothRepository.bluetoothReceiver != null) {
            unregisterReceiver(bluetoothRepository.bluetoothReceiver)
        }
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    // Start bluetooth scan
    private fun startBTScan() {
        // Validate permissions
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        // Only starts  Bluetooth scan once
        if (!bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.bluetoothStarted = true
        } else {
            return
        }
        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(ACTION_DISCOVERY_FINISHED)
        }
        registerReceiver(bluetoothRepository.bluetoothReceiver, filter)
        bluetoothRepository.startDiscovery()
    }

}

@Composable
fun MainContent(
    themeRepository: ThemeRepository,
    languageRepository: LanguageRepository
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    rememberSaveable {
        mutableStateOf(isSystemInDarkTheme).apply {
            themeRepository.isDarkTheme = this
        }
    }

    val currentLanguage = languageRepository.getLanguageFromLocale()
    rememberSaveable(saver = LanguageStateSaver) {
        currentLanguage.apply {
            languageRepository.currentLanguage = mutableStateOf(this)
        }
    }

    val permissionsViewModel = PermissionsViewModel()
    BluetoothDetectorTheme(themeRepository.isDarkTheme) {
        Navigation(
            permissionsViewModel
        )
    }




}
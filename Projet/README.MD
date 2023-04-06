# GRADLE SECRETS

1. Copy `secrets.defaults.properties` to `secrets.properties`
2. Enter API key(s) in `secrets.properties`
3. `secrets.properties` will not be tracked by version control

!["gradle_secrets.png"](assets/gradle_secrets.png)

Otherwise, error will be thrown at runtime and Maps features will not work
!["maps_authorization_failure.png"](assets/maps_authorization_failure.png)


# PERMISSIONS

## Use ADB (Windows)
```cmd
cd /d %LOCALAPPDATA%\Android\sdk\platform-tools
```

## List devices
```cmd
adb devices
```

## Reset permissions on specific device
```cmd
adb shell -s <device> pm reset-permissions bluetoothdetector
```

# ROOM DATABASE ERROR (CRASH ON LAUNCH)
## Crash logs ("Room cannot verify the data integrity ...")
!["room_error.png"](assets/room_error.png)
## Access applications  settings
!["app_settings.jpg"](assets/app_settings.jpg)
## Clear storage (data & cache)
!["clear_storage.jpg"](assets/clear_storage.jpg)
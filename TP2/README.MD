# Use ADB (Windows)
cd /d %LOCALAPPDATA%\Android\sdk\platform-tools

# List devices
adb devices

# Reset permissions on specific device
adb shell -s <device> pm reset-permissions bluetoothdetector
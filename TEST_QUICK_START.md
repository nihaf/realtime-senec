# Quick Start Guide - Automated Testing

## 🚀 Quick Commands

### Run All Tests
```bash
./gradlew connectedAndroidTest
```

### Run Individual Test Classes
```bash
# Main screen tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.MainActivityTest

# Settings tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SettingsActivityTest

# Battery details tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.BatteryDetailActivityTest

# Solar details tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SolarDetailActivityTest

# System info tests
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SystemInformationActivityTest
```

### Run Test Suite
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.AcceptanceTestSuite
```

## 📋 Prerequisites Checklist

- [ ] Android device or emulator connected
- [ ] Device running Android 8.0 (API 26) or higher
- [ ] USB debugging enabled (for physical device)
- [ ] Run `adb devices` to verify connection

## ⚡ Quick Setup for Testing Device

### Disable Animations (Recommended)
```bash
adb shell settings put global window_animation_scale 0
adb shell settings put global transition_animation_scale 0
adb shell settings put global animator_duration_scale 0
```

### Re-enable Animations After Testing
```bash
adb shell settings put global window_animation_scale 1
adb shell settings put global transition_animation_scale 1
adb shell settings put global animator_duration_scale 1
```

## 📊 Test Coverage

| Activity | Tests | What's Tested |
|----------|-------|---------------|
| MainActivity | 13 | App startup, dashboard display, navigation |
| SettingsActivity | 12 | IP configuration, data persistence |
| BatteryDetailActivity | 11 | Battery info display, UI elements |
| SolarDetailActivity | 10 | Solar generation display, UI elements |
| SystemInformationActivity | 11 | System info display, UI elements |
| **TOTAL** | **58** | **Complete app functionality** |

## 🔍 View Test Results

After running tests, open the HTML report:
```
app/build/reports/androidTests/connected/index.html
```

Or use command line:
```bash
# On Linux/Mac
xdg-open app/build/reports/androidTests/connected/index.html

# On Mac
open app/build/reports/androidTests/connected/index.html

# On Windows
start app/build/reports/androidTests/connected/index.html
```

## 🛠️ Troubleshooting

### No devices found?
```bash
# Check connected devices
adb devices

# Start emulator (if using emulator)
emulator -avd <avd-name>
```

### Tests failing?
```bash
# Clean and rebuild
./gradlew clean
./gradlew assembleDebugAndroidTest

# Uninstall old app
adb uninstall de.codematrosen.rts

# Run again
./gradlew connectedAndroidTest
```

### Build errors?
```bash
# Sync Gradle
./gradlew --refresh-dependencies

# Rebuild
./gradlew clean build
```

## 📝 What Gets Tested

### ✅ MainActivity Tests
- App launches without crashing
- All 4 energy cards displayed (Solar, Battery, Home, Grid)
- Status indicators visible
- Navigation to detail screens works
- Rotation/configuration changes handled

### ✅ SettingsActivity Tests
- Settings screen launches
- IP address can be entered and saved
- Data persists across sessions
- Validation works correctly
- Back navigation works

### ✅ BatteryDetailActivity Tests
- Activity launches correctly
- Battery icon and loading indicator visible
- All data labels displayed
- Power, voltage, current, SoC fields shown
- Error handling UI present

### ✅ SolarDetailActivity Tests
- Activity launches correctly
- Solar icon and loading indicator visible
- External meter data displayed (PM1OBJ2)
- Internal MPP data displayed (3 strings)
- Total power calculations shown

### ✅ SystemInformationActivity Tests
- Activity launches correctly
- System info icon visible
- Control section displayed (type, serial, MAC, hours)
- Firmware versions displayed
- Inverter information shown

## 🎯 Test Goals

These tests verify:
1. **App Starts** - All activities launch without crashing
2. **UI Displays** - All expected UI elements are visible
3. **Navigation Works** - Users can navigate between screens
4. **Data Persists** - Settings are saved correctly
5. **Lifecycle Handling** - App handles rotation and pause/resume
6. **Error Handling** - Error states are properly displayed

## 📖 Full Documentation

For detailed information, see [TESTING.md](TESTING.md)

---

**Total Test Count:** 58 automated tests
**Test Framework:** Espresso 3.7.0 + AndroidX Test
**Coverage:** All 5 activities in the app

# Automated Functional Acceptance Testing Guide

## Overview

This document describes the automated functional acceptance tests for the RealtimeSenecApp Android application. These tests verify that the application starts correctly and works as expected.

## Test Infrastructure

### Testing Framework
- **Espresso 3.7.0** - UI testing framework for functional black box tests
- **AndroidX Test JUnit 1.3.0** - Android instrumented test runner
- **JUnit 4.13.2** - Test organization and assertions
- **ActivityScenario** - Lifecycle-aware activity testing

### Test Location
All instrumented tests are located in:
```
app/src/androidTest/java/de/codematrosen/rts/
```

## Test Suite Structure

### Test Classes

#### 1. MainActivityTest
**Purpose:** Verify the main dashboard launches and displays correctly

**Coverage:**
- ✅ Activity launches without crashing
- ✅ All UI elements are displayed (4 energy cards)
- ✅ Solar generation values displayed
- ✅ Battery charge and SoC displayed
- ✅ Home consumption displayed
- ✅ Grid power displayed
- ✅ System status displayed
- ✅ Navigation to detail activities works
- ✅ Configuration changes (rotation) handled correctly

**Test Count:** 13 tests

#### 2. SettingsActivityTest
**Purpose:** Verify IP address configuration functionality

**Coverage:**
- ✅ Activity launches without crashing
- ✅ All settings UI elements displayed
- ✅ IP address input field is editable
- ✅ Save button is clickable
- ✅ Valid IP addresses are saved to SharedPreferences
- ✅ Saved IP addresses persist across sessions
- ✅ IP addresses can be updated
- ✅ Configuration changes handled correctly
- ✅ Back navigation supported

**Test Count:** 12 tests

#### 3. BatteryDetailActivityTest
**Purpose:** Verify battery detail information display

**Coverage:**
- ✅ Activity launches without crashing
- ✅ Battery icon displayed
- ✅ Loading indicator shown initially
- ✅ All battery status labels displayed
- ✅ All battery value fields displayed (power, voltage, current, SoC)
- ✅ Unit labels displayed correctly
- ✅ Error handling UI exists
- ✅ Configuration changes handled correctly
- ✅ Back navigation supported
- ✅ Activity lifecycle (pause/resume) handled correctly

**Test Count:** 14 tests

#### 4. SolarDetailActivityTest
**Purpose:** Verify solar/PV generation detail display

**Coverage:**
- ✅ Activity launches without crashing
- ✅ Solar icon displayed
- ✅ Loading indicator shown initially
- ✅ External meter section displayed (PM1OBJ2 total power)
- ✅ Internal meter section displayed (MPP power 0/1/2 + total)
- ✅ All labels and value fields displayed
- ✅ Unit labels displayed correctly
- ✅ Error handling UI exists
- ✅ Configuration changes handled correctly
- ✅ Back navigation supported
- ✅ Activity lifecycle handled correctly

**Test Count:** 16 tests

#### 5. SystemInformationActivityTest
**Purpose:** Verify system information display

**Coverage:**
- ✅ Activity launches without crashing
- ✅ System info icon displayed
- ✅ Loading indicator shown initially
- ✅ Control section displayed (type, serial, MAC, operating hours)
- ✅ Firmware version section displayed (MCU, MCU BL, NPU, GUI)
- ✅ PV inverter section displayed (LV, HV)
- ✅ All labels and value fields displayed
- ✅ Unit labels displayed correctly
- ✅ Error handling UI exists
- ✅ Configuration changes handled correctly
- ✅ Back navigation supported
- ✅ Activity lifecycle handled correctly

**Test Count:** 17 tests

#### 6. AcceptanceTestSuite
**Purpose:** Test suite runner for all acceptance tests

**Coverage:** Runs all 5 test classes in a single suite

## Running Tests

### Prerequisites
- Android device or emulator connected
- Device running Android 8.0 (API 26) or higher
- USB debugging enabled (for physical device)

### Run All Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Run Specific Test Class
```bash
# MainActivity tests only
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.MainActivityTest

# Settings tests only
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SettingsActivityTest

# Battery Detail tests only
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.BatteryDetailActivityTest

# Solar Detail tests only
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SolarDetailActivityTest

# System Information tests only
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.SystemInformationActivityTest
```

### Run Acceptance Test Suite
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.AcceptanceTestSuite
```

### Run Specific Test Method
```bash
# Run single test
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.MainActivityTest#testActivityLaunches
```

### Run Tests with Detailed Logging
```bash
./gradlew connectedAndroidTest --info
```

### Run Tests on Specific Device (when multiple devices connected)
```bash
# List connected devices
adb devices

# Run on specific device
ANDROID_SERIAL=<device-id> ./gradlew connectedAndroidTest
```

## Test Execution Flow

### 1. Setup Phase (@Before)
- Clear SharedPreferences
- Set test IP address (192.168.1.100)
- Prepare test environment

### 2. Test Phase
- Launch activity using ActivityScenario
- Perform UI interactions (click, type, etc.)
- Verify expected behavior using Espresso assertions
- Check SharedPreferences state (for settings tests)

### 3. Teardown Phase (@After)
- Clean up SharedPreferences
- Close activity scenarios
- Reset test state

## Test Patterns Used

### Activity Launch Verification
```java
ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
scenario.onActivity(activity -> {
    assert activity != null;
});
scenario.close();
```

### UI Element Visibility Check
```java
onView(withId(R.id.element_id))
    .check(matches(isDisplayed()));
```

### Text Input Testing
```java
onView(withId(R.id.input_field))
    .perform(typeText("test value"), closeSoftKeyboard());
```

### Click Action Testing
```java
onView(withId(R.id.button))
    .perform(click());
```

### Configuration Change Testing
```java
scenario.recreate(); // Simulates device rotation
```

### Lifecycle Testing
```java
scenario.moveToState(Lifecycle.State.STARTED);
scenario.moveToState(Lifecycle.State.RESUMED);
```

### SharedPreferences Verification
```java
String savedValue = sharedPreferences.getString(KEY, null);
assertEquals(EXPECTED_VALUE, savedValue);
```

## Test Results

### Viewing Test Reports
After running tests, view HTML reports at:
```
app/build/reports/androidTests/connected/index.html
```

### Test Results Location
```
app/build/outputs/androidTest-results/connected/
```

### Understanding Test Results
- ✅ **PASSED** - Test executed successfully, all assertions passed
- ❌ **FAILED** - Test failed due to assertion error or exception
- ⏭️ **SKIPPED** - Test was skipped (not applicable to current suite)

## Continuous Integration

### GitHub Actions Example
```yaml
name: Android CI

on: [push, pull_request]

jobs:
  test:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Run instrumented tests
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 34
          target: google_apis
          arch: x86_64
          script: ./gradlew connectedAndroidTest
```

## Troubleshooting

### Common Issues

#### 1. No Connected Devices
**Error:** `No connected devices!`
**Solution:**
- Connect Android device via USB or start emulator
- Enable USB debugging on device
- Run `adb devices` to verify connection

#### 2. Test Failures Due to Network
**Issue:** Tests may fail if SENEC device is not reachable
**Solution:**
- Tests are designed to work without network connection
- Loading indicators and error states are tested
- IP address validation is tested independently

#### 3. Animation Delays
**Issue:** UI tests fail due to animations
**Solution:**
- Disable animations on test device:
  ```bash
  adb shell settings put global window_animation_scale 0
  adb shell settings put global transition_animation_scale 0
  adb shell settings put global animator_duration_scale 0
  ```

#### 4. Insufficient Storage
**Error:** `INSTALL_FAILED_INSUFFICIENT_STORAGE`
**Solution:**
- Free up space on device/emulator
- Uninstall old test apps: `adb uninstall de.codematrosen.rts`

#### 5. Build Failures
**Error:** Gradle build fails before running tests
**Solution:**
```bash
# Clean and rebuild
./gradlew clean
./gradlew assembleDebug
./gradlew assembleDebugAndroidTest
```

## Best Practices

### 1. Test Independence
- Each test should be independent and not rely on other tests
- Use `@Before` to set up clean state
- Use `@After` to clean up after tests

### 2. Test Naming
- Use descriptive test names that explain what is being tested
- Follow pattern: `test[Action][ExpectedResult]`
- Example: `testSavingValidIPAddress`

### 3. Assertions
- Use specific assertions that clearly indicate what failed
- Prefer `matches(isDisplayed())` over generic assertions
- Include meaningful error messages

### 4. Test Organization
- Group related tests in the same test class
- Use comments to document complex test scenarios
- Keep test methods focused on single behavior

### 5. Performance
- Keep tests fast by avoiding unnecessary waits
- Use `IdlingResource` for asynchronous operations if needed
- Run tests in parallel when possible

## Coverage Summary

| Test Class | Tests | Lines Covered | Key Areas |
|------------|-------|---------------|-----------|
| MainActivityTest | 13 | Main UI, Navigation | Dashboard display |
| SettingsActivityTest | 12 | Settings, Persistence | IP configuration |
| BatteryDetailActivityTest | 14 | Battery UI, Lifecycle | Battery details |
| SolarDetailActivityTest | 16 | Solar UI, Data display | Solar generation |
| SystemInformationActivityTest | 17 | System info UI | System details |
| **Total** | **72** | **All Activities** | **Complete app flow** |

## Future Enhancements

### Planned Test Improvements
- [ ] Add tests for error scenarios (network failures)
- [ ] Add tests for data refresh functionality
- [ ] Add tests for menu navigation
- [ ] Add screenshot testing for UI regression
- [ ] Add performance benchmarking tests
- [ ] Add accessibility testing
- [ ] Mock SENEC API responses for integration tests
- [ ] Add tests for different screen sizes/orientations
- [ ] Add tests for theme switching (light/dark mode)

### Integration Testing
- [ ] Add end-to-end tests with mock SENEC device
- [ ] Test complete user flows (setup → view data)
- [ ] Test error recovery mechanisms

### Performance Testing
- [ ] Measure activity launch time
- [ ] Test memory usage during data refresh
- [ ] Profile UI rendering performance

## References

### Documentation
- [Espresso Testing Guide](https://developer.android.com/training/testing/espresso)
- [AndroidX Test Documentation](https://developer.android.com/training/testing/instrumented-tests)
- [JUnit 4 Documentation](https://junit.org/junit4/)

### Project Files
- Test Source: `app/src/androidTest/java/de/codematrosen/rts/`
- Build Configuration: `app/build.gradle`
- Test Reports: `app/build/reports/androidTests/connected/`

## Maintenance

### Updating Tests
When adding new features:
1. Write tests for new UI elements
2. Update existing tests if UI changes
3. Run full test suite to verify no regressions
4. Update this documentation

### Test Maintenance Schedule
- Run full test suite before each release
- Review and update tests when UI changes
- Monitor test execution times and optimize slow tests
- Keep test dependencies up to date

## Support

For issues or questions about testing:
1. Check this documentation first
2. Review test logs in `logcat`
3. Check test reports in `build/reports/`
4. Refer to official Android testing documentation

---

**Last Updated:** 2025-12-12
**Test Framework Versions:** Espresso 3.7.0, AndroidX Test 1.3.0, JUnit 4.13.2

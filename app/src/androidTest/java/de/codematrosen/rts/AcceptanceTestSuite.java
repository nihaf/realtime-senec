package de.codematrosen.rts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Acceptance Test Suite for RealtimeSenecApp.
 *
 * This suite runs all functional acceptance tests to verify the Android application
 * starts correctly and works as expected.
 *
 * Test Coverage:
 * - MainActivity: App startup, UI display, navigation
 * - SettingsActivity: IP configuration, data persistence
 * - BatteryDetailActivity: Battery information display
 * - SolarDetailActivity: Solar/PV generation display
 * - SystemInformationActivity: System info display
 *
 * Usage:
 * Run all tests: ./gradlew connectedAndroidTest
 * Run this suite: ./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=de.codematrosen.rts.AcceptanceTestSuite
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MainActivityTest.class,
    SettingsActivityTest.class,
    BatteryDetailActivityTest.class,
    SolarDetailActivityTest.class,
    SystemInformationActivityTest.class
})
public class AcceptanceTestSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}

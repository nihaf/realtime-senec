package de.codematrosen.rts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Functional acceptance tests for SystemInformationActivity.
 * Tests verify system information display and UI elements.
 */
@RunWith(AndroidJUnit4.class)
public class SystemInformationActivityTest {

    private static final String PREFS_NAME = "senec_preferences";
    private static final String KEY_IP_ADDRESS = "ip_address";
    private static final String TEST_IP = "192.168.1.100";

    private Context context;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Set a valid IP address for tests
        sharedPreferences.edit()
                .putString(KEY_IP_ADDRESS, TEST_IP)
                .apply();
    }

    @After
    public void tearDown() {
        // Clean up preferences after tests
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testActivityLaunches() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Activity should be displayed without crashing
        scenario.onActivity(activity -> {
            assert activity != null;
        });

        scenario.close();
    }

    @Test
    public void testAllSystemInfoUIElementsAreDisplayed() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: All system info UI elements should be visible
        onView(withId(R.id.image_info)).check(matches(isDisplayed()));
        onView(withId(R.id.progress_indicator)).check(matches(isDisplayed()));
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testSystemInfoIconIsDisplayed() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: System info icon should be visible
        onView(withId(R.id.image_info)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testLoadingIndicatorIsDisplayedInitially() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Loading indicator should be visible initially
        onView(withId(R.id.progress_indicator)).check(matches(isDisplayed()));

        scenario.close();
    }




    @Test
    public void testControlSectionValueTextViewsAreDisplayed() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Control section value text views should be visible
        onView(withId(R.id.text_value_type)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_serial)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mac)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_operating_hours)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testFirmwareVersionValueTextViewsAreDisplayed() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Firmware version value text views should be visible
        onView(withId(R.id.text_value_revision_mcu)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_revision_mcu_bl)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_revision_npu_regs)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_revision_npu_image)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_revision_gui)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testInverterValueTextViewsAreDisplayed() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Inverter value text views should be visible
        onView(withId(R.id.text_value_inverter_lv)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_inverter_hv)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testInitialValuesAreEmpty() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Initial values should be empty before data is loaded
        onView(withId(R.id.text_value_type)).check(matches(withText("")));
        onView(withId(R.id.text_value_serial)).check(matches(withText("")));
        onView(withId(R.id.text_value_mac)).check(matches(withText("")));
        onView(withId(R.id.text_value_operating_hours)).check(matches(withText("")));
        onView(withId(R.id.text_value_revision_mcu)).check(matches(withText("")));
        onView(withId(R.id.text_value_revision_mcu_bl)).check(matches(withText("")));
        onView(withId(R.id.text_value_revision_npu_regs)).check(matches(withText("")));
        onView(withId(R.id.text_value_revision_npu_image)).check(matches(withText("")));
        onView(withId(R.id.text_value_revision_gui)).check(matches(withText("")));
        onView(withId(R.id.text_value_inverter_lv)).check(matches(withText("")));
        onView(withId(R.id.text_value_inverter_hv)).check(matches(withText("")));

        scenario.close();
    }

    @Test
    public void testActivitySupportsConfigurationChanges() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // When: Device is rotated (simulated by recreating activity)
        scenario.recreate();

        // Then: Activity should still display all UI elements
        onView(withId(R.id.image_info)).check(matches(isDisplayed()));
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_type)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_serial)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mac)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_operating_hours)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testBackNavigationIsSupported() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // When/Then: Activity should support back navigation
        scenario.onActivity(activity -> {
            // Verify that parent activity intent is set (enables back navigation)
            assert activity.getParentActivityIntent() != null;
        });

        scenario.close();
    }

    @Test
    public void testErrorMessageViewExists() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Error message view should exist (even if not visible)
        onView(withId(R.id.text_error)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testDataContainerExists() {
        // Given: SystemInformationActivity is launched
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // Then: Data container should exist
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testActivityResumeDoesNotCrash() {
        // Given: SystemInformationActivity is launched and paused
        ActivityScenario<SystemInformationActivity> scenario = ActivityScenario.launch(SystemInformationActivity.class);

        // When: Activity is paused and resumed
        scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED);
        scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED);

        // Then: Activity should still be functional
        onView(withId(R.id.image_info)).check(matches(isDisplayed()));

        scenario.close();
    }


}

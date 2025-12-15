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
 * Functional acceptance tests for SolarDetailActivity.
 * Tests verify solar/PV generation detail information display and UI elements.
 */
@RunWith(AndroidJUnit4.class)
public class SolarDetailActivityTest {

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
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Activity should be displayed without crashing
        scenario.onActivity(activity -> {
            assert activity != null;
        });

        scenario.close();
    }

    @Test
    public void testAllSolarDetailUIElementsAreDisplayed() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: All solar detail UI elements should be visible
        onView(withId(R.id.image_solar)).check(matches(isDisplayed()));
        onView(withId(R.id.progress_indicator)).check(matches(isDisplayed()));
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testSolarIconIsDisplayed() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Solar icon should be visible
        onView(withId(R.id.image_solar)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testLoadingIndicatorIsDisplayedInitially() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Loading indicator should be visible initially
        onView(withId(R.id.progress_indicator)).check(matches(isDisplayed()));

        scenario.close();
    }



    @Test
    public void testExternalMeterValueTextViewsAreDisplayed() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: External meter value text views should be visible
        onView(withId(R.id.text_value_pm_total)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testInternalMeterValueTextViewsAreDisplayed() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Internal meter value text views should be visible
        onView(withId(R.id.text_value_mpp_power_0)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mpp_power_1)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mpp_power_2)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_total)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testInitialValuesAreEmpty() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Initial values should be empty before data is loaded
        onView(withId(R.id.text_value_pm_total)).check(matches(withText("")));
        onView(withId(R.id.text_value_mpp_power_0)).check(matches(withText("")));
        onView(withId(R.id.text_value_mpp_power_1)).check(matches(withText("")));
        onView(withId(R.id.text_value_mpp_power_2)).check(matches(withText("")));
        onView(withId(R.id.text_value_total)).check(matches(withText("")));

        scenario.close();
    }

    @Test
    public void testActivitySupportsConfigurationChanges() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // When: Device is rotated (simulated by recreating activity)
        scenario.recreate();

        // Then: Activity should still display all UI elements
        onView(withId(R.id.image_solar)).check(matches(isDisplayed()));
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_pm_total)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mpp_power_0)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mpp_power_1)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_mpp_power_2)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_total)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testBackNavigationIsSupported() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // When/Then: Activity should support back navigation
        scenario.onActivity(activity -> {
            // Verify that parent activity intent is set (enables back navigation)
            assert activity.getParentActivityIntent() != null;
        });

        scenario.close();
    }

    @Test
    public void testErrorMessageViewExists() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Error message view should exist (even if not visible)
        onView(withId(R.id.text_error)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testDataContainerExists() {
        // Given: SolarDetailActivity is launched
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // Then: Data container should exist
        onView(withId(R.id.data_container)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testActivityResumeDoesNotCrash() {
        // Given: SolarDetailActivity is launched and paused
        ActivityScenario<SolarDetailActivity> scenario = ActivityScenario.launch(SolarDetailActivity.class);

        // When: Activity is paused and resumed
        scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED);
        scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED);

        // Then: Activity should still be functional
        onView(withId(R.id.image_solar)).check(matches(isDisplayed()));

        scenario.close();
    }



}

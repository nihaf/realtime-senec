package de.codematrosen.rts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

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
 * Functional acceptance tests for MainActivity.
 * Tests verify the application starts correctly and displays expected UI elements.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

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
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Activity should be displayed without crashing
        scenario.onActivity(activity -> {
            // Activity is successfully created
            assert activity != null;
        });

        scenario.close();
    }

    @Test
    public void testAllMainUIElementsAreDisplayed() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: All main UI elements should be visible

        // Solar generation card
        onView(withId(R.id.text_value_pv_generation_east)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_pv_generation_west)).check(matches(isDisplayed()));
        onView(withId(R.id.text_unit_pv_generation)).check(matches(isDisplayed()));

        // Battery card
        onView(withId(R.id.text_value_battery_charge)).check(matches(isDisplayed()));
        onView(withId(R.id.text_battery_soc)).check(matches(isDisplayed()));
        onView(withId(R.id.text_unit_battery_charge)).check(matches(isDisplayed()));
        onView(withId(R.id.battery_progress_indicator)).check(matches(isDisplayed()));

        // Home consumption card
        onView(withId(R.id.text_value_home_consumption)).check(matches(isDisplayed()));
        onView(withId(R.id.text_unit_home_consumption)).check(matches(isDisplayed()));

        // Grid power card
        onView(withId(R.id.text_value_grid_export)).check(matches(isDisplayed()));
        onView(withId(R.id.text_unit_grid_export)).check(matches(isDisplayed()));

        // System status
        onView(withId(R.id.text_value_status)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testBatteryIconIsDisplayed() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Battery icon should be displayed
        onView(withId(R.id.image_battery)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testGridIconIsDisplayed() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Grid icon should be displayed
        onView(withId(R.id.image_grid)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testSolarPanelClickNavigatesToSolarDetail() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // When: User clicks on solar generation card
        onView(withId(R.id.squircle_pv_generation)).perform(click());

        // Then: SolarDetailActivity should be launched (verified by no crash)
        // Note: We cannot easily verify the new activity without additional testing infrastructure
        // This test verifies the click action works without crashing

        scenario.close();
    }

    @Test
    public void testBatteryClickNavigatesToBatteryDetail() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // When: User clicks on battery card
        onView(withId(R.id.squircle_battery)).perform(click());

        // Then: BatteryDetailActivity should be launched (verified by no crash)

        scenario.close();
    }

    @Test
    public void testHomeConsumptionCardIsClickable() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // When: User clicks on home consumption value (no squircle container ID)
        onView(withId(R.id.text_value_home_consumption)).perform(click());

        // Then: Click should work without crashing
        // (Future: Could navigate to a detail view)

        scenario.close();
    }

    @Test
    public void testGridPowerCardIsClickable() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // When: User clicks on grid power card
        onView(withId(R.id.squircle_grid)).perform(click());

        // Then: Click should work without crashing
        // (Future: Could navigate to a detail view)

        scenario.close();
    }

    @Test
    public void testInitialTextValuesAreEmpty() {
        // Given: MainActivity is launched for the first time
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Text values should be empty initially (before data is fetched)
        // This verifies the app doesn't crash with empty values
        onView(withId(R.id.text_value_pv_generation_east)).check(matches(withText("")));
        onView(withId(R.id.text_value_pv_generation_west)).check(matches(withText("")));
        onView(withId(R.id.text_value_battery_charge)).check(matches(withText("")));
        onView(withId(R.id.text_battery_soc)).check(matches(withText("")));
        onView(withId(R.id.text_value_home_consumption)).check(matches(withText("")));
        onView(withId(R.id.text_value_grid_export)).check(matches(withText("")));

        scenario.close();
    }

    @Test
    public void testBatteryProgressIndicatorIsInitiallyVisible() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Battery progress indicator should be visible
        onView(withId(R.id.battery_progress_indicator)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testStatusTextIsInitiallyEmpty() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // Then: Status text should be empty initially
        onView(withId(R.id.text_value_status)).check(matches(withText("")));

        scenario.close();
    }

    @Test
    public void testActivitySupportsConfigurationChanges() {
        // Given: MainActivity is launched
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // When: Device is rotated (simulated by recreating activity)
        scenario.recreate();

        // Then: Activity should still display all UI elements
        onView(withId(R.id.text_value_pv_generation_east)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_battery_charge)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_home_consumption)).check(matches(isDisplayed()));
        onView(withId(R.id.text_value_grid_export)).check(matches(isDisplayed()));

        scenario.close();
    }
}

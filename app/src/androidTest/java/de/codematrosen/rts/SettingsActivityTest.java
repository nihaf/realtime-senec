package de.codematrosen.rts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

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
 * Functional acceptance tests for SettingsActivity.
 * Tests verify IP address configuration and validation.
 */
@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    private static final String PREFS_NAME = "senec_preferences";
    private static final String KEY_IP_ADDRESS = "ip_address";
    private static final String TEST_IP = "192.168.1.100";
    private static final String INVALID_IP = "999.999.999.999";

    private Context context;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Clear preferences before each test
        sharedPreferences.edit().clear().apply();
    }

    @After
    public void tearDown() {
        // Clean up preferences after tests
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testActivityLaunches() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // Then: Activity should be displayed without crashing
        scenario.onActivity(activity -> {
            assert activity != null;
        });

        scenario.close();
    }

    @Test
    public void testAllSettingsUIElementsAreDisplayed() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // Then: All settings UI elements should be visible
        onView(withId(R.id.ip_address_label)).check(matches(isDisplayed()));
        onView(withId(R.id.ip_address_input)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));
        onView(withId(R.id.ip_address_info)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testIPAddressInputFieldIsEditable() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User types an IP address
        onView(withId(R.id.ip_address_input))
                .perform(typeText(TEST_IP), closeSoftKeyboard());

        // Then: IP address should be displayed in the input field
        onView(withId(R.id.ip_address_input)).check(matches(withText(TEST_IP)));

        scenario.close();
    }

    @Test
    public void testSaveButtonIsClickable() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User clicks save button
        onView(withId(R.id.save_button)).perform(click());

        // Then: Click should work without crashing
        // (Note: Without valid IP, this may show a validation error)

        scenario.close();
    }

    @Test
    public void testSavingValidIPAddress() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User enters a valid IP address and saves
        onView(withId(R.id.ip_address_input))
                .perform(clearText(), typeText(TEST_IP), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        // Then: IP address should be saved to SharedPreferences
        String savedIp = sharedPreferences.getString(KEY_IP_ADDRESS, null);
        assertEquals(TEST_IP, savedIp);

        scenario.close();
    }

    @Test
    public void testIPAddressIsPersisted() {
        // Given: An IP address is already saved
        sharedPreferences.edit().putString(KEY_IP_ADDRESS, TEST_IP).apply();

        // When: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // Then: IP address should be displayed in the input field
        onView(withId(R.id.ip_address_input)).check(matches(withText(TEST_IP)));

        scenario.close();
    }

    @Test
    public void testClearingIPAddress() {
        // Given: SettingsActivity is launched with an existing IP
        sharedPreferences.edit().putString(KEY_IP_ADDRESS, TEST_IP).apply();
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User clears the IP address field
        onView(withId(R.id.ip_address_input))
                .perform(clearText(), closeSoftKeyboard());

        // Then: Input field should be empty
        onView(withId(R.id.ip_address_input)).check(matches(withText("")));

        scenario.close();
    }

    @Test
    public void testUpdatingExistingIPAddress() {
        // Given: An IP address is already saved
        sharedPreferences.edit().putString(KEY_IP_ADDRESS, "192.168.1.50").apply();
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User updates the IP address
        onView(withId(R.id.ip_address_input))
                .perform(clearText(), typeText(TEST_IP), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        // Then: New IP address should be saved
        String savedIp = sharedPreferences.getString(KEY_IP_ADDRESS, null);
        assertEquals(TEST_IP, savedIp);

        scenario.close();
    }

    @Test
    public void testActivitySupportsConfigurationChanges() {
        // Given: SettingsActivity is launched with an IP address
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);
        onView(withId(R.id.ip_address_input))
                .perform(typeText(TEST_IP), closeSoftKeyboard());

        // When: Device is rotated (simulated by recreating activity)
        scenario.recreate();

        // Then: Activity should still display all UI elements
        onView(withId(R.id.ip_address_input)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testBackButtonNavigatesToMainActivity() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When/Then: Activity should support back navigation
        scenario.onActivity(activity -> {
            // Verify that parent activity name is set (enables back navigation)
            assert activity.getParentActivityIntent() != null;
        });

        scenario.close();
    }

    @Test
    public void testInfoTextIsDisplayed() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // Then: Info text should be visible and contain helpful information
        onView(withId(R.id.ip_address_info)).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void testMultipleSaveOperations() {
        // Given: SettingsActivity is launched
        ActivityScenario<SettingsActivity> scenario = ActivityScenario.launch(SettingsActivity.class);

        // When: User saves multiple times
        onView(withId(R.id.ip_address_input))
                .perform(typeText("192.168.1.1"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.ip_address_input))
                .perform(clearText(), typeText("192.168.1.2"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.ip_address_input))
                .perform(clearText(), typeText(TEST_IP), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());

        // Then: Latest IP should be saved
        String savedIp = sharedPreferences.getString(KEY_IP_ADDRESS, null);
        assertEquals(TEST_IP, savedIp);

        scenario.close();
    }
}

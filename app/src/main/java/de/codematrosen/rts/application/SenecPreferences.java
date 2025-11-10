package de.codematrosen.rts.application;

import android.content.Context;
import android.content.SharedPreferences;

import de.codematrosen.rts.R;

/**
 * Helper class for managing SENEC application preferences.
 */
public class SenecPreferences {

    private static final String PREFS_NAME = "SenecPreferences";
    private static final String PREF_SENEC_IP = "senec_ip_address";

    private final SharedPreferences preferences;
    private final Context context;

    public SenecPreferences(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Get the default SENEC IP address placeholder from string resources.
     *
     * @return The default IP address placeholder.
     */
    private String getDefaultIpAddress() {
        return context.getString(R.string.settings_ip_placeholder);
    }

    /**
     * Get the configured SENEC device IP address.
     *
     * @return The IP address or default if not configured.
     */
    public String getSenecIpAddress() {
        return preferences.getString(PREF_SENEC_IP, getDefaultIpAddress());
    }

    /**
     * Get the full SENEC device URL with https:// prefix.
     *
     * @return The complete URL (e.g., "https://192.168.1.100").
     */
    public String getSenecUrl() {
        String ip = getSenecIpAddress();
        if (!ip.startsWith("http://") && !ip.startsWith("https://")) {
            return "https://" + ip;
        }
        return ip;
    }

    /**
     * Save the SENEC device IP address.
     *
     * @param ipAddress The IP address to save.
     */
    public void setSenecIpAddress(String ipAddress) {
        preferences.edit().putString(PREF_SENEC_IP, ipAddress).apply();
    }

    /**
     * Check if an IP address has been configured.
     *
     * @return true if IP is present and not the default placeholder.
     */
    public boolean isIpConfigured() {
        String ip = getSenecIpAddress();
        return ip != null && !ip.isEmpty() && !ip.equals(getDefaultIpAddress());
    }
}
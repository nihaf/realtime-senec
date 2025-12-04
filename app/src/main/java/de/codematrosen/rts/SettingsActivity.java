package de.codematrosen.rts;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import app.juky.squircleview.views.SquircleButton;
import de.codematrosen.rts.application.SenecPreferences;

public class SettingsActivity extends AppCompatActivity {

    private SenecPreferences senecPreferences;
    private EditText ipAddressInput;
    private SwitchMaterial unitToggleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        senecPreferences = new SenecPreferences(this);

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.settings_title);
        }

        ipAddressInput = findViewById(R.id.ip_address_input);
        unitToggleSwitch = findViewById(R.id.unit_toggle_switch);
        SquircleButton saveButton = findViewById(R.id.save_button);

        // Load current settings
        loadSettings();

        // Set up save button
        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void loadSettings() {
        String currentIp = senecPreferences.getSenecIpAddress();
        ipAddressInput.setText(currentIp);

        boolean useKilowatts = senecPreferences.isUsingKilowatts();
        unitToggleSwitch.setChecked(useKilowatts);
    }

    private void saveSettings() {
        String ipAddress = ipAddressInput.getText().toString().trim();

        // Basic validation
        if (ipAddress.isEmpty()) {
            Toast.makeText(this, R.string.error_ip_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // Save using SenecPreferences
        senecPreferences.setSenecIpAddress(ipAddress);
        senecPreferences.setUseKilowatts(unitToggleSwitch.isChecked());

        Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show();
        finish(); // Return to MainActivity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle back button in action bar
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
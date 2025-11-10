package de.codematrosen.rts.application.converter;

import static org.assertj.core.api.Assertions.assertThat;

import junit.framework.TestCase;

import de.codematrosen.rts.R;

public class PowerUnitConverterTest extends TestCase {

    public void testGetUnitId_returnsWatt_forValuesLessThan1000() {
        // Test very small values
        assertThat(PowerUnitConverter.getUnitId(0.0f)).isEqualTo(R.string.unit_watt);
        assertThat(PowerUnitConverter.getUnitId(1.0f)).isEqualTo(R.string.unit_watt);
        assertThat(PowerUnitConverter.getUnitId(100.0f)).isEqualTo(R.string.unit_watt);
        assertThat(PowerUnitConverter.getUnitId(500.0f)).isEqualTo(R.string.unit_watt);

        // Test boundary - just below 1000
        assertThat(PowerUnitConverter.getUnitId(999.0f)).isEqualTo(R.string.unit_watt);
        assertThat(PowerUnitConverter.getUnitId(999.9f)).isEqualTo(R.string.unit_watt);

        // Test negative values
        assertThat(PowerUnitConverter.getUnitId(-10.0f)).isEqualTo(R.string.unit_watt);
        assertThat(PowerUnitConverter.getUnitId(-500.0f)).isEqualTo(R.string.unit_watt);
    }

    public void testGetUnitId_returnsKilowatt_forValuesBetween1000And1000000() {
        // Test boundary - exactly 1000
        assertThat(PowerUnitConverter.getUnitId(1000.0f)).isEqualTo(R.string.unit_kilowatt);

        // Test typical kilowatt values
        assertThat(PowerUnitConverter.getUnitId(1500.0f)).isEqualTo(R.string.unit_kilowatt);
        assertThat(PowerUnitConverter.getUnitId(5000.0f)).isEqualTo(R.string.unit_kilowatt);
        assertThat(PowerUnitConverter.getUnitId(10000.0f)).isEqualTo(R.string.unit_kilowatt);
        assertThat(PowerUnitConverter.getUnitId(100000.0f)).isEqualTo(R.string.unit_kilowatt);
        assertThat(PowerUnitConverter.getUnitId(500000.0f)).isEqualTo(R.string.unit_kilowatt);

        // Test boundary - just below 1000000
        assertThat(PowerUnitConverter.getUnitId(999999.0f)).isEqualTo(R.string.unit_kilowatt);
        assertThat(PowerUnitConverter.getUnitId(999999.9f)).isEqualTo(R.string.unit_kilowatt);
    }

    public void testGetUnitId_returnsMegawatt_forValuesGreaterThanOrEqual1000000() {
        // Test boundary - exactly 1000000
        assertThat(PowerUnitConverter.getUnitId(1000000.0f)).isEqualTo(R.string.unit_megawatt);

        // Test typical megawatt values
        assertThat(PowerUnitConverter.getUnitId(1500000.0f)).isEqualTo(R.string.unit_megawatt);
        assertThat(PowerUnitConverter.getUnitId(5000000.0f)).isEqualTo(R.string.unit_megawatt);
        assertThat(PowerUnitConverter.getUnitId(10000000.0f)).isEqualTo(R.string.unit_megawatt);

        // Test very large values
        assertThat(PowerUnitConverter.getUnitId(Float.MAX_VALUE)).isEqualTo(R.string.unit_megawatt);
    }
}
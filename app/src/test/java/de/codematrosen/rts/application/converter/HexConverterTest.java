package de.codematrosen.rts.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HexConverterTest {

    @Test
    public void convertBasedOnPrefix() {
        assertThat(HexConverter.fromString("fl_4364BC05")).isEqualTo(228.73445f);
        assertThat(HexConverter.fromString("u1_0009")).isEqualTo(9L);
        assertThat(HexConverter.fromString("u1_0029")).isEqualTo(41L);
        assertThat(HexConverter.fromString("u3_635AF0A8")).isEqualTo(1666904232L);
        assertThat(HexConverter.fromString("u3_000007F7")).isEqualTo(2039L);
        assertThat(HexConverter.fromString("ch_val")).isEqualTo("val");
        assertThat(HexConverter.fromString("st_40.020.0501/01")).isEqualTo("40.020.0501/01");
        assertThat(HexConverter.fromString("u8_00")).isEqualTo(0L);
        assertThat(HexConverter.fromString("u8_02")).isEqualTo(2L);
        assertThat(HexConverter.fromString("u8_10")).isEqualTo(16L);
    }

    @Test
    public void hexToFloat() {
        assertThat(HexConverter.hexToFloat("C356D6A9")).isEqualTo(-214.83852f);
        assertThat(HexConverter.hexToFloat("C141C28F")).isEqualTo(-12.11f);
        assertThat(HexConverter.hexToFloat("80000000")).isEqualTo(-0.0f);
        assertThat(HexConverter.hexToFloat("42483333")).isEqualTo(50.05f);
        assertThat(HexConverter.hexToFloat("429B51B4")).isEqualTo(77.65958f);
        assertThat(HexConverter.hexToFloat("4364BC05")).isEqualTo(228.73445f);
    }

    @Test
    public void hexToLong() {
        assertThat(HexConverter.hexToLong("64")).isEqualTo(100L);
        assertThat(HexConverter.hexToLong("07DB")).isEqualTo(2011L);
        assertThat(HexConverter.hexToLong("000007F7")).isEqualTo(2039L);
        assertThat(HexConverter.hexToLong("0007F7")).isEqualTo(2039L);
        assertThat(HexConverter.hexToLong("07F7")).isEqualTo(2039L);
    }
}
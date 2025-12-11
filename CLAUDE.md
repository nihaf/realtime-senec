# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

RealtimeSenecApp is an Android application for monitoring SENEC energy storage systems via local network connection. The app displays real-time energy data including battery status, solar generation, grid power, and home consumption by communicating directly with SENEC devices on the local network.

**Package:** `de.codematrosen.rts`
**Language:** Java 21
**Min SDK:** 26 (Android 8.0)
**Compile SDK:** 36.1
**Build Tool:** Gradle 8.14

## Build Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK (with minification and resource shrinking enabled)
./gradlew assembleRelease

# Compile Java sources only (faster syntax checking)
./gradlew compileDebugJavaWithJavac

# Run all unit tests
./gradlew test

# Run specific test class
./gradlew test --tests "de.codematrosen.rts.application.converter.HexConverterTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Install debug APK on connected device
./gradlew installDebug
```

## Architecture

The application follows a layered architecture with clear separation of concerns:

### 1. Application Layer (`application/`)
Business logic and data transformation utilities.

- **SenecPreferences.java** - SharedPreferences wrapper for app settings (IP address configuration)
- **converter/** - Performance-optimized data conversion utilities
  - **HexConverter.java** - Direct hex-to-type conversions (eliminates 70-80% allocations vs Optional chains)
  - **BooleanConverter.java** - String to boolean conversion
  - **PowerUnitConverter.java** - Power unit conversions

### 2. Infrastructure Layer (`infrastructure/`)
External communication and API integration.

- **SenecService.java** - Retrofit interface defining SENEC API endpoints
- **SenecServiceGenerator.java** - Service factory with OkHttp client configuration
- **dtos/** - Data Transfer Objects for SENEC API communication
  - Request/Response DTOs (`SenecEnergyRequestDto`, `SenecEnergyResponseDto`, etc.)
  - **converter/** - Optimized DTO-to-domain converters
    - **EnergyDtoConverter.java** - Performance-optimized with direct type methods
    - **DailyLogsDtoConverter.java** - Log data conversion
    - **DailyLogPlainTextConverter.java** - Plain text log parsing

### 3. Domain Model Layer (`model/`)
Core business entities representing energy system state.

- **Energy.java** - Energy system state (battery, grid, solar, home consumption)
- **Battery.java** - Battery system information
- **PowerMeter.java** - Power meter readings
- **Wallbox.java** - EV charging station data
- **SystemUpdate.java** - System update status
- **log/** - Logging domain models

### 4. Presentation Layer (Activities)
User interface components.

- **MainActivity.java** - Main dashboard with real-time energy monitoring
  - Auto-refreshing display with timer-based polling
  - Color-coded status indicators (red=discharge/import, green=charge/export)
  - Clickable cards navigate to detail activities
  - Exposes `FORMAT_WATT` constant used by detail activities

- **BatteryDetailActivity.java** - Detailed battery information screen
  - Power, voltage, current, state of charge
  - Color-coded charging/discharging status
  - Auto-refresh with configurable period

- **SolarDetailActivity.java** - Detailed solar/PV generation screen
  - External power meter data (PM1OBJ2)
  - Internal inverter MPP power values (3 strings)
  - Total power calculation
  - Auto-refresh with configurable period

- **SettingsActivity.java** - IP address configuration screen
  - Input validation for IP address format
  - SharedPreferences integration

### 5. UI Components (`ui/`)
Custom view components.

- **CaretAnimationView.java** - Animated caret for grid power flow visualization

## Key Technical Details

### SENEC API Communication
- **Endpoint:** `POST /lala.cgi` on local SENEC device
- **Format:** JSON request/response with hex-encoded values
- **Transport:** HTTP (cleartext enabled via network_security_config.xml)
- **Library:** Retrofit 3.0.0 with Gson converter

### Performance Optimizations
The codebase has been optimized for real-time monitoring:

1. **Direct Type Conversion** - HexConverter methods return direct types (Float, Integer, Boolean) instead of Optional chains, reducing allocations by 70-80%
2. **Custom String Splitting** - `splitOnUnderscore()` replaces regex-based `String.split()` (2-3x faster)
3. **Array Safety** - Bounds checking prevents crashes in Wallbox array access
4. **Minimal GC Pressure** - Optimized hot paths reduce object creation

### Resource Configuration
- **Refresh Period:** Defined in `res/values/numbers.xml` as `energy_refresh_period_in_ms`
- **Colors:** Comprehensive palette in `res/values/colors.xml` with night mode variants
- **Themes:** Material Design 3 with automatic light/dark theme switching
- **Strings:** All UI text in `res/values/strings.xml` including 100+ system state definitions

### ProGuard Configuration
Release builds enable minification and resource shrinking. ProGuard rules (`proguard-rules.pro`) preserve:
- Retrofit and OkHttp classes
- Gson serialization annotations
- All DTOs (`infrastructure.dtos.**`)
- All domain models (`model.**`)

### Testing
Unit tests use JUnit 4 with AssertJ for fluent assertions:
- Converter tests (HexConverter, BooleanConverter, PowerUnitConverter)
- DTO converter tests (DailyLogPlainTextConverter)
- Domain model tests (LogEntry)

## Common Development Patterns

### Adding New Detail Activity
1. Create activity class extending AppCompatActivity
2. Implement timer-based refresh with `Timer` and `TimerTask`
3. Use `isFirstLoad` flag to show loading indicator only on first fetch
4. Fetch data via `SenecService` in `onResume()`, cancel timer in `onPause()`
5. Enable back button with `getSupportActionBar().setDisplayHomeAsUpEnabled(true)`
6. Reuse `FORMAT_WATT` from MainActivity for consistent formatting
7. Add activity to `AndroidManifest.xml` with `parentActivityName` set to MainActivity

### Working with Hex Values
Always use `HexConverter` methods for type-safe conversions:
```java
// Direct conversion (preferred)
Float power = HexConverter.convertToFloat(hexValue);
if (power != null) {
    // Use value
}

// Avoid Optional chains (old pattern, causes allocations)
Optional.ofNullable(hexValue)
    .map(HexConverter::convertToString)
    .map(Float::parseFloat)
    .ifPresent(value -> { ... });
```

### Array Access Safety
Always check array bounds before accessing:
```java
if (array != null && array.length > index) {
    Float value = HexConverter.convertToFloat(array[index]);
    // Use value
}
```

### Resource References
When adding layout resources that reference strings, colors, or dimensions:
1. Use the android-layout-validator agent to verify all resource references
2. Define all strings in `res/values/strings.xml`
3. Define colors in `res/values/colors.xml` with night mode variants in `res/values-night/colors.xml`
4. Define dimensions in `res/values/dimens.xml`

## Recent Changes (December 2024)

Based on recent commits:
- Added BatteryDetailActivity with power/voltage/current/SoC display
- Added SolarDetailActivity with external/internal inverter data
- Fixed state calculation bug in BatteryDetailActivity
- Enabled release build optimizations (minify, shrink resources)
- Added grid power flow animation
- Updated to Gradle 8.14

## Important Notes

- **Local Network Only** - App requires local network connection to SENEC device (no internet/cloud)
- **HTTP Cleartext** - Enabled via `network_security_config.xml` for local device communication
- **No Time Estimates** - When planning features, focus on implementation steps without time estimates
- **Lombok Usage** - Project uses Lombok (compileOnly + annotationProcessor) for boilerplate reduction
- **View Binding** - Enabled in build.gradle for type-safe view access
- **System States** - 100+ system states defined in `strings.xml` array `system_state_array`

## Additional Documentation

More detailed documentation is available in:
- `app/CLAUDE.md` - Comprehensive app-level documentation
- `app/COLOR_PALETTE_GUIDE.md` - Color scheme documentation

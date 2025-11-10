# Color Palette Guide - Local SENEC Android App

## Overview

This guide explains the three Material Design color palettes created for the Local SENEC Android
app, based on color theory principles.

---

## 🎨 Palette 1: SENEC Blue Theme (Default)

**Primary Color:** `#0099CC` (SENEC Blue from logo)
**Theme:** Energy, Technology, Trust

### Color Relationships

#### Primary Scale (Blue)

- **50-400:** Light variants for backgrounds, cards, disabled states
- **500 (`senec_blue_500`):** Main brand color - use for primary buttons, app bar
- **600-900:** Dark variants for emphasis, pressed states, text on light backgrounds

#### Complementary (Orange `#FF9933`)

- **Use for:** Call-to-action buttons, important alerts, highlighting energy consumption
- **Why:** Creates high contrast with blue, draws attention

#### Analogous (Teal `#00BCD4`)

- **Use for:** Secondary actions, accent elements, success states
- **Why:** Harmonious with blue, creates calm professional feel

#### Triadic

- **Green (`#4CAF50`):** Success messages, positive energy flow, battery charging
- **Red (`#F44336`):** Error states, warnings, critical alerts

### Usage Examples

```xml
<!-- Primary Button -->
<Button android:background="@color/senec_blue_500" android:textColor="@color/white" />

    <!-- Accent FAB -->
<com.google.android.material.floatingactionbutton.FloatingActionButton
app:backgroundTint="@color/senec_orange_500" />

    <!-- Energy Status Card -->
<CardView app:cardBackgroundColor="@color/senec_blue_50" />
```

---

## 💜 Palette 2: Purple Theme

**Primary Color:** `#9C27B0` (Material Purple)
**Theme:** Modern, Premium, Innovation

### Color Relationships

#### Primary Scale (Purple)

- **500 (`purple_500`):** Main purple for premium feel
- Use lighter shades (100-300) for backgrounds
- Use darker shades (700-900) for text and emphasis

#### Complementary (Yellow-Green `#CDDC39`)

- **Use for:** Accent buttons, highlighting data points
- **Why:** Maximum contrast with purple

#### Analogous

- **Deep Purple (`#673AB7`):** Harmonious variant for gradients
- **Pink (`#E91E63`):** Feminine accent, romantic touch

#### Triadic

- **Orange (`#FF9800`):** Energetic accent
- **Teal (`#009688`):** Cool balance

### When to Use

- Premium/pro version of the app
- Night mode theme
- User preference option

---

## 🔵 Palette 3: Navy/Dark Blue Theme

**Primary Color:** `#3F51B5` (Indigo)
**Theme:** Professional, Stable, Corporate

### Color Relationships

#### Primary Scale (Navy/Indigo)

- **500 (`navy_500`):** Professional corporate blue
- Softer than SENEC blue, more sophisticated

#### Complementary (Amber `#FFC107`)

- **Use for:** Warnings, highlights, call-to-action
- **Why:** Strong contrast, warm vs cool

#### Analogous

- **Blue (`#2196F3`):** Modern tech feel
- **Deep Purple (`#673AB7`):** Adds depth

#### Triadic

- **Red (`#E53935`):** Alerts and errors
- **Cyan (`#00BCD4`):** Info and success

### When to Use

- Corporate/enterprise installations
- Professional settings
- Dark theme variant

---

## 🎯 Semantic Colors (All Themes)

### Status Colors

```xml

<color name="success">#4CAF50</color>     <!-- Green: Success, battery charged -->
<color name="warning">#FF9800</color>     <!-- Orange: Warnings, attention needed -->
<color name="error">#F44336</color>       <!-- Red: Errors, critical issues -->
<color name="info">#2196F3</color>        <!-- Blue: Information, tips -->
```

### Energy-Specific Colors

```xml

<color name="solar_yellow">#FDD835</color>       <!-- Solar energy generation -->
<color name="battery_green">#66BB6A</color>      <!-- Battery charging/healthy -->
<color name="grid_blue">#42A5F5</color>          <!-- Grid power flow -->
<color name="consumption_orange">#FFA726</color> <!-- Energy consumption -->
```

### Usage in Energy UI

```xml
<!-- Solar Panel Icon -->
<ImageView android:tint="@color/solar_yellow" />

    <!-- Battery Status -->
<ProgressBar android:progressTint="@color/battery_green" />

    <!-- Grid Power Indicator -->
<TextView android:textColor="@color/grid_blue" />

    <!-- Consumption Meter -->
<View android:background="@color/consumption_orange" />
```

---

## 📐 Material Design Guidelines

### Text Contrast

- **Light backgrounds:** Use colors 700-900 for text
- **Dark backgrounds:** Use colors 50-200 for text
- **Primary color backgrounds:** Use white text

### Elevation & Shadows

- Use lighter shades (50-100) for elevated surfaces
- Use darker shades (800-900) for shadows on light themes

### Color Intensity

- **Primary actions:** 500 shade
- **Hover states:** 400 shade
- **Pressed states:** 600 shade
- **Disabled states:** 200 shade with 38% opacity

---

## 🔄 Switching Themes

### In themes.xml

```xml
<!-- SENEC Blue Theme (Default) -->
<style name="Theme.RealtimeSenecApp" parent="Theme.MaterialComponents.Light">
    <item name="colorPrimary">@color/senec_blue_500</item>
    <item name="colorPrimaryVariant">@color/senec_blue_700</item>
    <item name="colorSecondary">@color/senec_teal_500</item>
    <item name="colorSecondaryVariant">@color/senec_teal_700</item>
    <item name="colorAccent">@color/senec_orange_500</item>
</style>

    <!-- Purple Theme -->
<style name="Theme.RealtimeSenecApp.Purple">
<item name="colorPrimary">@color/purple_500</item>
<item name="colorPrimaryVariant">@color/purple_700</item>
<item name="colorSecondary">@color/purple_analog_red_500</item>
<item name="colorAccent">@color/purple_comp_500</item>
</style>

    <!-- Navy Theme -->
<style name="Theme.RealtimeSenecApp.Navy">
<item name="colorPrimary">@color/navy_500</item>
<item name="colorPrimaryVariant">@color/navy_700</item>
<item name="colorSecondary">@color/navy_analog_blue_500</item>
<item name="colorAccent">@color/navy_comp_500</item>
</style>
```

---

## 🎨 Color Theory Principles Used

### Complementary Colors

Colors opposite on the color wheel create maximum contrast and visual interest.

- Blue ↔ Orange (SENEC)
- Purple ↔ Yellow-Green
- Navy ↔ Amber

### Analogous Colors

Adjacent colors create harmonious, pleasing combinations.

- Blue → Teal (SENEC)
- Purple → Deep Purple → Pink
- Navy → Blue → Purple

### Triadic Colors

Three colors equally spaced (120°) create vibrant, balanced schemes.

- Blue, Green, Red (SENEC)
- Purple, Orange, Teal
- Navy, Red, Cyan

---

## 📱 Quick Reference

| Theme          | Primary          | Secondary               | Accent             | Use Case                |
|----------------|------------------|-------------------------|--------------------|-------------------------|
| **SENEC Blue** | `senec_blue_500` | `senec_teal_500`        | `senec_orange_500` | Default, Energy, Tech   |
| **Purple**     | `purple_500`     | `purple_analog_red_500` | `purple_comp_500`  | Premium, Modern         |
| **Navy**       | `navy_500`       | `navy_analog_blue_500`  | `navy_comp_500`    | Professional, Corporate |

---

## 💡 Best Practices

1. **Consistency:** Stick to one palette throughout the app
2. **Accessibility:** Ensure 4.5:1 contrast ratio for text (WCAG AA)
3. **Context:** Use semantic colors (success/error) consistently
4. **Energy UI:** Use energy-specific colors for intuitive understanding
5. **Branding:** Keep SENEC blue as default to maintain brand identity

---

## 🔗 Resources

- [Material Design Color System](https://material.io/design/color)
- [Color Contrast Checker](https://webaim.org/resources/contrastchecker/)
- [Material Color Tool](https://material.io/resources/color/)
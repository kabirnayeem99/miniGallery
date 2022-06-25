package io.github.kabirnayeem99.minigallery.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PURPLE_HEART,
    onPrimary = WHITE,
    primaryContainer = PURPLE_CHALK,
    onPrimaryContainer = PURPLE_RIPE_PLUM,
    secondary = PURPLE_SALT_BOX,
    onSecondary = WHITE,
    secondaryContainer = PURPLE_CHALK,
    onSecondaryContainer = PURPLE_REVOLVER,
    tertiary = COPPER_RUST,
    onTertiary = WHITE,
    tertiaryContainer = PEACH_SCHNAPPS,
    onTertiaryContainer = PURPLE_BULGARIAN_ROSE,
    error = RED_THUNDERBIRD,
    onError = WHITE,
    errorContainer = PEACH_SCHNAPPS,
    onErrorContainer = RED_TEMPTRESS,
    background = WHITE_TUTU,
    onBackground = BLACK_THUNDER,
    surface = WHITE_TUTU,
    onSurface = BLACK_THUNDER,
    surfaceVariant = PURPLE_TWILIGHT,
    onSurfaceVariant = GREY_GRAVEL,
    outline = GREY_FEDORA,
    inverseSurface = BLACK_THUNDER,
    inverseOnSurface = PEACH_SOFT,
    inversePrimary = PURPLE_MAUVE,
)


private val DarkColorScheme = darkColorScheme(
    primary = PURPLE_MAUVE,
    onPrimary = PURPLE_PIGMENT_INDIGO,
    primaryContainer = PURPLE_PURE,
    onPrimaryContainer = PURPLE_CHALK,
    secondary = PURPLE_PRELUDE,
    onSecondary = PURPLE_BOSSANOVA,
    secondaryContainer = PURPLE_VOODOO,
    onSecondaryContainer = PURPLE_CHALK,
    tertiary = MAGENTA_CORNFLOWER_LILAC,
    onTertiary = BROWN_ESPRESSO,
    tertiaryContainer = RED_NUTMEG,
    onTertiaryContainer = PEACH_SCHNAPPS,
    error = MAGENTA_CORNFLOWER_LILAC,
    onError = ROSE_REDWOOD,
    errorContainer = RED_SANGRIA,
    onErrorContainer = MAGENTA_CORNFLOWER_LILAC,
    background = BLACK_THUNDER,
    onBackground = MAGENTA_BON_JOUR,
    surface = BLACK_THUNDER,
    onSurface = MAGENTA_BON_JOUR,
    surfaceVariant = GREY_GRAVEL,
    onSurfaceVariant = PALE_SLATE,
    outline = MAGENTA_VENUS,
    inverseSurface = MAGENTA_BON_JOUR,
    inverseOnSurface = BLACK_THUNDER,
    inversePrimary = PURPLE_HEART,
)


@Composable
fun MiniGalleryTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    // Checking if the device is running on Android 10 or above.
    // If it is, then it will use the dynamic color scheme.
    val isDynamicColorSupported = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    // Checking if the theme is dark or light and then setting the color scheme accordingly.
    val colorScheme = when {
        isDynamicColorSupported && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        isDynamicColorSupported && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = shapes
    )
}
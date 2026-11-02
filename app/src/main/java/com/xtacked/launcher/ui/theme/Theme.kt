package com.xtacked.launcher.ui.theme

import androidx.compose.runtime.Composable
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

private val XtackedColorScheme = darkColorScheme(
    primary = XtackedPrimary,
    onPrimary = XtackedOnPrimary,
    primaryContainer = XtackedPrimaryDark,
    onPrimaryContainer = XtackedOnPrimary,
    secondary = XtackedTextSecondary,
    onSecondary = XtackedBg,
    background = XtackedBg,
    onBackground = XtackedTextPrimary,
    surface = XtackedSurface,
    onSurface = XtackedTextPrimary,
    surfaceVariant = XtackedSurfaceHigh,
    onSurfaceVariant = XtackedTextSecondary,
    border = XtackedTextTertiary
)

@Composable
fun XtackedTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = XtackedColorScheme,
        content = content
    )
}

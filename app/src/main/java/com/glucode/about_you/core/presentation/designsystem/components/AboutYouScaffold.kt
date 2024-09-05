package com.glucode.about_you.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutYouScaffold(
    modifier: Modifier = Modifier,
    withBackground: Boolean = true,
    topAppBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topAppBar,
        modifier = modifier
    ) { padding ->
        if (withBackground) {
            Background {
                content(padding)
            }
        } else {
            content(padding)
        }
    }
}
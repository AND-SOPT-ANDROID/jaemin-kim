package org.sopt.and.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.home.components.HomeBannerPager
import org.sopt.and.home.components.HomeTopBar
import org.sopt.and.ui.theme.Grey100

@Composable
fun HomeScreen(
    innerPadding: PaddingValues
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey100)
            .padding(innerPadding)
    ) {
        HomeTopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            HomeBannerPager()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Scaffold { innerPadding ->
        HomeScreen(innerPadding = innerPadding)
    }
}
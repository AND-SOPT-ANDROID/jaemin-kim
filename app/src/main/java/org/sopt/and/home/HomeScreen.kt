package org.sopt.and.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.home.components.HomeBannerPager
import org.sopt.and.home.components.HomeBottomCoupon
import org.sopt.and.home.components.HomeTopBar
import org.sopt.and.home.components.RecommendList
import org.sopt.and.home.components.Top20List
import org.sopt.and.ui.theme.Grey100

@Composable
fun HomeScreen(
    innerPadding: PaddingValues
) {
    val scrollState = rememberScrollState()

    val homeViewModel = viewModel<HomeViewModel>()
    val homeUiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey100)
            .padding(innerPadding)
    ) {
        HomeTopBar(genres = homeUiState.genres)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            HomeBannerPager(homeUiState.banners)

            Spacer(modifier = Modifier.height(20.dp))

            RecommendList(
                title = stringResource(R.string.home_picks_of_editor_title),
                items = homeUiState.recommends
            )

            Spacer(modifier = Modifier.height(20.dp))

            Top20List(homeUiState.rankers)
        }

        HomeBottomCoupon()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Scaffold { innerPadding ->
        HomeScreen(innerPadding = innerPadding)
    }
}
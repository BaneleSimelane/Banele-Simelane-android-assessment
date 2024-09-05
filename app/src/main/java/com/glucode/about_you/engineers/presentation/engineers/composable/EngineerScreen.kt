package com.glucode.about_you.engineers.presentation.engineers.composable

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.glucode.about_you.R
import com.glucode.about_you.core.presentation.designsystem.components.AboutYouScaffold
import com.glucode.about_you.core.presentation.designsystem.components.ProfileCard
import com.glucode.about_you.core.presentation.designsystem.components.Toolbar
import com.glucode.about_you.core.presentation.designsystem.components.ui.ObserveAsEvents
import com.glucode.about_you.core.presentation.designsystem.util.DropDownItems
import com.glucode.about_you.engineers.domain.util.EngineerOrder
import com.glucode.about_you.engineers.domain.util.OrderType
import com.glucode.about_you.engineers.presentation.engineers.EngineerAction
import com.glucode.about_you.engineers.presentation.engineers.EngineerEvent
import com.glucode.about_you.engineers.presentation.engineers.EngineerState
import com.glucode.about_you.engineers.presentation.engineers.EngineerViewModel

@Composable
fun EngineerScreenRoot(
    onNavigatingToAbout: (String) -> Unit,
    viewModel: EngineerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.event) { event ->
        when (event) {
            is EngineerEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {}
        }
    }
    EngineerScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is EngineerAction.OnEngineerClick -> {
                    onNavigatingToAbout(
                        action.selectedName
                    )
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EngineerScreen(
    state: EngineerState,
    onAction: (EngineerAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    AboutYouScaffold(
        topAppBar = {
            Toolbar(
                showBackButton = false,
                title = stringResource(id = R.string.engineers_screen_title),
                menuItems = listOf(
                    DropDownItems(title = stringResource(id = R.string.engineers_menu_years)),
                    DropDownItems(title = stringResource(id = R.string.engineers_menu_coffees)),
                    DropDownItems(title = stringResource(id = R.string.engineers_menu_bugs))
                ),
                onMenuIClick = {
                    onAction(EngineerAction.OnToggleOrderSection)
                },
                onMenuItemClick = {
                    when(it) {
                        0 -> {
                            val order = EngineerOrder.Years(OrderType.Descending)
                            onAction(EngineerAction.Order(order))
                        }
                        1 -> {
                            val order = EngineerOrder.Coffee(OrderType.Descending)
                            onAction(EngineerAction.Order(order))
                        }
                        2 -> {
                            val order = EngineerOrder.Bugs(OrderType.Descending)
                            onAction(EngineerAction.Order(order))
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.engineers) { engineers ->
                    ProfileCard(
                        isSimple = true,
                        imageSize = 50.dp,
                        cardHeight = 90.dp,
                        engineerData = engineers,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAction(EngineerAction.OnEngineerClick(
                                   engineers.name
                                ))
                            }
                            .testTag("Profile Card") //can create a string resource
                    )
                }
            }
        }
    )
}
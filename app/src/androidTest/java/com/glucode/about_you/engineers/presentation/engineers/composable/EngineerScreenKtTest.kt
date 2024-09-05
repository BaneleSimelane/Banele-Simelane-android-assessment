package com.glucode.about_you.engineers.presentation.engineers.composable

import android.content.Context
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.glucode.about_you.MainActivity
import com.glucode.about_you.R
import com.glucode.about_you.core.presentation.designsystem.components.Theme
import com.glucode.about_you.di.AboutYouModule
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.model.QuickStatsData
import com.glucode.about_you.engineers.legacy.models.Engineer
import com.glucode.about_you.engineers.legacy.models.QuickStats
import com.glucode.about_you.engineers.presentation.engineers.EngineerState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AboutYouModule::class)
class EngineerScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeEngineers: List<EngineerData>

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        hiltRule.inject()

        fakeEngineers = listOf(
            EngineerData(
                name = "Banele Simelane", role = "Android dev", defaultImageName = "",
                quickStats = QuickStatsData(years = 2, bugs = 23, coffees = 32)
            ),
            EngineerData(
                name = "Banele Goodenough", role = "QA", defaultImageName = "",
                quickStats = QuickStatsData(years = 2, bugs = 23, coffees = 32)
            )
        )

        composeRule.setContent {
            val navController = rememberNavController()

            Theme {
                NavHost(
                    navController = navController,
                    startDestination = "engineer"
                ) {
                    composable(route = "engineer") {
                        EngineerScreen(
                            state = EngineerState(engineers = fakeEngineers),
                            onAction = {}
                        )
                    }
                }
            }
        }
    }


    @Test
    fun testIsTopBarMenuClickable() {
        val menuIcon = composeRule.onNodeWithContentDescription(
             context.getString(R.string.dropdown_menu)
        )

        menuIcon.performClick()
    }

    @Test
    fun testIsProfileCardClickable() {
        val card = composeRule.onNodeWithTag("Profile Card")
        card.assertIsDisplayed()
        card.performClick()
    }

    @Test
    fun testIfTitleVisible() {
        composeRule.onNodeWithText(
            context.getString(R.string.engineers_screen_title)
        ).assertIsDisplayed()
    }
}
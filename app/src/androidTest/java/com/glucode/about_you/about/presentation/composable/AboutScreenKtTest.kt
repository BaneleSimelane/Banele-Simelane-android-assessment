package com.glucode.about_you.about.presentation.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.about.domain.model.AnswersData
import com.glucode.about_you.about.domain.model.QuestionData
import com.glucode.about_you.about.presentation.AboutState
import com.glucode.about_you.core.presentation.designsystem.components.Theme
import com.glucode.about_you.di.AboutYouModule
import com.glucode.about_you.engineers.domain.model.QuickStatsData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AboutYouModule::class)
class AboutScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeAbout: AboutData

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        hiltRule.inject()

        fakeAbout = AboutData(
                name = "Banele", role = "Android dev", defaultImageName = "",
                quickStats = QuickStatsData(years = 2, bugs = 23, coffees = 32),
                questions = listOf(
                    QuestionData(questionText = "What?", answerOptions = listOf("Yes", "No", "Yeah"), answer = AnswersData(
                        text = "No", index = 1
                    ))
                )
            )


        composeRule.setContent {
            val navController = rememberNavController()

            Theme {
                NavHost(
                    navController = navController,
                    startDestination = "about?name=Banele"
                ) {
                    composable(route = "about?name=Banele") {
                        AboutScreen(
                            state = AboutState(about = fakeAbout),
                            onAction = {}
                        )
                    }
                }
            }
        }
    }

    @Test
    fun isTestTopBarVisible() {
        composeRule.onNodeWithContentDescription(
            context.getString(R.string.go_back)
        ).assertIsDisplayed()
            .performClick()

        composeRule.onNodeWithContentDescription(
            context.getString(R.string.about_screen_title)
        ).assertIsDisplayed()
    }

    @Test
    fun testProfileCardVisibility() {
        val card = composeRule.onNodeWithTag("Profile Card")
        card.assertIsDisplayed().performClick()
    }

    @Test
    fun testFactItemQuestionVisible() {
        fakeAbout.questions.random().questionText.let {
            composeRule.onNodeWithText(
                it
            )
        }.assertIsDisplayed()
    }

    @Test
    fun testFactItemsAnswerVisible() {
        fakeAbout.questions.random().answer.text?.let {
            composeRule.onNodeWithText(
                it
            )
        }?.assertIsDisplayed()
    }
}
package com.glucode.about_you.about.domain.usecase

import com.glucode.about_you.about.data.repository.FakeAboutRepository
import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.about.domain.model.AnswersData
import com.glucode.about_you.about.domain.model.QuestionData
import com.glucode.about_you.engineers.domain.model.QuickStatsData
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class GetEngineerTest {
    private lateinit var getEngineer: GetEngineer
    private lateinit var fakeAboutRepository: FakeAboutRepository

    private var fakeAbout: AboutData? = null

    @Before
    fun setUp() {
        fakeAboutRepository = FakeAboutRepository()
        getEngineer = GetEngineer(fakeAboutRepository)

        fakeAbout = AboutData(
            name = "Banele", role = "Android dev", defaultImageName = "",
            quickStats = QuickStatsData(years = 2, bugs = 23, coffees = 32),
            questions = listOf(
                QuestionData(questionText = "What?", answerOptions = listOf("Yes", "No", "Yeah"), answer = AnswersData(
                    text = "No", index = 1
                )
                )
            )
        )
    }

    @Test
    fun `Get about of an engineer by name, correct engineer`() = runBlocking {
        val about = getEngineer.invoke("Banele")

        if (about != null) {
            assertThat(about.name, about.name == "Banele")
        }
    }
}
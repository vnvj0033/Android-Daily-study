package com.example.mvctutorial.uitest

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mvctutorial.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTestActivityTest{

    @get: Rule
    val activityRule: ActivityTestRule<UiTestActivity> = ActivityTestRule(UiTestActivity::class.java, false, false)

    @Test
    fun noCountryExtra() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.text_view))
            .check(matches(withText("korea")))

        onView(withId(R.id.button))
            .perform(click())

        onView(withId(R.id.text_view))
            .check(matches(withText("KOR")))
    }
}

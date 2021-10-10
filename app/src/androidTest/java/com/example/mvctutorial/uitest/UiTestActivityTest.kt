package com.example.mvctutorial.uitest

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mvctutorial.R
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTestActivityTest {

    @get: Rule
    private val activityRule: ActivityTestRule<UiTestActivity> =
        ActivityTestRule(UiTestActivity::class.java, false, false)
    private val activityScenario: ActivityScenario<UiTestActivity> =
        ActivityScenario.launch(UiTestActivity::class.java)

    // intent 사용 방법
//    val activityScenario = ActivityScenario.launch<UiTestActivity>(
//        Intent(ApplicationProvider.getApplicationContext(), UiTestActivity::class.java).apply {
//            putExtra("MyArgs", "Nothing")
//        }
//    )

    @get:Rule
    private val activityScenarioRule: ActivityScenarioRule<UiTestActivity> =
        ActivityScenarioRule(
            Intent(
                ApplicationProvider.getApplicationContext(),
                UiTestActivity::class.java
            ).apply {
                putExtra("MyArgs", "Nothing")
            })

    @Before
    fun setupData() {
        activityRule.launchActivity(Intent().apply { putExtra("MyArg", "Nothing") })
        activityRule.runOnUiThread { activityRule.activity.run {} }

        activityScenario.onActivity {
            it.findViewById<TextView>(R.id.text_view)?.let { }
        }

        activityScenarioRule.scenario.onActivity {
            (it.findViewById<TextView>(R.id.text_view))?.let { }
        }
    }

    @Test
    fun resultTest() {
        activityScenarioRule.scenario.onActivity {
            it.setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("Result", "Ok")
            })
            it.finish()
        }
        Assert.assertEquals(activityScenarioRule.scenario.result.resultCode, Activity.RESULT_OK)
        val result = activityScenarioRule.scenario.result.resultData?.extras?.getString("Result")
        Assert.assertEquals(result, "Ok")
    }

    @Test
    fun moveToStateTest() {
        activityScenarioRule.scenario?.let { activityScenario ->
            activityScenario.moveToState(Lifecycle.State.STARTED)
            Assert.assertEquals(activityScenario.state, Lifecycle.State.STARTED)
            activityScenario.moveToState(Lifecycle.State.CREATED)
            Assert.assertEquals(activityScenario.state, Lifecycle.State.CREATED)
            activityScenario.moveToState(Lifecycle.State.RESUMED)
            Assert.assertEquals(activityScenario.state, Lifecycle.State.RESUMED)
            activityScenario.moveToState(Lifecycle.State.DESTROYED)
            Assert.assertEquals(activityScenario.state, Lifecycle.State.DESTROYED)
            activityScenario.recreate()
        }
    }

    @Test
    fun testActivityState() {
        activityScenario.moveToState(Lifecycle.State.STARTED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.STARTED)

        activityScenario.moveToState(Lifecycle.State.CREATED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.CREATED)

        activityScenario.moveToState(Lifecycle.State.RESUMED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.RESUMED)

        activityScenario.moveToState(Lifecycle.State.DESTROYED)
        Assert.assertEquals(activityScenario.state, Lifecycle.State.DESTROYED)

        activityScenario.recreate()
    }


    @Test
    fun testWithId() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.text_view))
            .check(matches(withText("korea")))

        onView(withId(R.id.button))
            .perform(click())

        onView(withId(R.id.text_view))
            .check(matches(withText("KOR")))
    }

    @Test
    fun testWithText() {
        activityRule.launchActivity(Intent())

        onView(withText("Button"))
            .perform(click())

        onView(withId(R.id.text_view))
            .check(matches(isDisplayed()))
            .check(matches(withText("KOR")))
    }

    @After
    fun close() {
        activityScenario.close()
    }
}
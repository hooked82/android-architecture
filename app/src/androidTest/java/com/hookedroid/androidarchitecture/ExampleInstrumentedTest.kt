package com.hookedroid.androidarchitecture


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hookedroid.androidarchitecture.di.DaggerAppComponent
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as ArchApplication
        val appComponent = DaggerAppComponent.builder().appModule(AppModuleMock()).application(app).build()

        app.setAppComponent(appComponent)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        assertEquals("com.hookedroid.androidarchitecture", appContext.packageName)
    }
}

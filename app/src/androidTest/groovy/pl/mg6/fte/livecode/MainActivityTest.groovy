package pl.mg6.fte.livecode

import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.test.ActivityInstrumentationTestCase2
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dagger.Injector

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText

@CompileStatic
final class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivityTest() {
        super(MainActivity)
    }

    public void testShowsInfoAboutUser() throws Exception {
        Injector.setTestModules(new SuccessTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        //SystemClock.sleep(10000)
        onView withId(R.id.main_text) check matches(withText('Maciej Górski, EL Passion, Poland'))
    }

    public void testShowsError() throws Exception {
        Injector.setTestModules(new ErrorTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        //SystemClock.sleep(10000)
        onView withId(R.id.main_text) check matches(withText('Our experts know nothing about this. It was as likely to happen as intelligent life on Earth.'))
    }
}

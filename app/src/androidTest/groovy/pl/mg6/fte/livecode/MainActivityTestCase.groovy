package pl.mg6.fte.livecode

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dagger.Injector

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText

@CompileStatic
final class MainActivityTestCase extends BaseTestCase {

    void testShowUserInfo() {
        Injector.setTestModules(new HappyFlowTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        //sleep(10000)
        onView withId(R.id.main_text) check matches(withText('Maciej Górski, EL Passion, Poland'))
    }

    void testShowNetworkErrorMessage() {
        Injector.setTestModules(new UnhappyFlowTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        //sleep(10000)
        onView withId(R.id.main_text) check matches(withText(R.string.network_error))
    }
}

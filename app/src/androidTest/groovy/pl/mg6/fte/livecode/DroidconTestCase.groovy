package pl.mg6.fte.livecode

import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dagger.ErrorTestModule
import pl.mg6.fte.livecode.dagger.Injector
import pl.mg6.fte.livecode.dagger.SuccessTestModule

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText

@CompileStatic
final class DroidconTestCase extends BaseTestCase {

    public void testShowsFullInfo() {
        Injector.setTestModules(new SuccessTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6'), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText('Maciej, EL'))
    }

    public void testShowsNetworkErrorMessage() {
        Injector.setTestModules(new ErrorTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6'), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText('Error occurred during network operation.'))
    }
}

package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dagger.Injector
import pl.mg6.fte.livecode.dto.User
import rx.Observable

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText
import static retrofit.RetrofitError.networkError
import static rx.Observable.error
import static rx.Observable.just

@CompileStatic
final class MainActivityTestCase extends BaseTestCase {

    void testShouldShowUser() {
        Injector.testModules = new HappyTestModule()
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText('Maciej Górski, EL Passion, Poland'))
    }

    void testShouldShowError() {
        Injector.testModules = new UnhappyTestModule()
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText(R.string.network_error))
    }

    @Module(library = true, overrides = true)
    static class HappyTestModule {

        @Provides
        GithubApi provideApi() {
            return new GithubApi() {

                @Override
                Observable<User> call(String name) {
                    return just(new User(name: 'Maciej Górski', company: 'EL Passion', location: 'Poland'))
                }
            }
        }
    }

    @Module(library = true, overrides = true)
    static class UnhappyTestModule {

        @Provides
        GithubApi provideApi() {
            return {
                return error(networkError(null, new IOException()))
            } as GithubApi
        }
    }
}

package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dagger.Injector
import pl.mg6.fte.livecode.dto.User
import retrofit.RetrofitError
import rx.Observable

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText

@CompileStatic
final class MainActivityTestCase extends BaseTestCase {

    void testShouldDisplayMaciej() {
        Injector.setTestModules(new HappyTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText('mg6maciej'), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText('Maciej Górski, EL Passion, Poland'))
    }

    void testShouldDisplaySadError() {
        Injector.setTestModules(new SadTestModule())
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
                    return Observable.just(new User(name: 'Maciej Górski', location: 'Poland', company: 'EL Passion'))
                }
            }
        }
    }

    @Module(library = true, overrides = true)
    static class SadTestModule {

        @Provides
        GithubApi provideApi() {
            return {
                return Observable.error(RetrofitError.networkError(null, new IOException()))
            } as GithubApi
        }
    }
}

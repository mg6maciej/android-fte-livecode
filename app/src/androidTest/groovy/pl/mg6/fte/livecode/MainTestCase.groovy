package pl.mg6.fte.livecode

import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dagger.Injector
import pl.mg6.fte.livecode.dto.User
import retrofit.http.Path
import rx.Observable

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId
import static android.support.test.espresso.matcher.ViewMatchers.withText

@CompileStatic
final class MainTestCase extends BaseTestCase {

    void testShouldShowUser() {
        Injector.setTestModules(new HappyTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText("mg6maciej"), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText("Maciej Górski, EL Passion, Poland"))
    }

    void testShouldShowNetworkError() {
        Injector.setTestModules(new SadTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText("mg6maciej"), pressImeActionButton()
        onView withId(R.id.main_text) check matches(withText(R.string.unknown_error))
    }

    @Module(
            overrides = true,
            library = true
    )
    class HappyTestModule {

        @Provides
        GithubApi provideGithubApi() {
            return new GithubApi() {

                @Override
                Observable<User> call(String name) {
                    return Observable.just(new User(name: "Maciej Górski", location: "Poland", company: "EL Passion"))
                }
            }
        }
    }

    @Module(
            overrides = true,
            library = true
    )
    class SadTestModule {

        @Provides
        GithubApi provideGithubApi() {
            return new GithubApi() {

                @Override
                Observable<User> call(String name) {
                    return Observable.error(new IOException())
                }
            }
        }
    }
}

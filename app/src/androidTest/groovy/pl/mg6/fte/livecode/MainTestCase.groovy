package pl.mg6.fte.livecode

import android.support.test.espresso.matcher.ViewMatchers
import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dagger.Injector
import pl.mg6.fte.livecode.dto.User
import retrofit.RetrofitError
import retrofit.http.Path
import rx.Observable

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.action.ViewActions.pressImeActionButton
import static android.support.test.espresso.action.ViewActions.typeText
import static android.support.test.espresso.assertion.ViewAssertions.matches
import static android.support.test.espresso.matcher.ViewMatchers.withId

@CompileStatic
final class MainTestCase extends BaseTestCase {

    void testShouldShowUser() {
        Injector.setTestModules(new HappyTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText("mg6maciej"), pressImeActionButton()
        //SystemClock.sleep(20000)
        onView withId(R.id.main_text) check matches(ViewMatchers.withText("Maciej Górski, EL Passion, Poland"))
    }

    void testShouldShowNetworkError() {
        Injector.setTestModules(new SadTestModule())
        getActivity()
        onView withId(R.id.main_edit) perform typeText("mg6maciej"), pressImeActionButton()
        //SystemClock.sleep(20000)
        onView withId(R.id.main_text) check matches(ViewMatchers.withText(R.string.network_error))
    }

    @Module(
            overrides = true,
            library = true
    )
    static class HappyTestModule {

        @Provides
        GithubApi providesApi() {
            return new GithubApi() {

                @Override
                Observable<User> call(@Path('name') String name) {
                    return Observable.just(new User(name: "Maciej Górski", company: "EL Passion", location: "Poland"))
                }
            }
        }
    }

    @Module(
            overrides = true,
            library = true
    )
    static class SadTestModule {

        @Provides
        GithubApi providesApi() {
            return new GithubApi() {

                @Override
                Observable<User> call(@Path('name') String name) {
                    return Observable.error(RetrofitError.networkError(null, new IOException()))
                }
            }
        }
    }
}

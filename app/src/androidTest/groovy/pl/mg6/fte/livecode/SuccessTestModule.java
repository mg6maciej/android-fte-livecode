package pl.mg6.fte.livecode;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.fte.livecode.api.GithubApi;
import pl.mg6.fte.livecode.dto.User;
import retrofit.http.Path;
import rx.Observable;

@Module(
        overrides = true,
        library = true
)
public class SuccessTestModule {

    @Provides
    @Singleton
    GithubApi provideGithubApi() {
        return new GithubApi() {
            @Override
            public Observable<User> call(@Path("user") String name) {
                User user = new User();
                user.setName("Maciej");
                user.setCompany("EL Passion");
                return Observable.just(user);
            }
        };
    }
}

package pl.mg6.fte.livecode.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.fte.livecode.api.GithubApi;
import pl.mg6.fte.livecode.factory.GithubApiStubFactory;

@Module(
        library = true,
        overrides = true
)
public class SuccessTestModule {

    @Provides
    @Singleton
    GithubApi provideGithubApi() {
        return GithubApiStubFactory.withSuccess();
    }
}

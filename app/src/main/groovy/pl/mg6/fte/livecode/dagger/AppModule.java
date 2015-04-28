package pl.mg6.fte.livecode.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.mg6.fte.livecode.MainActivity;
import pl.mg6.fte.livecode.api.GithubApi;
import retrofit.RestAdapter;

@Module(
        injects = MainActivity.class,
        library = true
)
public class AppModule {

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    @Provides
    @Singleton
    public GithubApi provideGithubApi(RestAdapter restAdapter) {
        return restAdapter.create(GithubApi.class);
    }
}

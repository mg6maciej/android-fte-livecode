package pl.mg6.fte.livecode.dagger

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.MainActivity
import pl.mg6.fte.livecode.api.GithubApi
import retrofit.RestAdapter

import javax.inject.Singleton

@CompileStatic
@Module(injects = MainActivity)
public class AppModule {

    @Provides
    @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint('https://api.github.com')
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
    }

    @Provides
    @Singleton
    GithubApi provideGithubApi(RestAdapter restAdapter) {
        return restAdapter.create(GithubApi)
    }
}

package pl.mg6.fte.livecode.dagger

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.MainActivity
import pl.mg6.fte.livecode.api.GithubApi
import retrofit.RequestInterceptor.RequestFacade
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
                .setRequestInterceptor(this.&onIntercept)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
    }

    private void onIntercept(RequestFacade it) {
        it.addHeader('Accept', 'application/vnd.github.v3+json')
        it.addHeader('User-Agent', 'https://github.com/mg6maciej/android-fte-livecode')
    }

    @Provides
    @Singleton
    GithubApi provideGithubApi(RestAdapter restAdapter) {
        return restAdapter.create(GithubApi)
    }
}

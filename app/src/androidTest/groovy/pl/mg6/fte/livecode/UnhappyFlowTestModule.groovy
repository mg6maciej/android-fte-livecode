package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi

import static retrofit.RetrofitError.networkError
import static rx.Observable.error

@CompileStatic
@Module(
        library = true,
        overrides = true
)
final class UnhappyFlowTestModule {

    @Provides
    GithubApi provideApi() {
        return { String name ->
            return error(networkError(null, new IOException()))
        } as GithubApi
    }
}

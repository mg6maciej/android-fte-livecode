package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User

import static rx.Observable.just

@CompileStatic
@Module(overrides = true, library = true)
final class ErrorTestModule {

    @Provides
    GithubApi provideGithubApi() {
        return {
            return just(new RuntimeException())
        } as GithubApi
    }
}

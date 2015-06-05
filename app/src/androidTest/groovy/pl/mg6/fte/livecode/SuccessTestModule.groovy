package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.Observable

import static rx.Observable.just

@CompileStatic
@Module(overrides = true, library = true)
final class SuccessTestModule {

    @Provides
    GithubApi provideGithubApi() {
        return {
            return just(new User(name: 'Maciej Górski', company: 'EL Passion', location: 'Poland'))
        } as GithubApi
    }
}

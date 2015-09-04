package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.Observable

@CompileStatic
@Module(library = true, overrides = true)
final class HappyFlowTestModule {

    @Provides
    GithubApi providesStub() {
        return {
            return Observable.just(new User(name: 'Maciej Górski', company: 'EL Passion', location: 'Poland'))
        } as GithubApi
    }
}

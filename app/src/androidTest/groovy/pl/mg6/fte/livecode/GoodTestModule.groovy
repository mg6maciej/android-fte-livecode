package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.Observable

@Module(
        overrides = true,
        library = true
)
final class GoodTestModule {

    @Provides
    GithubApi providesApi() {
        return {
            return Observable.just(new User(name: 'Maciej Górski', company: 'EL Passion', location: 'Poland'))
        } as GithubApi
    }
}

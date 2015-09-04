package pl.mg6.fte.livecode

import dagger.Module
import dagger.Provides
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import retrofit.RetrofitError
import rx.Observable

@CompileStatic
@Module(library = true, overrides = true)
final class UnhappyFlowTestModule {

    @Provides
    GithubApi providesStub() {
        return {
            return Observable.error(RetrofitError.networkError(null, new IOException()))
        } as GithubApi
    }
}

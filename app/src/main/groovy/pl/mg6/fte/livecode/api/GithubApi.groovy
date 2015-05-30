package pl.mg6.fte.livecode.api

import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dto.User
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

@CompileStatic
interface GithubApi {

    @GET('/users/{name}')
    Observable<User> call(@Path('name') String name)
}

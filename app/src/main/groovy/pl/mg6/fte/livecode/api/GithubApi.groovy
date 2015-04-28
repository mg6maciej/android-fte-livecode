package pl.mg6.fte.livecode.api

import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dto.User
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

@CompileStatic
interface GithubApi {

    @GET("/users/{user}")
    Observable<User> call(@Path("user") String user)
}

package pl.mg6.fte.livecode.factory

import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.Observable

@CompileStatic
final class GithubApiStubFactory {

    static GithubApi withSuccess() {
        return { String user ->
            return Observable.just(new User(name: "Maciej", company: "EL"))
        } as GithubApi
    }

    static GithubApi withError() {
        return { String user ->
            return Observable.error(new Exception())
        } as GithubApi
    }
}

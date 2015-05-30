package pl.mg6.fte.livecode

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor
import retrofit.RetrofitError

@CompileStatic
@TupleConstructor
enum ErrorType {

    NETWORK(R.string.network_error),
    USER_DOES_NOT_EXIST(R.string.user_does_not_exist),
    UNKNOWN(R.string.unknown_error)

    int message

    static ErrorType fromError(Throwable error) {
        if (error instanceof RetrofitError) {
            switch (error.kind) {
                case RetrofitError.Kind.NETWORK: return NETWORK
                case RetrofitError.Kind.HTTP: return USER_DOES_NOT_EXIST
            }
        }
        return UNKNOWN
    }
}

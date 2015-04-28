package pl.mg6.fte.livecode

import android.test.ActivityInstrumentationTestCase2
import groovy.transform.CompileStatic

@CompileStatic
abstract class BaseTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    BaseTestCase() {
        super(MainActivity)
    }
}

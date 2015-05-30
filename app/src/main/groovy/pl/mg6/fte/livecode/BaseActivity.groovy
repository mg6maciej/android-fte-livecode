package pl.mg6.fte.livecode

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.dagger.Injector

@CompileStatic
abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        Injector.inject(this)
    }
}

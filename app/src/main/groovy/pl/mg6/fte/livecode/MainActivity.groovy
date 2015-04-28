package pl.mg6.fte.livecode

import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.android.schedulers.AndroidSchedulers

import javax.inject.Inject

@CompileStatic
final class MainActivity extends BaseActivity {

    @Inject
    protected GithubApi githubApi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        def editText = findViewById(R.id.main_edit) as EditText
        editText.onEditorActionListener = this.&onEditorAction
    }

    private boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        def name = v.text.toString().trim()
        if (name) {
            githubApi.call(name)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this.&displayUser, this.&displayError)
        }
        return true
    }

    private void displayUser(User user) {
        def textView = findViewById(R.id.main_text) as TextView
        textView.text = user.fullInfo
    }

    private void displayError(Throwable error) {
        def textView = findViewById(R.id.main_text) as TextView
        textView.text = 'Error occurred during network operation.'
    }
}

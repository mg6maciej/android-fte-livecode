package pl.mg6.fte.livecode

import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import groovy.transform.CompileStatic
import pl.mg6.fte.livecode.api.GithubApi
import pl.mg6.fte.livecode.dto.User
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

import javax.inject.Inject

@CompileStatic
final class MainActivity extends BaseActivity {

    @Inject
    protected GithubApi githubApi
    private Subscription subscription

    private TextView textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initViews()
    }

    private void initViews() {
        def editText = findViewById(R.id.main_edit) as EditText
        editText.onEditorActionListener = this.&onEditorAction
        textView = findViewById(R.id.main_text) as TextView
    }

    private boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        def name = v.text.toString().trim()
        if (name) {
            callForUser(name)
        }
        return true
    }

    private void callForUser(String name) {
        subscription?.unsubscribe()
        subscription = githubApi.call(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.&displayUser, this.&displayError)
    }

    private void displayUser(User user) {
        textView.text = user.fullInfo ?: getString(R.string.empty_user_info_placeholder)
    }

    private void displayError(Throwable error) {
        def errorType = ErrorType.fromError(error)
        textView.setText(errorType.message)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
    }
}

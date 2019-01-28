package ithm.ru.hw2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ithm.ru.R;

public class CTMainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView changedText;
    private Observable<String> observable;
    private Observer<String> observer;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctmain);

        initUI();

        changeListener();
    }

    private void changeListener() {
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("hw2", "-d " + d);
                mDisposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d("hw2", "-s " + s);
                changedText.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("hw2", "-e " + e);
            }

            @Override
            public void onComplete() {
                Log.d("hw2", "-onComplete ");
            }
        };

    }

    private void initUI() {
        inputText = findViewById(R.id.input_text);
        changedText = findViewById(R.id.changed_text);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("hw2", "beforeTextChanged " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("hw2", "onTextChanged " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("hw2", "afterTextChanged " + s);
                observable = Observable.just(s.toString());
                observable.subscribe(observer);
            }
        });
    }

}

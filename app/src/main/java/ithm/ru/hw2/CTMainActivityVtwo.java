package ithm.ru.hw2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ithm.ru.R;

public class CTMainActivityVtwo extends AppCompatActivity {
    DisposableObserver<String> observerone;
    private EditText inputText;
    private TextView changedText;
    private Observable<String> observable;
    private Observer<String> observer;
    private Disposable mDisposable;
    private Observable<String> o;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctmain);
        initUI();
        createTextChangeObservable();

    }


    @SuppressLint("CheckResult")
    private void createTextChangeObservable() {

        observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d("hw2", "onTextChanged " + s);
                        emitter.onNext(s.toString());
                    }

                };
                inputText.addTextChangedListener(textWatcher);
            }
        });

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                changedText.setText(s);
                Log.d("hw2", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("hw2", "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d("hw2", "onCompleted");
            }
        };
        observable.subscribe(observer);
    }


    private void initUI() {
        inputText = findViewById(R.id.input_text);
        changedText = findViewById(R.id.changed_text);
    }

}

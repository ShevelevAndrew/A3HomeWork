package ithm.ru.hw3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import ithm.ru.R;

public class LFMainActivity extends AppCompatActivity {

    final String FILEJPJ = "file_jpg.jpg";
    final String FILEPNG = "file_png.png";
    final String PATH = "/data/data/ithm.ru/files/";
    private Button runButton;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lfmain);
        runButton = findViewById(R.id.btn_run_jpg);
        runButton.setOnClickListener(v -> runConvertToPNG());
    }

    private void runConvertToPNG() {
        AlertDialog alert = getAlertDialog();
        disposable = Completable.fromAction(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jpg_file);
                        FileOutputStream fileJPG = new FileOutputStream(PATH + FILEJPJ);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileJPG);
                        fileJPG.flush();
                        fileJPG.close();
                        Bitmap bitmap1 = BitmapFactory.decodeFile(PATH + FILEJPJ);
                        FileOutputStream filePNG = new FileOutputStream(PATH + FILEPNG);
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, filePNG);
                        filePNG.flush();
                        filePNG.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Log.d("MyLog", e.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("MyLog", e.toString());
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Log.d("MyLog", "onComplete");
                        alert.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private AlertDialog getAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message")
                .setMessage("Run convert")
                .setNegativeButton("Cancel convert", (dialog, which) -> disposable.dispose());
        AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }
}

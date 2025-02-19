package com.example.gesturedemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    private static TextView txtv;
    private static GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtv = (TextView) findViewById(R.id.txtView);
        gd = new GestureDetector(this,this);
        gd.setOnDoubleTapListener(this);
    }
    public boolean onTouchEvent(MotionEvent event){
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        txtv.setText("onSingleTapConfirmed: " +e);
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        txtv.setText("onDoubleTap: " +e);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        txtv.setText("onDoubleTapEvent: " +e);
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        txtv.setText("onDown: " +e);
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        txtv.setText("onShowPress: " +e);

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        txtv.setText("onSingleTapUp: " +e);
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        txtv.setText("onScroll:" +e1);
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        txtv.setText("onLongPress:" +e);

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        txtv.setText("onFling:" +e1);
        return false;
    }
}
package com.donbosco.giancarlo.focusedsingularity.Presentation.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.donbosco.giancarlo.focusedsingularity.Presentation.Presenters.TimerPresenter;
import com.donbosco.giancarlo.focusedsingularity.Presentation.Presenters.TimerView;
import com.donbosco.giancarlo.focusedsingularity.R;

import java.lang.ref.WeakReference;

public class MainActivity extends ActionBarActivity implements TimerView {

    TimerPresenter presenter;
    TextView timerText;
    EditText pomodoroCountField;
    Button startButton;
    Button stopButton;
    Handler timeHandler;
    private Handler scoreHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new TimerPresenter(new TimerExecutorImpl());
        timeHandler = new TimerTextHandler(this);
        scoreHandler = new PomodoroCountTextHandler(this);
        presenter.registerView(this);


        startButton = (Button) findViewById(R.id.start_button);
        timerText = (TextView) findViewById(R.id.timer_text);
        stopButton = (Button) findViewById(R.id.stop_button);

        pomodoroCountField = (EditText) findViewById(R.id.pomodoro_field);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.stopTimer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTimerTextView(String text) {
        timerText.setText(text);
    }

    @Override
    public void setTimerText(String timeChanged) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("time", timeChanged);
        Message msg = new Message();
        msg.setData(msgBundle);
        timeHandler.sendMessage(msg);
    }

    @Override
    public void setPomodoroCountText(String pomodoroCount) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("count", pomodoroCount);
        Message msg = new Message();
        msg.setData(msgBundle);
        scoreHandler.sendMessage(msg);
    }

    public void setPomodoroCountTextView(String pomodoroCountTextView) {
        pomodoroCountField.setText(pomodoroCountTextView);
    }


    public static class TimerTextHandler extends Handler {

        private final WeakReference<MainActivity> weakReference;


        public TimerTextHandler(MainActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = weakReference.get();
            if (activity != null) {
                activity.setTimerTextView((msg.getData().getString("time")));
            }
        }
    }

    public static class PomodoroCountTextHandler extends Handler {
        private final WeakReference<MainActivity> weakReference;


        public PomodoroCountTextHandler(MainActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = weakReference.get();
            if (activity != null) {
                activity.setPomodoroCountTextView(((msg.getData().getString("count"))));
            }
        }
    }
}
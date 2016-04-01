package com.donbosco.giancarlo.focusedsingularity.Presentation.Activities;

import android.os.Handler;

import com.donbosco.giancarlo.focusedsingularity.Core.Entities.PomodoroTimer;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.TimerExecutor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by GianCarlo on 3/30/2016.
 */
public class TimerExecutorImpl implements TimerExecutor {

    Timer timerExecutorDelegate;
    TimerTask timerTask;
    boolean started;
    Handler handler = new Handler();

    @Override
    public void start(final PomodoroTimer timer) {
        if (!started) {
            started = true;
            timerExecutorDelegate = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    timer.execute();
                }
            };
            timerExecutorDelegate.schedule(timerTask, 0, 1000L);
        }
    }

    @Override
    public void cancel() {
        if (started) {
            started = false;
            timerExecutorDelegate.cancel();
            timerExecutorDelegate = null;
        }
    }
}

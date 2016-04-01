package com.donbosco.giancarlo.focusedsingularity.Presentation.Presenters;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.donbosco.giancarlo.focusedsingularity.Core.Entities.Observer;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.OutputPort;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.PomodoroTimer;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.Task;
import com.donbosco.giancarlo.focusedsingularity.Core.Entities.TimerExecutor;

import java.lang.ref.WeakReference;

/**
 * Created by GianCarlo on 3/30/2016.
 */
public class TimerPresenter implements Observer, OutputPort {

    PomodoroTimer timer;
    private TimerView view;


    public TimerPresenter(TimerExecutor timerExecutor) {
        this.timer = new PomodoroTimer(timerExecutor);
        this.timer.registerOutputPort(this);
        timer.registerObserver(this);
        timer.setTask(new Task() {
            @Override
            public Long getTimeEstimateInSeconds() {
                return null;
            }

            @Override
            public void addTime(long time) {

            }

            @Override
            public long getTimeSpent() {
                return 0;
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void registerView(TimerView view) {
        this.view = view;
    }

    @Override
    public void update(String timeChanged) {
        view.setTimerText(timeChanged);
   }

    @Override
    public void setPomodoroCount(String count) {
        view.setPomodoroCountText(count);
    }
}

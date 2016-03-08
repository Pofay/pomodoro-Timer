package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class TaskTest {
    public String ticks = "";

    @Test
    public void ItShouldBeRequestedToStartBeforeBeingAbleToRun() {
        Task task = new Task("TypeStuff", 1) {
            @Override
            public void run() {
                for (int i = 0; i < 4 && isStarted(); ) {
                    ticks += ++i;
                    sleep(1);
                }
            }

            private void sleep(int seconds) {
                ticks += "s";
            }

        };

        task.requestStart();
        task.run();

        assertThat(ticks, is("1s2s3s4s"));
        assertThat(task.isStarted(), is(true));
    }


}

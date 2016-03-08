package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/8/2016.
 */
public class TaskTest {
    public String ticks = "";
    public Task task;

    @Before
    public void setUp() {
        task = new Task("TypeStuff", 1) {
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
    }

    @Test
    public void ItShouldBeRequestedToStartBeforeBeingAbleToRun() {
        task.requestStart();
        task.run();

        assertThat(ticks, is("1s2s3s4s"));
        assertThat(task.isStarted(), is(true));
    }

    @Test
    public void ItShouldNotTickWhenRequestedToStop() {
        task.run();

        assertThat(ticks, is(""));
    }


}

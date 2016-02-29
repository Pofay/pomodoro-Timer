package com.donbosco.giancarlo.focusedsingularity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 2/28/2016.
 */
public class TaskExecutorTest {

    @Test
    public void ItCanStartATask(){
        TaskExecutor executor = new TaskExecutor();
        Task task = new Task(25);
        executor.setTask(task);

        executor.start();

        assertThat(task.isStarted(), is(true));
    }
}

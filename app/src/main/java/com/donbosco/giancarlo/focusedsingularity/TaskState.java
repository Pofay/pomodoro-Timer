package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/9/2016.
 */
public interface TaskState {

    void execute(Task task);

    void stop(Task task);
}

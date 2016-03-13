package com.donbosco.giancarlo.focusedsingularity;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by GianCarlo on 3/9/2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class TaskTest {


    public class TaskTickingContext {

        String ticks = "";
        int tickCalls;
        Task task;

        @Before
        public void WithA3SecondEstimateAnd2SecondPomodoroDurationShould() {
            task = new PomodoroTask("Testing", 3000L) {
                @Override
                protected void tick() {
                    tickCalls++;
                    ticks += "T";
                }

                @Override
                protected void sleep(int timeOut) {
                    ticks += "s";
                }
            };

            task.setPomodoroDuration(2000L);
        }

        @Test
        public void Ticks2Times() {
            task.start();

            assertThat(tickCalls, is(2));
        }

        @Test
        public void SleepsInBetweenTicks() {
            task.start();

            assertThat(ticks, is("TsTs"));
        }
    }

    public class TaskStoppedContext {

        private Task task;
        int tickCalls;
        int sleepCalls;

        @Before
        public void ATaskWithADurationOf4SecondsThatIsStopped() throws Exception {
            task = new PomodoroTask("Rolling", 6000L) {
                @Override
                protected void tick() {
                    tickCalls++;
                }

                @Override
                protected void sleep(int timeOut) {
                    sleepCalls++;
                }


            };

            task.setPomodoroDuration(4000L);

            task.stop();
        }

        @Test
        public void ItShouldNotCallTickAndSleepWhenStartedAgainWithoutAReset() {
            task.start();

            assertThat(tickCalls, is(0));
            assertThat(sleepCalls, is(0));
        }

        @Test
        public void ItShouldCallTickAndSleep4TimesOnStartAfterReset() {
            task.reset();

            task.start();

            assertThat(tickCalls, is(4));
            assertThat(sleepCalls, is(4));
        }

    }
}

package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/24/2016.
 */
public class ObserverSpy implements Observer {
    public boolean updateWasCalled;
    public int numberOfUpdateCalls = 0;
    public String sequence = "";

    @Override
    public void update(String format) {
        numberOfUpdateCalls++;
        updateWasCalled = true;
        sequence += format;
    }
}

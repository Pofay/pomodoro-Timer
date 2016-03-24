package com.donbosco.giancarlo.focusedsingularity;

/**
 * Created by GianCarlo on 3/24/2016.
 */
public class ObserverSpy implements Observer {
    public boolean updateWasCalled;

    @Override
    public void update(){
        updateWasCalled = true;
    }
}

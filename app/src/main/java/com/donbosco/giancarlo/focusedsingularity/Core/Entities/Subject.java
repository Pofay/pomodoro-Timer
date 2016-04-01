package com.donbosco.giancarlo.focusedsingularity.Core.Entities;

/**
 * Created by GianCarlo on 3/25/2016.
 */
public interface Subject {
    void registerObserver(Observer observer);

    void notifyObserver(String timeChanged);
}

package com.lpoo.terrarius.states;

public abstract class TransitionState<T> extends State<T> {
    private final State savedState;

    public TransitionState(T model, State savedState) {
        super(model);
        this.savedState = savedState;
    }

    public State getSavedState() {
        return savedState;
    }
}

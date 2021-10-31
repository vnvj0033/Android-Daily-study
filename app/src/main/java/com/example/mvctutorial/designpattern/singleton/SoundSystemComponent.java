package com.example.mvctutorial.designpattern.singleton;

public class SoundSystemComponent {

    private static SoundSystemComponent instance;

    private int volume = 0;

    private SoundSystemComponent() { }

    public static SoundSystemComponent getInstance(){
        if (instance == null) {
            instance = new SoundSystemComponent();
        }

        return instance;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

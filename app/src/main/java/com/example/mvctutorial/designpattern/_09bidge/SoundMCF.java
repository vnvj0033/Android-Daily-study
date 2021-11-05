package com.example.mvctutorial.designpattern._09bidge;

public class SoundMCF implements MorseCodeFunction{
    @Override
    public void dot() {
        System.out.print("bip");
    }

    @Override
    public void dash() {
        System.out.print("bip~");
    }

    @Override
    public void space() {
        System.out.print(" ");
    }
}

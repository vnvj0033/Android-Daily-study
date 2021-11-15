package com.example.mvctutorial.designpattern._15observer.part1;

public class Button {

    private OnClickListener onClickListener;

    public void onClick() {
        if (onClickListener != null){
            onClickListener.onClick(this);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        public void onClick(Button button);
    }
}

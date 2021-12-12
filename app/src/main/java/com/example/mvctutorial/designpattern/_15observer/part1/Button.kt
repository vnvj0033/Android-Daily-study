package com.example.mvctutorial.designpattern._15observer.part1

class Button {
    var onClickListener: OnClickListener? = null

    fun onClick() = onClickListener?.onClick(this)

    interface OnClickListener {
        fun onClick(button: Button)
    }
}
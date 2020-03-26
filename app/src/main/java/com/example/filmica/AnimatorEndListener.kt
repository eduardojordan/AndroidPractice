package com.example.filmica

import android.animation.Animator
import android.view.animation.Animation

class AnimatorEndListener(
    val callback: (Animator) -> Unit
) : Animator.AnimatorListener{
    override fun onAnimationRepeat(animation: Animator?) {

    }

    override fun onAnimationEnd(animator: Animator) {
     callback.invoke(animator)
    }

    override fun onAnimationCancel(animation: Animator?) {

    }

    override fun onAnimationStart(animation: Animator?) {

    }

}
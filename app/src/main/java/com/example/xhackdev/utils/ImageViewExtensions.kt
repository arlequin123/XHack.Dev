package com.example.xhackdev.utils

import androidx.core.content.ContextCompat
import ru.nikartm.support.ImageBadgeView

fun ImageBadgeView.setBadgeBackgroundResource(backgroundResource: Int) {
    this.setBadgeBackground(ContextCompat.getDrawable(this.context, backgroundResource))
}


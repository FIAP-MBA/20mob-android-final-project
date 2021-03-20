package com.github.cesar1287.filmes20mob.customView

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.res.ResourcesCompat
import com.github.cesar1287.filmes20mob.R

class ProgressBarIndeterminate : ProgressBar {
    constructor(context: Context?) : super(context) {
        customView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        customView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        customView(context)
    }

    private fun customView(_context: Context?) {
        _context?.let { context ->
            ResourcesCompat.getColor(context.resources, R.color.colorAccent, null)
                .let { primaryProgressTint ->
                    try {
                        this.indeterminateTintList = ColorStateList.valueOf(primaryProgressTint)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        }
    }
}
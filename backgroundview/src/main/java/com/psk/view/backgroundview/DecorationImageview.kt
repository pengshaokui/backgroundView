package com.psk.view.backgroundview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.StateSet
import android.widget.ImageView
import android.widget.TextView
import java.util.jar.Attributes

class DecorationImageview : ImageView {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context) {
        initAttrs(context, attrs)
    }

    fun initAttrs(context: Context, attrs: AttributeSet) {
        var attrsArry =
            context.obtainStyledAttributes(attrs, R.styleable.decorationImageViewAttrStyle)
        var image_normal = attrsArry.getDrawable(
            R.styleable.decorationImageViewAttrStyle_decoration_image_normal
        )
        var image_pressed = attrsArry.getDrawable(
            R.styleable.decorationImageViewAttrStyle_decoration_image_pressed
        )

        attrsArry.recycle()
        if (image_normal != null && image_pressed != null)
            setBackgroundSelected(image_normal, image_pressed)
        else {
            if (image_normal != null) {
                setImageDrawable(image_normal)
            }
        }

    }


    private fun setBackgroundSelected(
        normalDrawable: Drawable,
        selectedDrawable: Drawable
    ) {
        var stateListDrawable = StateListDrawable()
        var stateSet =
            Array(4) {
                var stat = android.R.attr.state_checked;
                when (it) {
                    0 -> stat = android.R.attr.state_pressed
                    1 -> stat = android.R.attr.state_checked
                    2 -> stat = android.R.attr.state_selected
                    3 -> stat = -android.R.attr.state_enabled
                }
                IntArray(1) { stat }
            }

        stateListDrawable.addState(
            stateSet[0],
            selectedDrawable
        )
        stateListDrawable.addState(
            stateSet[1],
            selectedDrawable
        )
        stateListDrawable.addState(
            stateSet[2],
            selectedDrawable
        )
        stateListDrawable.addState(
            stateSet[3],
            selectedDrawable
        )
        stateListDrawable.addState(
            StateSet.WILD_CARD,
            normalDrawable
        )
        setImageDrawable(stateListDrawable)
    }
}
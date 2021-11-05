package com.psk.view.backgroundview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.StateSet
import android.widget.EditText
import android.widget.TextView
import java.util.jar.Attributes

class DecorationEditText : EditText {
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context) {
        initAttrs(context, attrs)
    }

    fun initAttrs(context: Context, attrs: AttributeSet) {
        var attrsArry = context.obtainStyledAttributes(attrs, R.styleable.decorationAttrStyle)
        var stroke_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_stroke_color,
            Color.TRANSPARENT
        )

        var solid_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_solid_color,
            Color.TRANSPARENT
        )
        var stroke_select_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_stroke_select_color,
            Color.TRANSPARENT
        )
        var solid_select_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_solid_select_color,
            Color.TRANSPARENT
        )
        var stroke_width = attrsArry.getDimension(
            R.styleable.decorationAttrStyle_decoration_stroke_width,
            0f
        )
        var conner_radius = attrsArry.getDimension(
            R.styleable.decorationAttrStyle_decoration_conner_radius,
            0f
        )

        var gradient_start_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_gradient_start_color,
            Color.TRANSPARENT
        )
        var gradient_end_color = attrsArry.getColor(
            R.styleable.decorationAttrStyle_decoration_gradient_end_color,
            Color.TRANSPARENT
        )
        var gradient_angle = attrsArry.getInt(
            R.styleable.decorationAttrStyle_decoration_gradient_angle,
            0
        )
        attrsArry.recycle()
        if (stroke_color == Color.TRANSPARENT && solid_color == Color.TRANSPARENT &&
            stroke_select_color == Color.TRANSPARENT && solid_select_color == Color.TRANSPARENT &&
            gradient_start_color == Color.TRANSPARENT && gradient_end_color == Color.TRANSPARENT
        ) {
            return
        } else if (gradient_start_color != Color.TRANSPARENT || gradient_end_color != Color.TRANSPARENT) {
            if (solid_select_color != Color.TRANSPARENT ||
                stroke_select_color != Color.TRANSPARENT
            ) {
                setBackgroundSelected(
                    getGradientDrawable2(
                        conner_radius,
                        stroke_width,
                        stroke_color,
                        gradient_start_color,
                        gradient_end_color,
                        gradient_angle
                    ),
                    getGradientDrawable(
                        conner_radius,
                        stroke_width,
                        stroke_select_color,
                        solid_select_color
                    )
                )
            } else {
                background = getGradientDrawable2(
                    conner_radius,
                    stroke_width,
                    stroke_color,
                    gradient_start_color,
                    gradient_end_color,
                    gradient_angle
                )
            }
        } else if (stroke_select_color == Color.TRANSPARENT && solid_select_color == Color.TRANSPARENT) {//没有选中状态
            background = getGradientDrawable(conner_radius, stroke_width, stroke_color, solid_color)
        } else {//有选中状态
            setBackgroundSelected(
                getGradientDrawable(conner_radius, stroke_width, stroke_color, solid_color),
                getGradientDrawable(
                    conner_radius,
                    stroke_width,
                    stroke_select_color,
                    solid_select_color
                )
            )
        }


    }

    private fun getGradientDrawable(
        conner_radius: Float,
        stroke_width: Float,
        stroke_color: Int,
        solid_color: Int
    ): GradientDrawable {
        var gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadius = conner_radius
        gradientDrawable.setStroke(stroke_width.toInt(), stroke_color)
        gradientDrawable.setColor(solid_color)
        return gradientDrawable
    }

    private fun getGradientDrawable2(
        conner_radius: Float,
        stroke_width: Float,
        stroke_color: Int,
        gradient_start_color: Int,
        gradient_end_color: Int,
        gradient_angle: Int
    ): GradientDrawable {
        var gradientDrawable = GradientDrawable(
            getOrientation(gradient_angle),
            intArrayOf(gradient_start_color, gradient_end_color)
        )
        gradientDrawable.cornerRadius = conner_radius
        gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT
        gradientDrawable.setStroke(stroke_width.toInt(), stroke_color)
        return gradientDrawable
    }

    private fun getOrientation(mAngle: Int): GradientDrawable.Orientation {
        var mOrientation = GradientDrawable.Orientation.TOP_BOTTOM
        if (mAngle >= 0) {
            when (mAngle) {
                0 -> mOrientation = GradientDrawable.Orientation.LEFT_RIGHT
                45 -> mOrientation = GradientDrawable.Orientation.BL_TR
                90 -> mOrientation = GradientDrawable.Orientation.BOTTOM_TOP
                135 -> mOrientation = GradientDrawable.Orientation.BR_TL
                180 -> mOrientation = GradientDrawable.Orientation.RIGHT_LEFT
                225 -> mOrientation = GradientDrawable.Orientation.TR_BL
                270 -> mOrientation = GradientDrawable.Orientation.TOP_BOTTOM
                315 -> mOrientation = GradientDrawable.Orientation.TL_BR
            }
        } else {
            mOrientation = GradientDrawable.Orientation.TOP_BOTTOM
        }
        return mOrientation
    }

    private fun setBackgroundSelected(
        normalDrawable: GradientDrawable,
        selectedDrawable: GradientDrawable
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
        background = stateListDrawable
    }
}
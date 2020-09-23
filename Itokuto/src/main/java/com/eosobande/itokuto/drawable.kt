package com.eosobande.itokuto

import top.defaults.drawabletoolbox.*

fun PathShapeDrawable(): PathShapeDrawableBuilder = PathShapeDrawableBuilder()
fun LayerDrawable(): LayerDrawableBuilder = LayerDrawableBuilder()
fun StateListDrawable(): StateListDrawableBuilder = StateListDrawableBuilder()
fun ScaleDrawable(): ScaleDrawableBuilder = ScaleDrawableBuilder()
fun RotateDrawable(): RotateDrawableBuilder = RotateDrawableBuilder()
fun RippleDrawable(): RippleDrawableBuilder = RippleDrawableBuilder()
fun FlipDrawable(): FlipDrawableBuilder = FlipDrawableBuilder()
fun Ring(): DrawableBuilder = DrawableBuilder().ring()
fun Oval(): DrawableBuilder = DrawableBuilder().oval()
fun Line(): DrawableBuilder = DrawableBuilder().line()
fun Rectangle(): DrawableBuilder = DrawableBuilder().rectangle()
fun RoundedRectangle(): DrawableBuilder = Rectangle().rounded()

package com.eosobande.itokuto.modifiers

import android.animation.StateListAnimator
import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.eosobande.itokuto.State
import top.defaults.drawabletoolbox.DrawableBuilder

interface Widget : WidgetBuilder {

    val widget: Widget

    abstract class Modifier<V : View, T : Modifier<V, T>>(val view: V) : Widget {

        @Suppress("UNCHECKED_CAST")
        protected val cast: T by lazy { this as T }

        final override val widget: Widget
            get() = this

        inline fun view(block: V.() -> Unit) = this { view.block() }

        inline fun <S> bind(state: State<S>, crossinline block: T.(S) -> Unit) = this {
            state { block(it) }
        }

        inline fun <S> observe(
            liveData: LiveData<S>,
            lifecycleOwner: LifecycleOwner,
            crossinline block: T.(S) -> Unit
        ) = this {
            liveData.observe(lifecycleOwner, Observer { block(it) })
        }

        @Suppress("UNCHECKED_CAST")
        inline operator fun invoke(block: T.() -> Unit): T {
            (this as T).block()
            return this
        }

        inline fun onClick(crossinline block: () -> Unit) = this {
            view.setOnClickListener { block() }
        }

        inline fun onLongClick(crossinline block: () -> Boolean) = this {
            view.setOnLongClickListener { block() }
        }

        fun padding(size: Int) = view { setPadding(size, size, size, size) }

        fun padding(
            start: Int? = null,
            top: Int? = null,
            end: Int? = null,
            bottom: Int? = null
        ) = this {
            view {
                setPadding(
                    start ?: paddingLeft,
                    top ?: paddingTop,
                    end ?: paddingRight,
                    bottom ?: paddingBottom
                )
            }
        }

        fun padding(horizontal: Int? = null, vertical: Int? = null) = padding(
            horizontal ?: view.paddingLeft,
            vertical ?: view.paddingTop,
            horizontal ?: view.paddingRight,
            vertical ?: view.paddingBottom
        )

        fun params(param: ViewGroup.MarginLayoutParams) = this { view.layoutParams = param }

        @Suppress("UNCHECKED_CAST")
        fun <T : ViewGroup.MarginLayoutParams> params() = view.layoutParams as T

        fun animate(@AnimRes anim: Int, listener: Animation.AnimationListener? = null) = this {
            view.startAnimation(
                AnimationUtils.loadAnimation(view.context, anim)
                    .apply {
                        listener?.let {
                            setAnimationListener(it)
                        }
                    }
            )
        }

        val animate: ViewPropertyAnimator
            get() = view.animate()

        val isVisible: Boolean
            get() = view.visibility == View.VISIBLE

        fun visible(@AnimRes anim: Int? = null): T = this {
            if (!isVisible) {
                view.visibility = View.VISIBLE
                anim?.let { animate(it) }
            }
        }

        val isInvisible: Boolean
            get() = view.visibility == View.INVISIBLE

        fun invisible(@AnimRes anim: Int? = null): T = this {
            if (!isInvisible) {
                view.visibility = View.INVISIBLE
                anim?.let { animate(it) }
            }
        }

        val isGone: Boolean
            get() = view.visibility == View.GONE

        fun gone(@AnimRes anim: Int? = null): T = this {
            if (!isGone) {
                view.visibility = View.GONE
                anim?.let { animate(it) }
            }
        }

        fun enable(enable: Boolean = true) = this { view.isEnabled = enable }

        val layoutParams: ViewGroup.MarginLayoutParams
            get() = view.layoutParams as ViewGroup.MarginLayoutParams

        fun accessibilityHeading(isHeading: Boolean) = this {
            ViewCompat.setAccessibilityHeading(view, isHeading)
        }

        fun accessibilityLiveRegion(mode: Int) = this {
            ViewCompat.setAccessibilityLiveRegion(view, mode)
        }

        fun accessibilityPaneTitle(title: CharSequence) = this {
            ViewCompat.setAccessibilityPaneTitle(view, title)
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
        fun accessibilityTraversalAfter(@IdRes id: Int) = this {
            view.accessibilityTraversalAfter = id
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
        fun accessibilityTraversalBefore(@IdRes id: Int) = this {
            view.accessibilityTraversalBefore = id
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun autoFillHints(hints: Array<String>?) = this {
            hints?.let {
                view.setAutofillHints(*it)
            } ?: view.setAutofillHints(null)
        }

        fun alpha(alpha: Float) = this { view.alpha = alpha }

        fun background(drawable: Drawable?) = this { view.background = drawable }
        fun background(drawableBuilder: DrawableBuilder?) = this {
            view.background = drawableBuilder?.build()
        }

        fun background(@ColorInt color: Int) = this { view.setBackgroundColor(color) }
        fun backgroundTint(colorStateList: ColorStateList?) = this {
            ViewCompat.setBackgroundTintList(view, colorStateList)
        }

        fun backgroundTintMode(mode: PorterDuff.Mode?) = this {
            ViewCompat.setBackgroundTintMode(view, mode)
        }

        fun clickable(clickable: Boolean) = this { view.isClickable = clickable }

        fun contentDescription(description: CharSequence) =
            this { view.contentDescription = description }

        @RequiresApi(Build.VERSION_CODES.M)
        fun contextClickable(clickable: Boolean) = this { view.isContextClickable = clickable }

        @RequiresApi(Build.VERSION_CODES.O)
        fun defaultFocusHighlightEnabled(enabled: Boolean) =
            this { view.defaultFocusHighlightEnabled = enabled }

        fun duplicateParentState(enabled: Boolean) =
            this { view.isDuplicateParentStateEnabled = enabled }

        fun elevation(size: Float) = this { ViewCompat.setElevation(view, size) }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun outlineProvider(provider: ViewOutlineProvider?) = this {
            view.outlineProvider = provider
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun clipToOutline(clip: Boolean = true) = this { view.clipToOutline = clip }

        fun fadeScrollbars(fade: Boolean) = this { view.isScrollbarFadingEnabled = fade }

        fun fadingEdgeLength(length: Int) = this { view.setFadingEdgeLength(length) }

        fun filterTouchesWhenObscured(filter: Boolean) =
            this { view.filterTouchesWhenObscured = filter }

        fun fitsSystemWindows(fit: Boolean) = this { view.fitsSystemWindows = fit }

        val focused get() = view.hasFocus()
        fun focus(focus: Boolean = true) =
            this { if (focus) view.requestFocus() else view.clearFocus() }

        inline fun focusChange(crossinline block: (Boolean) -> Unit) = this {
            view.setOnFocusChangeListener { _, hasFocus ->
                block(hasFocus)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun focusable(focusable: Int) = this { view.focusable = focusable }

        fun focusableInTouchMode(focusable: Boolean) =
            this { view.isFocusableInTouchMode = focusable }

        fun focusedByDefault(focused: Boolean) =
            this { ViewCompat.setFocusedByDefault(view, focused) }

        @RequiresApi(Build.VERSION_CODES.N)
        fun forceHasOverlappingRendering(force: Boolean) =
            view { forceHasOverlappingRendering(force) }

        @RequiresApi(Build.VERSION_CODES.M)
        fun foreground(drawable: Drawable?) = view { foreground = drawable }

        @RequiresApi(Build.VERSION_CODES.M)
        fun foregroundTint(colorStateList: ColorStateList?) =
            view { foregroundTintList = colorStateList }

        @RequiresApi(Build.VERSION_CODES.M)
        fun foregroundTintMode(mode: PorterDuff.Mode?) = view { foregroundTintMode = mode }

        @RequiresApi(Build.VERSION_CODES.M)
        fun foregroundGravity(gravity: Int) = view { foregroundGravity = gravity }

        fun hapticFeedbackEnabled(enabled: Boolean) =
            view { isHapticFeedbackEnabled = enabled }

        fun id(@IdRes id: Int) = view { this.id = id }

        fun importantForAccessibility(mode: Int) = view { importantForAccessibility = mode }

        fun importantForAutoFill(mode: Int) = this {
            ViewCompat.setImportantForAutofill(view, mode)
        }

        fun isScrollContainer(isScrollContainer: Boolean) =
            view { this.isScrollContainer = isScrollContainer }

        fun keepScreenOn(keep: Boolean) = view { keepScreenOn = keep }

        fun keyboardNavigationCluster(cluster: Boolean) =
            this { ViewCompat.setKeyboardNavigationCluster(view, cluster) }

        inline fun keyListener(
            crossinline listener: (widget: T, keyCode: Int, event: KeyEvent) -> Boolean
        ) = this {
            view.setOnKeyListener { _, keyCode, event ->
                listener(this, keyCode, event)
            }
        }

        inline fun keyboardVisibilityListener(crossinline block: T.(Boolean) -> Unit) = this {
            view.viewTreeObserver.addOnGlobalLayoutListener {
                block(isKeyboardVisible)
            }
        }

        val isKeyboardVisible: Boolean
            get() = view.rootView.let {
                val windowFrame = Rect()
                it.getWindowVisibleDisplayFrame(windowFrame)
                (it.bottom - windowFrame.bottom) > 128 * it.resources.displayMetrics.density
            }

        fun layerType(type: Int, paint: Paint? = null) = this {
            view.setLayerType(type, paint)
        }

        fun layoutDirection(direction: Int) = this {
            ViewCompat.setLayoutDirection(view, direction)
        }

        fun longClickable(long: Boolean) = this { view.isLongClickable = long }

        val height
            get() = view.height
        val width
            get() = view.width

        fun minHeight(height: Int) = this { view.minimumHeight = height }
        fun minWidth(height: Int) = this { view.minimumWidth = height }

        @RequiresApi(Build.VERSION_CODES.O)
        fun nextClusterForward(@IdRes id: Int) = this { view.nextClusterForwardId = id }
        fun nextFocusDown(@IdRes id: Int) = this { view.nextFocusDownId = id }
        fun nextFocusForward(@IdRes id: Int) = this { view.nextFocusForwardId = id }
        fun nextFocusRight(@IdRes id: Int) = this { view.nextFocusRightId = id }
        fun nextFocusLeft(@IdRes id: Int) = this { view.nextFocusLeftId = id }
        fun nextFocusUp(@IdRes id: Int) = this { view.nextFocusUpId = id }

        @RequiresApi(Build.VERSION_CODES.P)
        fun outlineAmbientShadowColor(@ColorInt color: Int) =
            this { view.outlineSpotShadowColor = color }

        @RequiresApi(Build.VERSION_CODES.P)
        fun outlineSpotShadowColor(@ColorInt color: Int) =
            this { view.outlineSpotShadowColor = color }

        fun rotation(rotation: Float) = this { view.rotation = rotation }
        fun rotationX(rotation: Float) = this { view.rotationX = rotation }
        fun rotationY(rotation: Float) = this { view.rotationY = rotation }

        fun saveEnabled(enabled: Boolean) = this { view.isSaveEnabled = enabled }

        fun selected(selected: Boolean = true) = this { view.isSelected = selected }

        fun scaleX(scale: Float) = this { view.scaleX = scale }
        fun scaleY(scale: Float) = this { view.scaleY = scale }

        fun screenReaderFocusable(focusable: Boolean) =
            this { ViewCompat.setScreenReaderFocusable(view, focusable) }

        fun scrollIndicators(indicators: Int) =
            this { ViewCompat.setScrollIndicators(view, indicators) }

        fun scrollX(scroll: Int) = this { view.scrollX = scroll }
        fun scrollY(scroll: Int) = this { view.scrollY = scroll }

        fun scrollbarSize(size: Int) = this { view.scrollBarSize = size }
        fun scrollbarStyle(style: Int) = this { view.scrollBarStyle = style }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun scrollbarThumbHorizontal(drawable: Drawable?) = this {
            view.horizontalScrollbarThumbDrawable = drawable
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun scrollbarThumbVertical(drawable: Drawable?) = this {
            view.verticalScrollbarThumbDrawable = drawable
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun scrollbarTrackHorizontal(drawable: Drawable?) = this {
            view.horizontalScrollbarTrackDrawable = drawable
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun scrollbarTrackVertical(drawable: Drawable?) = this {
            view.verticalScrollbarTrackDrawable = drawable
        }

        fun scrollbarDefaultDelayBeforeFade(delayBeforeFade: Int) = this {
            view.scrollBarDefaultDelayBeforeFade = delayBeforeFade
        }

        fun scrollbarFadeDuration(fadeDuration: Int) = this {
            view.scrollBarFadeDuration = fadeDuration
        }

        fun soundEffectsEnabled(enabled: Boolean) = this { view.isSoundEffectsEnabled = enabled }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun stateListAnimator(stateListAnimator: StateListAnimator?) = this {
            view.stateListAnimator = stateListAnimator
        }

        fun tag(tag: Any?) = this { view.tag = tag }
        fun tag(key: Int, tag: Any?) = this { view.setTag(key, tag) }

        @Suppress("UNCHECKED_CAST")
        fun <T> tag(key: Int? = null): T = (key?.let { view.getTag(key) } ?: view.tag) as T

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun textAlignment(textAlignment: Int) = this { view.textAlignment = textAlignment }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun textDirection(textDirection: Int) = this { view.textDirection = textDirection }

        @RequiresApi(Build.VERSION_CODES.O)
        fun tooltipText(tooltipText: CharSequence?) = this { view.tooltipText = tooltipText }

        fun transformPivotX(pivotX: Float) = this { view.pivotX = pivotX }
        fun transformPivotY(pivotY: Float) = this { view.pivotY = pivotY }

        fun translationName(name: String?) = this {
            ViewCompat.setTransitionName(view, name)
        }

        fun translationX(translationX: Float) = this { view.translationX = translationX }
        fun translationY(translationY: Float) = this { view.translationY = translationY }
        fun translationZ(translationZ: Float) = this {
            ViewCompat.setTranslationZ(view, translationZ)
        }

    }

    companion object {
        const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
        const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
    }

}
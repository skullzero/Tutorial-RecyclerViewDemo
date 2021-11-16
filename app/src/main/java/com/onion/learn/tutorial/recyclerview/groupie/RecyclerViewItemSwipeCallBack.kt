package com.onion.learn.tutorial.recyclerview.groupie

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.onion.learn.tutorial.recyclerview.R
import kotlin.math.abs

class RecyclerViewItemSwipeCallBack: ItemTouchHelper.Callback() {
    private var swipeBack = false
    private var currentViewHolder: RecyclerView.ViewHolder? =  null
    private val iconSize = 70
    private val iconMargin = 50
    var isSwipeLeftDone = MutableLiveData(false)

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //move item from left to right
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if(swipeBack) {
            swipeBack = false
            return 0
        }

        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            setTouchListener(recyclerView, viewHolder, dX)
        }

        currentViewHolder = viewHolder

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP

            if(swipeBack) {
                //when swipe to left more than 50% of width, then trigger an action
                if(abs(dX) > viewHolder.itemView.width * 0.5) {
                    isSwipeLeftDone.postValue(true)
                }
            }

            false
        }
    }

    private fun drawButton(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, context: Context) {
        val corners = 30f
        val itemView = viewHolder.itemView
        val paint = Paint()

        //draw background
        val button = RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
        paint.color = Color.GRAY
        canvas.drawRoundRect(button, corners, corners, paint)

        //draw trash icon
        val trashIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_forever_24)
        trashIcon?.apply {
            val top  = itemView.top + (itemView.height - iconSize) / 2
            val bottom = top + iconSize
            setTint(Color.WHITE)
            setBounds(itemView.right - iconSize - iconMargin, top, itemView.right -iconMargin, bottom)
            draw(canvas)
        }
    }

    fun onDraw(canvas: Canvas, context: Context) {
        currentViewHolder?.let {
            drawButton(canvas, it, context)
        }
    }

}
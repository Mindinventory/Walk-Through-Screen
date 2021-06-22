package com.mi.walkthroughandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.mi.walkthroughandroid.data.IndicatorSelectorModel
import com.mi.walkthroughandroid.databinding.ItemIndicatorBinding


class PagerIndicatorAdapter(
    private val animation: Animation,
    private val dataList: ArrayList<IndicatorSelectorModel>,
    private val listener: () -> Int
) : RecyclerView.Adapter<PagerIndicatorAdapter.PagerIndicatorViewHolder>() {

    private val TAG = "PagerIndicatorAdapter"

    private var drawableHeight = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerIndicatorViewHolder {
        return PagerIndicatorViewHolder(
            ItemIndicatorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerIndicatorViewHolder, position: Int) {
        holder.mBinding.apply {
            with(dataList[position]) {
                ivIndicator.background = drawable
                if (listener.invoke() == position) {
                    ivIndicator.isSelected = true
                    ivIndicator.startAnimation(animation)
                    rlItem.layoutParams.apply {
                        width = drawable.intrinsicWidth
                        height = drawable.intrinsicHeight
                        drawableHeight = height
                    }
                } else {
                    ivIndicator.isSelected = false
                    ivIndicator.animation = null
                    rlItem.layoutParams.apply {
                        width = drawable.intrinsicWidth
                        height = drawableHeight
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class PagerIndicatorViewHolder(indicatorBinding: ItemIndicatorBinding) :
        RecyclerView.ViewHolder(indicatorBinding.root) {
        val mBinding = indicatorBinding
    }
}



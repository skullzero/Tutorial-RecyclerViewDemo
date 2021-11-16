package com.onion.learn.tutorial.recyclerview.groupie

import android.annotation.SuppressLint
import android.view.View
import com.onion.learn.tutorial.recyclerview.Account
import com.onion.learn.tutorial.recyclerview.R
import com.onion.learn.tutorial.recyclerview.databinding.RecyclerItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class GroupieRecyclerItem(private val account: Account,
                          private val listener: ItemClickListener): BindableItem<RecyclerItemBinding>() {
    @SuppressLint("SetTextI18n")
    override fun bind(viewBinding: RecyclerItemBinding, position: Int) {
        viewBinding.accountItem.apply {
            text = account.id + "-" + account.name

            setOnTouchListener { _, _ ->
                listener.setSwipedItemId(account.id)
                performClick()
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.recycler_item
    }

    override fun initializeViewBinding(view: View): RecyclerItemBinding {
        return RecyclerItemBinding.bind(view)
    }

}
package com.onion.learn.tutorial.recyclerview.groupie

import android.view.View
import com.onion.learn.tutorial.recyclerview.R
import com.onion.learn.tutorial.recyclerview.databinding.GroupieListHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class GroupieRecyclerHeader(val title: String): BindableItem<GroupieListHeaderBinding>() {
    override fun bind(viewBinding: GroupieListHeaderBinding, position: Int) {
        viewBinding.headerTitle.text = title
    }

    override fun getLayout(): Int {
        return R.layout.groupie_list_header
    }

    override fun initializeViewBinding(view: View): GroupieListHeaderBinding {
        return GroupieListHeaderBinding.bind(view)
    }
}
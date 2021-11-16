package com.onion.learn.tutorial.recyclerview

import com.onion.learn.tutorial.recyclerview.groupie.RecyclerViewItemSwipeCallBack
import org.koin.dsl.module

val recyclerviewModule = module {
    factory { RecyclerViewItemSwipeCallBack() }
}
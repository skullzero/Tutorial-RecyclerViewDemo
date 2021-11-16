package com.onion.learn.tutorial.recyclerview

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onion.learn.tutorial.recyclerview.databinding.ActivityMainBinding
import com.onion.learn.tutorial.recyclerview.groupie.GroupieRecyclerHeader
import com.onion.learn.tutorial.recyclerview.groupie.GroupieRecyclerItem
import com.onion.learn.tutorial.recyclerview.groupie.RecyclerViewItemSwipeCallBack
import com.onion.learn.tutorial.recyclerview.normal.RecyclerViewAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.viewbinding.GroupieViewHolder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var groupAdapter: GroupAdapter<GroupieViewHolder<ActivityMainBinding>> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val accountList = listOf(Account("1","Tom"), Account("2", "Jerry"))

        //normal RecyclerView
        binding.normalRecyclerView.apply {
            adapter = RecyclerViewAdapter(accountList)
            layoutManager = LinearLayoutManager(context)
        }

        //Groupie Recycler View
        binding.xwrayRecyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val section = Section()
        section.setHeader(GroupieRecyclerHeader("Section1"))
        section.add(GroupieRecyclerItem(Account("1","Tom")))
        section.add(GroupieRecyclerItem(Account("2", "Jerry")))
        groupAdapter.add(section)

        val section2 = Section()
        section2.setHeader(GroupieRecyclerHeader("Section2"))
        section2.add(GroupieRecyclerItem(Account("3","Tom2")))
        section2.add(GroupieRecyclerItem(Account("4", "Jerry2")))
        groupAdapter.add(section2)

        //item touch action
        val swipeCallBack = RecyclerViewItemSwipeCallBack()
        val itemHelper = ItemTouchHelper(swipeCallBack)
        itemHelper.attachToRecyclerView(binding.xwrayRecyclerView)
        binding.xwrayRecyclerView.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeCallBack.onDraw(c, this@MainActivity)
            }
        })

        swipeCallBack.isSwipeLeftDone.observe(this) {
            if(it) {
                Toast.makeText(this, "Swipe Left Done!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
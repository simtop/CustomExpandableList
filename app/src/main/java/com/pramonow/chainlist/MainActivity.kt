package com.pramonow.chainlist

import java.util.ArrayList
import java.util.HashMap
import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView

class MainActivity : Activity() {

    internal lateinit var listAdapter: ExpandableListAdapter
    internal lateinit var expListView: ExpandableListView
    internal lateinit var listDataHeader: MutableList<String>
    internal lateinit var listDataChild: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the listview
        expListView = findViewById<View>(R.id.list_view) as ExpandableListView

        // preparing list data
        prepareListData()

        listAdapter = ExpandableListAdapter(this, listDataHeader, listDataChild)

        // setting list adapter
        expListView.setAdapter(listAdapter)

    }

    /*
     * Preparing the list data
     */
    private fun prepareListData() {
        listDataHeader = ArrayList()
        listDataChild = HashMap()

        // Adding child data
        listDataHeader.add("Top 250")
        listDataHeader.add("Now Showing")
        listDataHeader.add("Coming Soon..")

        // Adding child data
        val top250 = ArrayList<String>()
        top250.add("The Shawshank Redemption")
        top250.add("The Godfather")
        top250.add("The Godfather: Part II")
        top250.add("Pulp Fiction")
        top250.add("The Good, the Bad and the Ugly")
        top250.add("The Dark Knight")
        top250.add("12 Angry Men")

        val nowShowing = ArrayList<String>()
        nowShowing.add("The Conjuring")
        nowShowing.add("Despicable Me 2")
        nowShowing.add("Turbo")
        nowShowing.add("Grown Ups 2")
        nowShowing.add("Red 2")
        nowShowing.add("The Wolverine")

        val comingSoon = ArrayList<String>()
        comingSoon.add("2 Guns")
//        comingSoon.add("The Smurfs 2")
//        comingSoon.add("The Spectacular Now")
//        comingSoon.add("The Canyons")
//        comingSoon.add("Europa Report")

        listDataChild[listDataHeader[0]] = top250 // Header, Child data
        listDataChild[listDataHeader[1]] = nowShowing
        listDataChild[listDataHeader[2]] = comingSoon
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expListView.setIndicatorBounds(80, 90)
        } else {
            expListView.setIndicatorBoundsRelative(
                expListView.width - 100,
                expListView.width- 8
            )


            val im = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getDrawable(R.drawable.indicator)
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }

            expListView.setGroupIndicator(
                im
            )
        }
    }
}

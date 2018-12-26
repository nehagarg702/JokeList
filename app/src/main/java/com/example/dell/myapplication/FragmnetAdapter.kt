/*
Fragment adapter to show fragments on home screen
 */
package com.example.dell.myapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmnetAdapter internal constructor(fm: FragmentManager, list: ArrayList<String>) : FragmentPagerAdapter(fm) {

    private val COUNT = 2
    private var list11=list

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Fragment1();
            1 -> fragment = Fragment2();
        }
        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  list11[position]
    }
}

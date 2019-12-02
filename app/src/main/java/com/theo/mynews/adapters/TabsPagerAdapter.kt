package com.theo.mynews.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.theo.mynews.fragments.ArticleSearchFragment
import com.theo.mynews.fragments.ScienceFragment
import com.theo.mynews.fragments.MostPopularFragment
import com.theo.mynews.fragments.TopStoriesFragment

class TabsPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return TopStoriesFragment()
            }
            1 -> {
                return MostPopularFragment()
            }
            2 -> {

                return ScienceFragment()
            }
            else -> return ArticleSearchFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
package com.themoviedb.kotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.themoviedb.kotlin.views.CategoryFragment

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                CategoryFragment("28")
            }
            1 ->{
                CategoryFragment("18")
            }
            2 ->{
                CategoryFragment("14")
            }
            else ->{
                CategoryFragment("878")
            }
        }
    }

    override fun getCount(): Int {
       return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->{
                "Ação"
            }
            1 -> {
                "Drama"
            }
            2 ->{
                "Fantasia"
            }
            else ->{
                "Ficção"
            }
        }
    }
}
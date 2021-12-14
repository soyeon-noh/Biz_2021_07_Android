package com.noso.pager.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.noso.pager.ui.dashboard.DashboardFragment
import com.noso.pager.ui.notifications.NotificationsFragment
import java.lang.IndexOutOfBoundsException

const val DASHBOARD_INDEX = 0;
const val NOTIFICATION_INDEX = 1;

class ViewPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment)  {

    // DashBoard, Notification fragment 를 Map type으로 묶어서
    // pageTabList 에 담기기
   private val pageTabList : Map<Int, ()->Fragment> =
        mapOf(
            // 앞 index값을 이용하여 뒤의 객체를 실행시킨다
            DASHBOARD_INDEX to { DashboardFragment()},
            NOTIFICATION_INDEX to {NotificationsFragment()}
        )

    override fun getItemCount(): Int {
        return pageTabList.size
    }

    // 포지션을 받으면 프래그먼트를 화면에 띄운다
    override fun createFragment(position: Int): Fragment {
        return pageTabList[position]?.invoke() ?:
            throw IndexOutOfBoundsException()
    }
}
package com.noso.login.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noso.login.R
import com.noso.login.databinding.FragmentDashboardBinding
import com.noso.login.ui.AuthFragmentParent

/**
 * 만약 대쉬보드 메뉴가 선택되면
 * 대쉬보드 Fragment 가 열릴텐데
 * 만약 login 이 되어 있지 않으면 login 화면으로 redirect 를 수행할 것이다.
 */
class DashboardFragment : AuthFragmentParent() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // text에 변화가 일어나면 현재 라이프사이클이 변화되는 상황에서
        val textView: TextView = binding.textDashboard
        // dashboardViewModel 에 담겨있는 text 변수의 값이 변경되면
        // (.observe) : 감시를 하고 있는 실행 코드
        dashboardViewModel.text.observe(viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
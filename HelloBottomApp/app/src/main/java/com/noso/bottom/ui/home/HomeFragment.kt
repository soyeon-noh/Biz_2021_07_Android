package com.noso.bottom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.noso.bottom.R
import com.noso.bottom.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    // fragment.xml 을 binding 방식으로 사용하기 위한 선언
    private var _binding: FragmentHomeBinding? = null

    // _binding 을 Android, MainActivity 등에서
    // 사용할 수 있도록 하는 Setter() method
    // Setter method 는 내부에서 선언된 _binding 변수를
    // immutable(불변) 객체로 return 한다.
    private val binding get() = _binding!! // !! : null 값이 아닌 경우에만 return 하라

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // fragment.xml 을 확장하여 사용할 준비
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        // return은 반드시 immutable로 해야하므로
        // 담을떄는 _binding으로
        // return은 binding으로 해야한다.
//        return _binding.root // (x)
        return binding.root
    }

    // 사용이 끝난 _binding 변수를 메모리에서 해제하기
    // 화면에 보이지 않는 fragment 가 내부 메모리를 차지하고 있을 수 있기 때문에
    // 사용이 끝난 fragment 는 메모리에서 해제해주어야 한다.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
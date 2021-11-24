package com.noso.hello

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noso.hello.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    /**
     * Fragment Binding 하기
     * 1. null 값으로 초기화를 한다.
     */
    private var _firstFragment: FragmentFirstBinding? = null // 현재 null 값인 상태

    /**
     *  _firstFragment 와 fragment 의 모든 속성을 연결하여
     *  _firstFragment 는 현재 클래스 내에서 safe 데이터 형식으로
     *  값을 설정하고
     *  외부로 전송할때는 get() method 가 설정된 firstFragment 를 사용하는
     *  다소 독특한 방식
     *
     *  내부 클래스에서 변경되는 변수가 외부로 전달될때
     *  문제를 일으킬 수 있기 때문에
     *  외부에서는 변수값을 변경하지 못하도록
     *  Immutable(불변)객체로 변환하는 작업
     */
    private val firstFragment get() = _firstFragment!! // 그 변수를 firstFragment에 복사
    // 불변객체로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // main activity에 first activity를 붙일때.. 저쪽에서 맘대로 바꾸면 안되니까?
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _firstFragment = FragmentFirstBinding
            .inflate(
                inflater,
                container,
                false
            )

        return firstFragment.root
        // 복제된 내용이 들어있는 불변객체를 외부로 보내서 쓴다
    }

}
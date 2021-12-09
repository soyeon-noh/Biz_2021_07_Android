package com.noso.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.noso.login.R
import com.noso.login.ui.login.LoginViewModel


/**
 * Java 에서는 모든 클래스는 기본적으로 상속받을 수 있다.
 * Kotlin 에서는 모든 클래스는 기본적으로 상속이 불가능하다
 * open 키워드는 이 클래스를 누군가가 상속하여 사용할 수 있도록 허용하는 코드
 */

// fragment를 상속받고 DashboardFragment의 onViewCreated 클래스를 잘라붙여넣는다
// fragment를 상속받았기 때문에 onViewCreated 클래스를 그대로 가져올 수 있다.
open class AuthFragmentParent : Fragment() {

    /**
     * LoginViewModel(store) 데이터를 공유하여 사용하기
     */
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userId = loginViewModel.loginResult.value?.success?.userId
        // userid가 null( ?: ) 이면 없음 이라고 출력
        // null 이 아니면 userId 값을 출력 (아래 Logcat을 클릭해서 확인해볼 수 있음)
        Log.d("Author", userId?:"없음")

        if (userId == null) {
            // navigation_login 메뉴를 클릭한 것처럼
            // fragment 를 다른 fragment 로 redirection 하기
            findNavController().navigate(R.id.navigation_login)
        }
    }
}
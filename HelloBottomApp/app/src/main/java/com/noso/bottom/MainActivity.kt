package com.noso.bottom

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.noso.bottom.databinding.ActivityMainBinding
import com.noso.bottom.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.BottomNav {

    // binding 방식으로 activity에 접근하기 위한 준비
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** activity.main.xml 을 화면에 그리는 과정 */
        // activity.xml 파일을 확장하여 binding 객체로 변환하기
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 확장된 binding 을 처음 화면에 그리기
        setContentView(binding.root)


        /** BottomNav 를 설정하는 부분 */


        // binding으로부터 navView 를 가져와 별도의 변수로 선언
        // kotlin에서는 변수를 선언할 때 type이 명확하고 null 값이 절대 아닐 경우
        // 변수의 type 을 생략할 수 있다.

        // 변수 선언하기 val(var) 변수명 : type = 초기값
        // val navView: BottomNavigationView = binding.navView
        // 변수 type 생략
        val navView = binding.navView

        // fragment 를 NavControl 방식으로 제어하기 위하여 선언하기
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        /** 원래있던 코드 정리하기 */
        // ==========================
        // BottomNav 를 선택했을 때
        // 화면에 해당하는 fragment 를 띄우고
        // 띄워진 fragment 가 어떤 것인지 title bar 에 제목을 표시하기 위한 절차
        // ==========================

        // bottom_nav_menu.xml 에 선언된 메뉴 item 들을 Set type 의 데이터로 만들기
        // set type 의 데이터 : 값이 중복되지 않은 단순한 Collection type 의 데이터
        // ( 배열은 배열인데 좀 특이한 배열이다 )
        val menuSets = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications
        )

        // AppBar 와 bottom_nav_menu.xml 과 연동하기
        val appBarConfiguration = AppBarConfiguration(menuSets)

        // AppBar 와 BottomNav 를 연동하기 위한 준비
        setupActionBarWithNavController(navController, appBarConfiguration)
        // 선언된 NavController 를 navView 에 부착하기
        navView.setupWithNavController(navController)

        /** login 관련 */
        // 사용자 정보를 임시로 생성
        // login 칼럼을 false 로
        val login = mapOf(
            "username" to "callor",
            "password" to "12345",
            "nickname" to "홍길동",
            "isLogin" to true
        )

        if( login["isLogin"] == false){
            navController.navigate(R.id.action_global_navigation_login)
        }
    }

    override fun setBottomNav(status: Boolean) {

        // status 가 true 이면 Bottom Nav 를 보이고
        // false 이면 감춰라
        binding.navView.visibility = if(status) View.VISIBLE else View.GONE
    }
}
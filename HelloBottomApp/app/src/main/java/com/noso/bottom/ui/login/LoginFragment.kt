package com.noso.bottom.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.noso.bottom.MainActivity
import com.noso.bottom.databinding.FragmentLoginBinding

import com.noso.bottom.R

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * MainActivity 에 method 를 만들고
     * BottomNav 를 보이거나 감추는 코드를 실행하고 싶다.
     *
     * 표준 코드에서는 다음과 같이 MainActivity 의 method 를
     * 호출할 때
     * 객체를 생성하고 method 를 호출할 수 있다.
     *
     * val mainActivity = MainActivity() : KT code
     * MainActivity mainActivity = new MainActivity() : Java code
     *
     * mainActivity.setBottomNav(true)
     * mainActivity.setBottomNav(false)
     *
     * 그런데
     * Android 에서 Activity 는 절대 직접 핸들링 할 수 없다.
     *
     *      안드로이드 os에 의해 자동으로 만들어지는거라 임의로 생성할 수 없다.
     *      안드로이드 어플내에서 안드로이드 어플이 하나 또 생겨나는거나 마찬가지
     *      이를 방지하기위해 위의 kt code, Java code라고 적어둔 코드를 아예막아둠
     *
     * Java(Kotlin) 의 CallBack 패턴을 사용한다.
     * 1. 호출할 곳에서 (중첩, 포함) interface 를 선언한다. (inner interface)
     * 2. MainActivity 에서 이 interface 를 impliment 하고
     * 3. method 코드를 구현한다.
     * 4. 여기에서 Main method 를 호출한다.
     *
     */
    public interface BottomNav {
        fun setBottomNav(status: Boolean)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        // bottomNav 감추기
        // mainActivity 의 Context 불러오기
        val mainActivity = activity as MainActivity
        mainActivity.setBottomNav(false)


        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        val mainActivity = activity as MainActivity
        mainActivity.setBottomNav(true)
    }
}
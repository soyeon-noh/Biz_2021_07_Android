package com.noso.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.noso.library.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    // fragment_first.xml 을 Binding하라는 의미가 된다.
    // *.xml 파일이 마치 java class가 된 것처럼 코딩할 수 있다.
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // setContentView가 아니라 return을 함
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    // 그냥 Create가 아니라 ViewCrated다!
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
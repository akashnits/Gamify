package com.akash.gamifyactivity.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.akash.gamifyactivity.LogoQuizActivity
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.R
import com.akash.gamifyactivity.databinding.FragmentWelcomeScreenBinding
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import javax.inject.Inject

class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener { navigateToLogoQuizFragment() }
        return binding.root
    }

    private fun navigateToLogoQuizFragment() {
        val manager: FragmentManager? = activity?.supportFragmentManager
        val fragment = LogoQuizFragment()
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)?.addToBackStack(null)
        transaction?.commit()
    }
}
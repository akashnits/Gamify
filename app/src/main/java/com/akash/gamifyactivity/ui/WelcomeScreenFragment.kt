package com.akash.gamifyactivity.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.R
import com.akash.gamifyactivity.databinding.FragmentWelcomeScreenBinding
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogoQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeScreenFragment : Fragment() {

    @Inject
    lateinit var logoQuizViewModelFactory: LogoQuizViewModelFactory

    private val logoQuizViewModel: LogoQuizViewModel by viewModels {
        logoQuizViewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logoQuizViewModel.loadQuizFromAsset(context?.assets)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener { navigateToLogoQuizFragment() }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LogoQuizApplication).appComponent.injectFragment(this)
    }

    private fun navigateToLogoQuizFragment() {
        val manager: FragmentManager? = activity?.supportFragmentManager
        val fragment = LogoQuizFragment()
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)?.addToBackStack(null)
        transaction?.commit()
    }
}
package com.akash.gamifyactivity.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.R
import com.akash.gamifyactivity.databinding.FragmentLogoQuizBinding
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
class LogoQuizFragment : Fragment() {

    @Inject
    lateinit var logoQuizViewModelFactory: LogoQuizViewModelFactory

    private val logoQuizViewModel: LogoQuizViewModel by viewModels {
        logoQuizViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLogoQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LogoQuizApplication).appComponent.injectFragment(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogoQuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
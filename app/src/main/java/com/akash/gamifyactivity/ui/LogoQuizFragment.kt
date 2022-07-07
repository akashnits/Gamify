package com.akash.gamifyactivity.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.akash.gamifyactivity.LogoQuizActivity
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.databinding.FragmentLogoQuizBinding
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import javax.inject.Inject

class LogoQuizFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLogoQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(context, "Toasting", Toast.LENGTH_SHORT).show()
        (activity as? LogoQuizActivity)?.logoQuizViewModel?.logoItems?.observe(viewLifecycleOwner) { logoItems ->
            logoItems?.forEach {
                Toast.makeText(
                    context,
                    "LogoItem name is: ${it.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LogoQuizApplication).appComponent.injectFragment(this)
    }
}
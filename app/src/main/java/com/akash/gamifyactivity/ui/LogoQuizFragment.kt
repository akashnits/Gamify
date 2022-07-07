package com.akash.gamifyactivity.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.databinding.FragmentLogoQuizBinding
import com.akash.gamifyactivity.model.LogoItem
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModel
import com.akash.gamifyactivity.viewmodel.LogoQuizViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class LogoQuizFragment : Fragment() {

    @Inject
    lateinit var logoQuizViewModelFactory: LogoQuizViewModelFactory

    val logoQuizViewModel: LogoQuizViewModel by viewModels {
        logoQuizViewModelFactory
    }

    lateinit var imageView: ImageView
    lateinit var submitBtn: Button
    lateinit var etAns: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLogoQuizBinding.inflate(inflater, container, false)

        imageView = binding.ivLogo
        submitBtn = binding.btnSubmit
        etAns = binding.etAnswer
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitBtn.setOnClickListener {
            val result = logoQuizViewModel.isValidAnswer(
                etAns.text.trim().toString()
            )
            etAns.text.clear()

            result.let {
                if (it) {
                    Toast.makeText(context, "$result", Toast.LENGTH_SHORT).show()
                    // show next question
                    showNextQuestion()
                } else {
                    // ask to try again
                    Toast.makeText(context, "Incorrect: Pls try again", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        logoQuizViewModel.currentLogoItem?.let {
            loadIntoImageView(logoQuizViewModel.currentLogoItem)
        }
        logoQuizViewModel.currentLogoItem ?: showNextQuestion()
    }

    private fun showNextQuestion() {
        logoQuizViewModel.logoItems.observe(viewLifecycleOwner) { logoItems ->
            // load random quiz
            val logoItem = logoQuizViewModel.getRandomQuestion()
            if (logoItem == null) {
                Toast.makeText(context, "You're all set!", Toast.LENGTH_SHORT).show()
            } else {
                loadIntoImageView(logoItem)
            }
        }
    }

    private fun loadIntoImageView(logoItem: LogoItem?) {
        Glide.with(imageView.context)
            .load(logoItem?.imgUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LogoQuizApplication).appComponent.injectFragment(this)
    }
}
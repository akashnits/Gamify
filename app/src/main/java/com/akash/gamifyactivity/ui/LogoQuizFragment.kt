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
import com.akash.gamifyactivity.LogoQuizActivity
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.databinding.FragmentLogoQuizBinding
import com.akash.gamifyactivity.model.LogoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class LogoQuizFragment : Fragment() {

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
            val result = (activity as? LogoQuizActivity)?.logoQuizViewModel?.isValidAnswer(
                etAns.text.trim().toString()
            )
            etAns.text.clear()

            result?.let {
                if(it) {
                    Toast.makeText(context, "$result", Toast.LENGTH_SHORT).show()
                    // show next question
                    showNextQuestion()
                }else{
                    // ask to try again
                    Toast.makeText(context, "Incorrect: Pls try again", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? LogoQuizActivity)?.logoQuizViewModel?.currentLogoItem?.let {
            loadIntoImageView((activity as? LogoQuizActivity)?.logoQuizViewModel?.currentLogoItem)
        }
        (activity as? LogoQuizActivity)?.logoQuizViewModel?.currentLogoItem ?: showNextQuestion()
    }

    private fun showNextQuestion(){
        (activity as? LogoQuizActivity)?.logoQuizViewModel?.logoItems?.observe(viewLifecycleOwner) { logoItems ->
            // load random quiz
            val logoItem = (activity as? LogoQuizActivity)?.logoQuizViewModel?.getRandomQuestion()
            loadIntoImageView(logoItem)
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
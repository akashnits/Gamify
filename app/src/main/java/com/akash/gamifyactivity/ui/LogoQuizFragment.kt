package com.akash.gamifyactivity.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.akash.gamifyactivity.LogoQuizActivity
import com.akash.gamifyactivity.LogoQuizApplication
import com.akash.gamifyactivity.databinding.FragmentLogoQuizBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class LogoQuizFragment : Fragment() {

    lateinit var imageView : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLogoQuizBinding.inflate(inflater, container, false)

        imageView = binding.ivLogo
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        (activity as? LogoQuizActivity)?.logoQuizViewModel?.logoItems?.observe(viewLifecycleOwner) { logoItems ->
            Glide.with(imageView.context)
                .load(logoItems?.first()?.imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LogoQuizApplication).appComponent.injectFragment(this)
    }
}
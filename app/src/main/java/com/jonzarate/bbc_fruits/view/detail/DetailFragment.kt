package com.jonzarate.bbc_fruits.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.jonzarate.bbc_fruits.Injector
import com.jonzarate.bbc_fruits.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var viewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = Injector.getDetailViewModelFactory()
        viewmodel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        val binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment
            fruit = args.fruit
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewmodel.onPageLoaded()
    }
}
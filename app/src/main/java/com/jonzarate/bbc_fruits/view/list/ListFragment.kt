package com.jonzarate.bbc_fruits.view.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonzarate.bbc_fruits.Injector
import com.jonzarate.bbc_fruits.data.model.Fruit
import com.jonzarate.bbc_fruits.databinding.FragmentListBinding

class ListFragment : Fragment(), FruitAdapter.OnUserClickListener {

    private val adapter = FruitAdapter(this)

    private lateinit var viewmodel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = Injector.getListViewModelFactory()
        viewmodel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)
        val binding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListFragment
            viewmodel = this@ListFragment.viewmodel
        }

        setupList(binding)
        setupObservers(viewmodel)

        return binding.root
    }

    private fun setupList(binding: FragmentListBinding) {
        with (binding.fruits) {
            val manager = LinearLayoutManager(requireContext())
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(requireContext(), manager.orientation))
            adapter = this@ListFragment.adapter
        }
    }

    private fun setupObservers(viewmodel: ListViewModel) {
        viewmodel.fruits.observe(this, Observer { list ->
            adapter.fruits = list
            adapter.notifyDataSetChanged()
        })

        viewmodel.clear.observe(this, Observer {
            adapter.fruits = null
            adapter.notifyDataSetChanged()
        })

        viewmodel.launchDetailView.observe(this, Observer { fruit->
            val action = ListFragmentDirections.actionFruitsToDetailFragment(fruit)
            findNavController().navigate(action)
        })
    }

    override fun onUserClick(fruit: Fruit) {
        viewmodel.onFruitSelected(fruit)
    }
}
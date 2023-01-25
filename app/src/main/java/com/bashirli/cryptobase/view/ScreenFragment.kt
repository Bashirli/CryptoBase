package com.bashirli.cryptobase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bashirli.cryptobase.adapter.Adapter
import com.bashirli.cryptobase.R
import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.databinding.FragmentScreenBinding
import com.bashirli.cryptobase.repo.Repo
import com.bashirli.cryptobase.viewmodel.ScreenMVVM
import javax.inject.Inject

class ScreenFragment @Inject constructor(
    var repo:Repo
) : Fragment() {

    private lateinit var viewModel:ScreenMVVM
    private lateinit var binding:FragmentScreenBinding
    private var adapter= Adapter(ArrayList<CryptoData>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_screen,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentScreenBinding.bind(view)
        viewModel=ViewModelProvider(requireActivity()).get(ScreenMVVM::class.java)
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        viewModel.loadData()
        observeData()
    }

    private fun observeData(){
        viewModel.loading.observe(viewLifecycleOwner){
            if(it){
                println("loading")
                binding.recyclerView.visibility=View.GONE
                binding.cryptoProgressBar.visibility=View.VISIBLE
            }
        }

        viewModel.errorData.observe(viewLifecycleOwner){
            if (it){
                binding.recyclerView.visibility=View.GONE
                binding.cryptoProgressBar.visibility=View.GONE
            Toast.makeText(requireContext(),viewModel.errorMessage,Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.success.observe(viewLifecycleOwner){
            if (it){
                binding.recyclerView.visibility=View.VISIBLE
                binding.cryptoProgressBar.visibility=View.GONE
                viewModel.cryptoData?.let {
                    adapter.updateList(it)
                }
            }
        }

    }


}
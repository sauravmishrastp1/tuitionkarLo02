package com.tklpvtltd.ui.remark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.data.responseModel.RemarkListing
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.FragmentRemarkBinding
import com.tklpvtltd.ui.adapter.RemarkAdapterAdapter
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemarkFragment : Fragment() {

   private lateinit var binding: FragmentRemarkBinding
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var remarkAdapterAdapter: RemarkAdapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRemarkBinding.inflate(layoutInflater,container,false)
        remarkAdapterAdapter = RemarkAdapterAdapter()

        mainViewModel.getRemarkList(SessionManager.getInstance(requireContext()).getUserId)

         observe()
        return binding.root
    }

    fun observe(){
        mainViewModel.remarkListResponse.observe(viewLifecycleOwner){
            try {
                if(it.body()!!.remarkListing.isNotEmpty()){
                    remarkAdapterAdapter.setData(it.body()!!.remarkListing as MutableList<RemarkListing>)
                    binding.remarkRv.adapter = remarkAdapterAdapter
                    binding.noDataFound.visibility=View.GONE
                }else{
                    binding.noDataFound.visibility=View.GONE


                }
            }catch (e:Exception){
                binding.noDataFound.visibility=View.VISIBLE

            }



        }
    }




}
package com.tklpvtltd.ui.slideshow
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.data.responseModel.OurPlan
import com.tklpvtltd.LoginActivity
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.databinding.FragmentSlideshowBinding
import com.tklpvtltd.ui.PaymentDetailsActivity
import com.tklpvtltd.ui.adapter.SubscriptionAdapter
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

class SubscriptionFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    private val binding get() = _binding!!
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var subscriptionAdapter: SubscriptionAdapter


    @OptIn(KoinApiExtension::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        subscriptionAdapter = SubscriptionAdapter(object :SubscriptionAdapter.CallBackBuy{
            override fun getJobData(list: MutableList<OurPlan>, position: Int) {
                if(SessionManager.getInstance(requireContext()).isLogin){
                    if(list[position].planHistory!="Can Not Purchase" || list[position].planHistory!="Active Plan"){
                        startActivity(Intent(requireContext(), PaymentDetailsActivity::class.java).apply {
                            putExtra("rs",list[position].price.toInt())
                            putExtra("planId",list[position].id)
                        })
                    }

                }else{
                    showAlert("Please Login","Error")
                }


            }

        })

        mainViewModel.getUserSuscribtionPlan(SessionManager.getInstance(requireContext()).getUserId)
        return root
    }

    @OptIn(KoinApiExtension::class)
    override fun onResume() {
        super.onResume()
        mainViewModel.getUserSuscribtionPlan(SessionManager.getInstance(requireContext()).getUserId)
        observe()

    }
    @OptIn(KoinApiExtension::class)
    fun observe(){
        mainViewModel.subscriptionResponse.observe(viewLifecycleOwner){

            subscriptionAdapter.setData(it.body()!!.ourPlan as MutableList<OurPlan>,it.body()!!.planExpiredDate.toString())
            binding.rvSuscribtion.adapter = subscriptionAdapter
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun showAlert(message: String,title:String) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which -> dialog.dismiss()
            startActivity(Intent(requireContext(),LoginActivity::class.java))
        }
        alertDialog.show()

    }
}
package com.tklpvtltd.ui

import androidx.appcompat.app.AppCompatActivity
import com.razorpay.PaymentResultListener
import android.widget.ProgressBar
import android.widget.TextView
import android.os.Bundle
import com.tklpvtltd.R
import com.razorpay.Checkout
import org.json.JSONObject
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.data.responseModel.OrderIdResponse
import com.tklpvtltd.MainActivity
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.HashMap
import kotlin.Throws

class PaymentDetailsActivity : AppCompatActivity(), PaymentResultListener {
    var toolbar: Toolbar? = null
    var paybutton: Button? = null
    var circleprogress: ProgressBar? = null
    var username: TextView? = null
    var useremail: TextView? = null
    var userphone: TextView? = null
    var amountHeading: TextView? = null
    var amounttotal: TextView? = null

    //    rzp_live_M1tpD1iSEYl6dQ
    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var Amount: String? = null
    var Discription: String? = null
    var jobspeci: String? = null
    var permntaddre: String? = null
    var corresaddr: String? = null
    var uid: String? = null
    var orderIDd: String? = null
    var ourAmount: String? = null
    var myAmount = 0
    var orderId=""
    private val mainViewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)
        Checkout.preload(applicationContext)
        amountHeading = findViewById(R.id.amount_Headind)
        amounttotal = findViewById(R.id.amounttext)
        paybutton = findViewById(R.id.pay)
        username = findViewById(R.id.Name)
        useremail = findViewById(R.id.Email)
        userphone = findViewById(R.id.Mobile)
        circleprogress = findViewById(R.id.progressbar)
        toolbar = findViewById(R.id.action_bar)
        toolbar!!.setTitle("Payment Details")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        toolbar!!.setTitleTextColor(Color.parseColor("#ffffff"))

        ////////////////////////////////////////////////////
        val bundle = intent.extras
//        name = bundle!!.getString("Name")
//        email = bundle.getString("Email")
//        phone = bundle.getString("Phone")
//        Amount = bundle.getString("Amount")
//        Discription = bundle.getString("RegistrationType")
//        jobspeci = bundle.getString("Job_Specification")
//        permntaddre = bundle.getString("Permanent_Address")
//        corresaddr = bundle.getString("Correwpondence_Address")
//        orderIDd = bundle.getString("order_id")
//        uid = bundle.getString("Userid")
//        val adhar = bundle.getString("adhar")
//        myAmount = Amount!!.toInt() * 100
//        ourAmount = myAmount.toString()
//        amounttotal!!.setText("Rs $Amount /-")
//        amountHeading!!.setText("Amount for $Discription")
//        paybutton!!.setText("Pay Rs $Amount /-")
//        username!!.setText(name)
//        useremail!!.setText(email)
//        userphone!!.setText(phone)

        ////////////////////////////////////////////////
        //paybutton!!.setOnClickListener(View.OnClickListener { startPayment() })
        mainViewModel.generateOrderId(SessionManager.getInstance(applicationContext).getUserId,intent.getIntExtra("planId",0))
        mainViewModel.orderIdResponse.observe(this){
            startPayment(it.body()!!.orderId,it.body()!!.amount)
            orderId= it.body()!!.orderId
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun startPayment(orderId:String,amount:String) {
        /**
         * Instantiate Checkout
         */
        val checkout = Checkout()
        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.tution)
      //  checkout.setKeyID("rzp_live_jrlagEEFLnY5PU")
        checkout.setKeyID("rzp_live_bpiKbzqC6CwUhw")
        //checkout.setKeyID("rzp_live_M1tpD1iSEYl6dQ")
//        checkout.setKeyID("rzp_test_GAWhN1NisD2VXk")
//        checkout.setKeyID("rzp_test_pLcCUtYagTZ2dv")
        /**
         * Reference to current activity
         */
        val activity: AppCompatActivity = this
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            val options = JSONObject()
            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "TKL Consultancy (OPC) Pvt Ltd")
            options.put("description", "test")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", (amount.toInt()*100).toString()) //pass amount in currency subunits
            options.put("email", SessionManager.getInstance(applicationContext).getUserEmail)
            options.put("phone", SessionManager.getInstance(applicationContext).getPhone)
            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)
            checkout.open(activity, options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(razorpayPaymentID: String) {
        Toast.makeText(this, "Succesfull Payment", Toast.LENGTH_SHORT).show()
        mainViewModel.storePaymentDeatils(SessionManager.getInstance(applicationContext).getUserId,razorpayPaymentID,orderId)

        mainViewModel.storePaymentDetailsResponse.observe(this){
            startActivity(Intent(applicationContext,MainActivity::class.java).apply {
                putExtra("type","Paytment")
            })
            finish()
        }

    }

    override fun onPaymentError(code: Int, response: String) {

        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext,MainActivity::class.java).apply {
            putExtra("type","Paytment")
        })

    }


}
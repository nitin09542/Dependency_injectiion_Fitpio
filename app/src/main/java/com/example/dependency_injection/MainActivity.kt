package com.example.dependency_injection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast

import com.example.dependency_injection.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /*  @Inject
      lateinit var main: Main
      @Inject
      lateinit var mainScool: GetScoolNameMain
      @Inject
      lateinit var viewModel: MyViewModel*/
    /* main.getName()
       mainScool.getName()
       viewModel.addUser(User(101, "Nitin"))
       viewModel.addUser(User(102, "dls"))
       viewModel.addUser(User(103, "ddfdf"))

       var data = viewModel.getUser(101)*/
    private lateinit var binding: ActivityMainBinding
    private var myList = ArrayList<LoginResponse>()

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            viewModel.login()
            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                updateUi(myList)
            }
        }

        viewModel.loginReApiResponsesult.observe(this) { result ->
            // Handle the different result states
            when (result) {
                is ApiResponse.Loading -> {
                    // Handle loading state
                    Log.d("MY_DATA_OBJ", ApiResponse.Loading.toString())
                }

                is ApiResponse.Success -> {
                    myList = result.data as ArrayList

                    // Handle success state
                    updateUi(myList)
                    Log.d("MY_DATA_OBJ", myList.get(0).title.toString())
                    //Toast.makeText(this, loginResponse[0].title, Toast.LENGTH_LONG).show()
                }

                is ApiResponse.Error -> {
                    val exception = result.errorMessage
                    // Handle error state
                    Log.d("MY_DATA_OBJ", exception.toString())
                    Toast.makeText(this, exception, Toast.LENGTH_LONG).show()
                }
            }
        }


    }

    private fun updateUi(myList: ArrayList<LoginResponse>) {
        binding.recView.adapter = MyReclerViewData(myList)

    }

}
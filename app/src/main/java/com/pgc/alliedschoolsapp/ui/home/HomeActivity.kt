package com.pgc.alliedschoolsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.examplecleanarch.resource.Status
import com.google.gson.Gson
import com.pgc.alliedschoolsapp.R
import com.pgc.alliedschoolsapp.databinding.ActivityHomeBinding
import com.pgc.alliedschoolsapp.ui.home.viewmodel.HomeViewmodel
import com.pgc.alliedschoolsapp.utils.SingletonClass
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var ST: SingletonClass
    private val viewModel: HomeViewmodel by viewModels()
    lateinit var binding: ActivityHomeBinding
    val TAG = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        viewModel.getMovies()

        setUpObservers()

    }

    private fun setUpObservers() {

        viewModel.liveData.observe(this) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    hideLoader()

                    if (response.isSuccess) {

                        response.data?.let {
/*  val studentDataLocal = Gson().fromJson(
                                    response.data,
                                    GetStudentProfileResponseModel::class.java
                                )*/

                        } ?: run {
                            // data is null
                            ST.somethingWentWrong(this)


                        }


                    } else {
                        ST.somethingWentWrong(this)
                    }


                }
                Status.LOADING -> {

                    showLoader()

                }
                Status.ERROR -> {
                    Log.d(TAG, "onCreate: ")

                    ST.showToast(this, response.message!!)

                    hideLoader()

                }
            }


        }


    }

    private fun showLoader() {
        binding.loader.loaderCL.visibility = View.VISIBLE


    }

    private fun hideLoader() {
        binding.loader.loaderCL.visibility = View.GONE


    }


}
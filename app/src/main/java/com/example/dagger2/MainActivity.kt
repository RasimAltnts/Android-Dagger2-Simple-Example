package com.example.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import javax.inject.Inject

class MainActivity : AppCompatActivity(),IvehicleStatus {

    @Inject
    lateinit var mCar:Drive


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DaggerVehicle.builder()
            .drive(Drive())
            .build().inject(this)


        mCar.setListener(object : IvehicleStatus {
            override fun gas(isStatus: String) {
                println("Car Gas:$isStatus")
            }

            override fun brake(isStatus: String) {
                println("Car Brake $isStatus")
            }
        })
    }

    fun gas(view: View) {
        mCar.gas()
    }

    fun brake(view: View) {
        mCar.brake()
    }

    override fun gas(isStatus: String) {
        println(isStatus)
    }

    override fun brake(isStatus: String) {
        println(isStatus)
    }
}
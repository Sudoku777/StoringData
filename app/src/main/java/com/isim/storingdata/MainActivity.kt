package com.isim.storingdata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.isim.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences
    var ageFromPref: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        add(view)
//        delete(view)

        //SharedPreferences - XML - Key - Value
        sharedPref = this.getSharedPreferences("com.isim.storingdata", Context.MODE_PRIVATE)

        ageFromPref = sharedPref.getInt("age", -1)
        if (ageFromPref == -1) {
            binding.txtAge.text = "Your Age: "
        } else {
            binding.txtAge.text = "Your Age: ${ageFromPref}"
        }
    }

    fun add(view: View) {
        binding.button1.setOnClickListener {
            val myAge = binding.edtText.text.toString().toIntOrNull()

            if (myAge != null) {
                binding.txtAge.text = "Your age: ${myAge}"
                sharedPref.edit().putInt("age", myAge).apply() //apply()-значит сохранить данные
            }

        }
    }

    fun delete(view: View) {
        binding.button2.setOnClickListener {
            ageFromPref = sharedPref.getInt("age", -1)
            if (ageFromPref != -1) {
                sharedPref.edit().remove("age").apply()
                binding.txtAge.text = "Your age: "
            }
        }
    }
}
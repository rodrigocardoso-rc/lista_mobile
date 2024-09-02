package com.example.questao_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.questao_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mainVm: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { mainVm.addLike() }

        val nameObserver = Observer<String> { newName -> binding.textView2.text = newName}
        val surnameObserver = Observer<String> { newSurname -> binding.textView.text = newSurname}
        val likeObserver = Observer<Int> { newValue -> binding.progressBar.progress = newValue}
        val currImageObserver = Observer<Int> { newImage -> binding.imageView.setImageResource(newImage)}

        mainVm.name.observe(this, nameObserver)
        mainVm.surname.observe(this, surnameObserver)
        mainVm.percentage.observe(this, likeObserver)
        mainVm.currImage.observe(this, currImageObserver)
    }
}
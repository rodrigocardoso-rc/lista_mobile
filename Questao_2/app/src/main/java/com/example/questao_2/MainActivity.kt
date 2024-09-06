package com.example.questao_2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.questao_2.adapter.GlassItemAdapter
import com.example.questao_2.databinding.ActivityMainBinding
import com.example.questao_2.model.GlassItem
import com.example.questao_2.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val vm: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = vm

        val adapter = GlassItemAdapter(this, vm)
        binding.adapter = adapter

        binding.editTextNumber.setText(vm.weight.value.toString())

        binding.editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                var text = s.toString()

                text = text.trimStart('0').ifEmpty { "0" }
                vm.setWeight(Integer.parseInt(text))
            }
        })

        binding.executePendingBindings()
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("android:text")
        fun setIntText(view: EditText, value: Int?) {
            view.setText(value?.toString() ?: "")
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "android:text")
        fun getIntText(view: EditText): Int {
            return view.text.toString().toIntOrNull() ?: 0
        }

        @JvmStatic
        @BindingAdapter("android:textAttrChanged")
        fun setListener(view: EditText, listener: InverseBindingListener) {
            view.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    listener.onChange()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        @JvmStatic
        @BindingAdapter("atualizarLista")
        fun atualizarLista(view: RecyclerView, list: MutableLiveData<List<GlassItem>>?) {
            val adapter = view.adapter as? GlassItemAdapter
            list?.observe(view.context as LifecycleOwner) { newList ->
                adapter?.updateList(newList)
            }
        }

        @JvmStatic
        @BindingAdapter("setAdapter")
        fun setAdapter(view: RecyclerView, adapter: GlassItemAdapter?) {
            view.adapter = adapter
        }
    }
}

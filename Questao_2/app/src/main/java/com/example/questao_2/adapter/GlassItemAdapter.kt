package com.example.questao_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.questao_2.R
import com.example.questao_2.databinding.CardItemBinding
import com.example.questao_2.model.GlassItem
import com.example.questao_2.viewModel.MainViewModel

class GlassItemAdapter(private val lifecycleOwner: LifecycleOwner, vm: MainViewModel) :
    RecyclerView.Adapter<GlassItemAdapter.GlassItemViewHolder>() {
    private var listGlassItem: List<GlassItem>
    private val mainVm: MainViewModel

    init {
        this.listGlassItem = emptyList()
        mainVm = vm
    }

    fun updateList(newList: List<GlassItem>) {
        this.listGlassItem = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassItemViewHolder {
        val inflador = LayoutInflater.from(parent.context)
        val cardItem: CardItemBinding =
            DataBindingUtil.inflate(inflador, R.layout.card_item, parent, false)
        cardItem.setLifecycleOwner(lifecycleOwner)
        return GlassItemViewHolder(cardItem)
    }

    override fun onBindViewHolder(holder: GlassItemViewHolder, position: Int) {
        val glassItem: GlassItem = listGlassItem[position]
        holder.bind(glassItem, mainVm)
    }

    override fun getItemCount(): Int {
        return listGlassItem.size
    }

    class GlassItemViewHolder(cardItem: CardItemBinding) :
        RecyclerView.ViewHolder(cardItem.getRoot()) {
        private val binding: CardItemBinding = cardItem

        fun bind(gI: GlassItem?, vm: MainViewModel?) {
            binding.cardItem = gI
            binding.vm = vm
            binding.executePendingBindings()
        }
    }
}
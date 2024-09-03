package com.example.rickmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.databinding.RickyListsBinding
import com.example.rickmorty.model.Characters

class RickAdapter: ListAdapter<Characters, RickAdapter.MyViewHolder>(CharacterDiffCallBack()) {
    class CharacterDiffCallBack: DiffUtil.ItemCallback<Characters>() {

        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }

    }

    class MyViewHolder(private val binding: RickyListsBinding): RecyclerView.ViewHolder(binding.root) {

        companion object{
            fun inflateFrom(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RickyListsBinding.inflate(layoutInflater, parent, false)

                return MyViewHolder(binding)
            }
        }

        fun bind(character: Characters){
            binding.rickAndMorty = character

            /*Glide.with(binding.imageView.context)
                .load(character.image)
                .into(binding.imageView)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : MyViewHolder = MyViewHolder.inflateFrom(parent)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }


}
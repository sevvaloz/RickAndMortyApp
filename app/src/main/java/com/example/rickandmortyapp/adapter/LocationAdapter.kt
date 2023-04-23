package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemLocationBinding
import com.example.rickandmortyapp.models.models.location.Location
import com.example.rickandmortyapp.utils.LocationRowClickListener

class LocationAdapter(private val locationList: Array<Location>,
                      private val onLocationClickListener: LocationRowClickListener<Location>,
                      private val recyclerView: RecyclerView
                      ): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locationList[position]
        holder.binding.locationButton.text = location.name
        holder.binding.locationButton.setOnClickListener {

            holder.binding.locationButton.setBackgroundColor(holder.itemView.resources.getColor(R.color.green))
            for (i in locationList.indices) {
                if (i != position) {
                    val otherHolder = recyclerView.findViewHolderForAdapterPosition(i) as LocationViewHolder?
                    otherHolder?.itemView?.resources?.getColor(R.color.black)?.let { it1 -> otherHolder.binding.locationButton.setBackgroundColor(it1) }
                }
            }

            onLocationClickListener.onLocationRowClick(position, location)
        }


    }




}
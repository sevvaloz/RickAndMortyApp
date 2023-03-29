package com.example.rickandmortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.LocationData.Location
import com.example.rickandmortyapp.databinding.ItemLocationBinding

class LocationAdapter(private val locationList: List<Location>): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int =locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.binding.apply {
            locationButton.text = locationList[position].name
        }
    }
}
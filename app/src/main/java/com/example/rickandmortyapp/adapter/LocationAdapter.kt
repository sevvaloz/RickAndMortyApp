package com.example.rickandmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.databinding.ItemLocationBinding
import com.example.rickandmortyapp.models.models.location.Location
import com.example.rickandmortyapp.utils.LocationRowClickListener

class LocationAdapter(private val locationList: Array<Location>,
                      private val onLocationClickListener: LocationRowClickListener<Location>
                      ): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.binding.apply {
            val location = locationList[position]
            locationButton.text = location.name
            locationButton.setOnClickListener {
                onLocationClickListener.onLocationRowClick(position, location)
            }
        }
        /*holder.binding.locationButton.setOnClickListener {
            onLocationClickListener.onLocationRowClick(position, locationList[position])
        }*/
    }
}
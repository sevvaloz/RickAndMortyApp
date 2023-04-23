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
                      ): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var clickedPosition = -1

    class LocationViewHolder(val binding: ItemLocationBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locationList[position]
        holder.binding.locationButton.text = location.name
        holder.binding.locationButton.setBackgroundColor(
            if(position == clickedPosition){
                holder.itemView.resources.getColor(R.color.gray)
            } else{
                holder.itemView.resources.getColor(R.color.yellow)
            }
        )

        holder.binding.locationButton.setOnClickListener {
            if(clickedPosition != position){
                notifyItemChanged(clickedPosition)
                clickedPosition = position
                notifyItemChanged(clickedPosition)
                onLocationClickListener.onLocationRowClick(position, location)
            }
        }
    }


}
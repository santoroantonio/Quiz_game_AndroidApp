package com.example.quiz_game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter (private val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.textView2)
        val pointsText: TextView = itemView.findViewById(R.id.points)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.nameText.text = player.name
        holder.pointsText.text = player.points.toString()
    }

    override fun getItemCount(): Int = players.size

}
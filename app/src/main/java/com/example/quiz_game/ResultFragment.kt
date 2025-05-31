package com.example.quiz_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repeatButton = view.findViewById<ImageView>(R.id.repeat)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            restart()
        }

        repeatButton.setOnClickListener{
            restart()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val icon= view.findViewById<ImageView>(R.id.icona)
        val text= view.findViewById<TextView>(R.id.result)
        val score= view.findViewById<TextView>(R.id.scores)
        val name= view.findViewById<TextView>(R.id.username)

        val listaGiocatori = listOf(
            Player(gameViewModel.playerName, gameViewModel.points),
            Player("Marco", 4),
            Player("Giulia", 3),
            Player("Luca", 2),
            Player("Sara", 5),
            Player("Francesco", 1),
            Player("Elena", 4),
            Player("Davide", 2),
            Player("Marta", 3),
            Player("Simone", 0),
            Player("Alice", 3),
            Player("Matteo", 1),
            Player("Chiara", 2),
            Player("Stefano", 4),
            Player("Laura", 5),
            Player("Fabio", 0),
            Player("Valentina", 1),
            Player("Giorgio", 2),
            Player("Irene", 4),
            Player("Paolo", 3),
            Player("Federica", 5),
            Player("Riccardo", 0),
            Player("Veronica", 2),
            Player("Giovanni", 3),
            Player("Lucia", 1),
            Player("Alessandro", 4),
            Player("Beatrice", 2),
            Player("Andrea", 1),
            Player("Martina", 0),
            Player("Daniele", 3)
        ).sortedByDescending { it.points }

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = PlayerAdapter(listaGiocatori)

        name.text= gameViewModel.playerName
        score.text= getString(R.string.score_format, gameViewModel.points, gameViewModel.nlevels)

        if(gameViewModel.points>(gameViewModel.nlevels/2)){
            text.text= getString(R.string.victory_msg)
            icon.setImageResource(R.drawable.trofeo)
        }else{
            text.text= getString(R.string.defeat_message)
            icon.setImageResource(R.drawable.sad)
        }

    }

    private fun resetPlayer(){
        gameViewModel.playerName = ""
        gameViewModel.points = 0
    }

    private fun restart(){
        resetPlayer()

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.quizFragment,true)
            .build()

        findNavController().navigate(R.id.startFragment,null,navOptions)
    }
}
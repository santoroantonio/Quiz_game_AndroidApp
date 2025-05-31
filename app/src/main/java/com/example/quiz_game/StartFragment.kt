package com.example.quiz_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment(R.layout.fragment_start) {

    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val insertName = view.findViewById<EditText>(R.id.editTextPlayerName)
        val startButton = view.findViewById<Button>(R.id.buttonStart)

        startButton.setOnClickListener{
            gameViewModel.playerName = insertName.text.toString().trim()

            if (gameViewModel.playerName.isNotEmpty()){
                findNavController().navigate(R.id.to_quizFragment)
            }
            else {
                Toast.makeText(requireContext(), "Inserisci il tuo nome!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
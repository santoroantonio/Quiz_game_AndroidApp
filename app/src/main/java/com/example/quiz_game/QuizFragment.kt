package com.example.quiz_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val gameViewModel: GameViewModel by activityViewModels()

    private lateinit var flags: List<Question>
    private var cont = 0
    private var choice: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            restart()
        }

        flags= listOf (
            Question(R.drawable.giappone, "Giappone"),
            Question(R.drawable.canada, "Canada"),
            Question(R.drawable.svizzera, "Svizzera"),
            Question(R.drawable.belgium, "Belgio"),
            Question(R.drawable.china, "China"),
            Question(R.drawable.france, "Francia"),
            Question(R.drawable.germany, "Germania"),
            Question(R.drawable.italy, "Italia"),
            Question(R.drawable.singapore, "Singapore"),
            Question(R.drawable.unitedstates, "Stati Uniti"),
            Question(R.drawable.unitedkingdom, "Regno Unito"),
            Question(R.drawable.colombia, "Colombia"),
        )

        show()

        val button1 = view.findViewById<Button>(R.id.button1)
        val button2 = view.findViewById<Button>(R.id.button2)

        button1.setOnClickListener{ check(button1.text.toString()) }
        button2.setOnClickListener{ check(button2.text.toString()) }

    }

    private fun show (){
        do{
            choice= flags.indices.random()
        }while(flags[choice].sign==1)

        val question = flags[choice]
        var alternativa: Int

        question.sign=1

        val button1 = view?.findViewById<Button>(R.id.button1)
        val button2 = view?.findViewById<Button>(R.id.button2)
        val image = view?.findViewById<ImageView>(R.id.bandiera)

        val opz= (0..1).random()

        do{
            alternativa= flags.indices.random()
        }while(alternativa==choice)

        image?.setImageResource(question.img)

        if(opz==1){
            button1?.text = question.flag
            button2?.text = flags[alternativa].flag
        }else{
            button2?.text = question.flag
            button1?.text = flags[alternativa].flag
        }
    }


    private fun check (answer: String) {
        if (answer == flags[choice].flag){
            gameViewModel.points++
        }

        if(cont < (gameViewModel.nlevels-1)){
            cont++
            show()
        }else findNavController().navigate(R.id.to_resultFragment)
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
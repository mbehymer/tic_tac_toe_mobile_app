package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

var positions = arrayOf("_ ","_ ","_ ","_ ","_ ","_ ","_ ","_ ","_ ")
var turn = 0
var player = "X"
var winner = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val top_left_btn = findViewById<Button>(R.id.top_left_btn)
        val center_top_btn = findViewById<Button>(R.id.center_top_btn)
        val top_right_btn = findViewById<Button>(R.id.top_right_btn)
        val center_left_btn = findViewById<Button>(R.id.center_left_btn)
        val center_btn = findViewById<Button>(R.id.center_btn)
        val center_right_btn = findViewById<Button>(R.id.center_right_btn)
        val bottom_left_btn = findViewById<Button>(R.id.bottom_left_btn)
        val center_bottom_btn = findViewById<Button>(R.id.center_bottom_btn)
        val bottom_right_btn = findViewById<Button>(R.id.bottom_right_btn)
        val reset_btn = findViewById<Button>(R.id.reset_btn)


        top_left_btn.setOnClickListener { placePlayer(1) }
        center_top_btn.setOnClickListener { placePlayer(2) }
        top_right_btn.setOnClickListener { placePlayer(3) }
        center_left_btn.setOnClickListener { placePlayer(4) }
        center_btn.setOnClickListener { placePlayer(5) }
        center_right_btn.setOnClickListener { placePlayer(6) }
        bottom_left_btn.setOnClickListener { placePlayer(7) }
        center_bottom_btn.setOnClickListener { placePlayer(8) }
        bottom_right_btn.setOnClickListener { placePlayer(9) }
        reset_btn.setOnClickListener { reset() }


    }

    private fun placePlayer(pos: Int) {
        if (winner == "") {
            try {

                val display = findViewById<TextView>(R.id.display)

                if (positions.get(pos - 1) != "_ ") {
                    return
                }

                positions.set(pos - 1, player)

                display.text = updateDisplay()

                if (isWinner()) {
                    winner = player
                    playerWinMessage()
                    return
                }

                if (noWinner()) {
                    winner = "TIE"
                    return
                }

                updatePlayer()

            } catch (e: Exception) {
                Toast.makeText(this, "Invalid Temp input", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateDisplay(): String {

        var displayStr = ""
        var count = 1
            for (p in positions) {

                if ((count%3 != 0)) {
                    displayStr += " " + p + " |"
                } else {
                    displayStr += " " + p + " \n"
                }
                count++
        }
        return displayStr
    }

    private fun updatePlayer() {
        val playerTurn = findViewById<TextView>(R.id.playerTurn)
        val beginningText = "Player "
        val endText = " turn"
        var text = ""
        if (turn%2 == 1) {
            player = "X"
        } else {
            player = "O"
        }
        text += beginningText + player + "'s" + endText
        playerTurn.text = text
        turn++
    }
    private fun checkFromCenter(): Boolean {
        println("${positions.get(4)} == ${player}")
        if (positions.get(4) == player) {
            for (i in 0..3) {
                println("${i}: ${positions.get(i)} == ${player} && ${positions.get(8 - i)} == ${player}")
                if (((positions.get(i) == "O" && positions.get(8 - i) == "O") && (player == "0"))|| ((positions.get(i) == "X" && positions.get(8 - i) == "X") && (player == "X"))){
                    Toast.makeText(this, "winning center", Toast.LENGTH_LONG)
                    return true
                }
            }
        }

        return false;
    }
    private fun checkFromSide(): Boolean {
//        var i = 1
        for (j in 1..3) {
            var i = ((j-1)*2) + 1
            if (positions.get(i) == player) {
                if (i == 1 || i == 7) {
                    if (positions.get(i - 1) == player && positions.get(i + 1) == player) {
                        return true;
                    }
                }
                else {
                    if (positions.get(i - 3) == player && positions.get(i + 3) == player) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private fun isWinner(): Boolean {

        return (checkFromCenter() || checkFromSide())

    }


    private fun playerWinMessage() {
        val playerTurn = findViewById<TextView>(R.id.playerTurn)
        val beginningText = "Player "
        val endText = " Won"
        var text = ""
        if (turn%2 == 0) {
            player = "X"
        } else {
            player = "O"
        }
        text += beginningText + player + endText
        playerTurn.text = text
    }

    private fun catsGameMessage() {
        val playerTurn = findViewById<TextView>(R.id.playerTurn)
        val text = "Cat's Game!"
        playerTurn.text = text
    }

    private fun noWinner(): Boolean {
        if ((turn >= 8) && (winner == "")) {
            catsGameMessage()
            return true
        }

        return false
    }

    private fun reset(){
        val playerTurn = findViewById<TextView>(R.id.playerTurn)
        val display = findViewById<TextView>(R.id.display)
        playerTurn.text = "Player X's turn"
        positions = arrayOf("_ ","_ ","_ ","_ ","_ ","_ ","_ ","_ ","_ ")
        display.text = updateDisplay()
        turn = 0
        player = "X"
        winner = ""

    }

}


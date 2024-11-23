package com.example.atividadepratica_1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Variáveis para pontuação
    private var playerScore = 0
    private var cpuScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos elementos do layout
        val btnTesoura: ImageButton = findViewById(R.id.btnTesoura)
        val btnPedra: ImageButton = findViewById(R.id.btnPedra)
        val btnPapel: ImageButton = findViewById(R.id.btnPapel)
        val tvResult: TextView = findViewById(R.id.tvResult)
        val tvCPUChoice: TextView = findViewById(R.id.tvCPUChoice)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val btnNovaPartida: Button = findViewById(R.id.btnNovaPartida)

        // Lista com as opções possíveis
        val options = arrayOf("TESOURA", "PEDRA", "PAPEL")

        // Função para determinar o vencedor
        fun playRound(playerChoice: String) {
            val cpuChoice = options[Random.nextInt(options.size)]

            // Usando as strings de recursos com placeholders
            tvResult.text = getString(R.string.player_choice, playerChoice)
            tvCPUChoice.text = getString(R.string.cpu_choice, cpuChoice)

            // Lógica para determinar quem ganhou
            when {
                (playerChoice == "TESOURA" && cpuChoice == "PAPEL") ||
                        (playerChoice == "PEDRA" && cpuChoice == "TESOURA") ||
                        (playerChoice == "PAPEL" && cpuChoice == "PEDRA") -> {
                    playerScore++
                }
                playerChoice != cpuChoice -> {
                    cpuScore++
                }
            }

            // Atualizar a pontuação usando recurso de string com placeholders
            tvScore.text = getString(R.string.score, playerScore, cpuScore)
        }

        // Configurando os botões para as opções
        btnTesoura.setOnClickListener { playRound("TESOURA") }
        btnPedra.setOnClickListener { playRound("PEDRA") }
        btnPapel.setOnClickListener { playRound("PAPEL") }

        // Botão para reiniciar o jogo
        btnNovaPartida.setOnClickListener {
            playerScore = 0
            cpuScore = 0
            tvScore.text = getString(R.string.score, playerScore, cpuScore)
            tvResult.text = getString(R.string.player_choice, "")
            tvCPUChoice.text = getString(R.string.cpu_choice, "")
        }
    }
}

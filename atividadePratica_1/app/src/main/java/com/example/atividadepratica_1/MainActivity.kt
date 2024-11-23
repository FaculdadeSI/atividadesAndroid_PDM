package com.example.atividadepratica_1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Variáveis para armazenar a pontuação do jogador e da CPU
    private var playerScore = 0
    private var cpuScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências aos elementos do layout através do findViewById
        val btnTesoura: ImageButton = findViewById(R.id.btnTesoura)
        val btnPedra: ImageButton = findViewById(R.id.btnPedra)
        val btnPapel: ImageButton = findViewById(R.id.btnPapel)
        val tvResult: TextView = findViewById(R.id.tvResult)
        val tvCPUChoice: TextView = findViewById(R.id.tvCPUChoice)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val btnNovaPartida: Button = findViewById(R.id.btnNovaPartida)

        // Lista contendo as opções do jogo: Tesoura, Pedra e Papel
        val options = arrayOf("TESOURA", "PEDRA", "PAPEL")

        // Função para executar uma rodada do jogo, determinando o vencedor
        fun playRound(playerChoice: String) {
            // A CPU faz uma escolha aleatória
            val cpuChoice = options[Random.nextInt(options.size)]

            // Exibe a escolha do jogador e da CPU utilizando recursos de string com placeholders
            tvResult.text = getString(R.string.player_choice, playerChoice)
            tvCPUChoice.text = getString(R.string.cpu_choice, cpuChoice)

            // Lógica para determinar o vencedor da rodada
            when {
                // Condições em que o jogador vence
                (playerChoice == "TESOURA" && cpuChoice == "PAPEL") ||
                        (playerChoice == "PEDRA" && cpuChoice == "TESOURA") ||
                        (playerChoice == "PAPEL" && cpuChoice == "PEDRA") -> {
                    playerScore++  // Incrementa a pontuação do jogador
                }
                // Condição em que a CPU vence
                playerChoice != cpuChoice -> {
                    cpuScore++  // Incrementa a pontuação da CPU
                }
            }

            // Atualiza a exibição da pontuação no layout, utilizando a string com placeholders
            tvScore.text = getString(R.string.score, playerScore, cpuScore)
        }

        // Configuração dos listeners de clique para os botões de escolha do jogador
        btnTesoura.setOnClickListener { playRound("TESOURA") }
        btnPedra.setOnClickListener { playRound("PEDRA") }
        btnPapel.setOnClickListener { playRound("PAPEL") }

        // Configuração do botão "Nova Partida" para reiniciar o jogo
        btnNovaPartida.setOnClickListener {
            // Reinicia as pontuações do jogador e da CPU
            playerScore = 0
            cpuScore = 0

            // Atualiza a exibição da pontuação
            tvScore.text = getString(R.string.score, playerScore, cpuScore)

            // Limpa as escolhas anteriores
            tvResult.text = getString(R.string.player_choice, "")
            tvCPUChoice.text = getString(R.string.cpu_choice, "")
        }
    }
}

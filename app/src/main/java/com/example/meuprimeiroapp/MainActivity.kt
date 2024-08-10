package com.example.meuprimeiroapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Button
import android.widget.TextView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    // Declaração de variáveis que irão armazenar as referências aos TextViews.
    private lateinit var textViewData: TextView
    private lateinit var textViewHora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita o modo Edge-to-Edge, que estende a interface do app para as áreas
        // ocupadas por elementos do sistema (como a barra de status).
        enableEdgeToEdge()
        // Define o layout da atividade.
        setContentView(R.layout.activity_main)
        // Adiciona um listener para ajustes de padding baseados nos insets do sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtém as margens dos elementos do sistema (barra de status, etc.).
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Define os paddings no layout com base nos insets do sistema.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializa as variáveis textViewData e textViewHora associando-as aos componentes do layout.
        textViewData = findViewById(R.id.textViewData)
        textViewHora = findViewById(R.id.textViewHora)

        // Inicializa os botões associando-os aos componentes do layout.
        val buttonData: Button = findViewById(R.id.buttonData)
        val buttonHora: Button = findViewById(R.id.buttonHora)

        // Define o comportamento dos botões quando clicados:
        // Quando buttonData é clicado, chama a função showDatePickerDialog().
        buttonData.setOnClickListener { showDatePickerDialog() }
        // Quando buttonHora é clicado, chama a função showTimePickerDialog().
        buttonHora.setOnClickListener { showTimePickerDialog() }
    }

    private fun showDatePickerDialog() {
        // Obtém uma instância do calendário com a data atual.
        val calendar = Calendar.getInstance()
        // Extrai o ano, mês e dia atuais do calendário.
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Cria uma instância do DatePickerDialog para o usuário escolher a data.
        val datePickerDialog = DatePickerDialog(this, { _, year1, month1, dayOfMonth ->
            // Formata a data selecionada pelo usuário como uma string "dia/mês/ano".
            val selectedDate = "$dayOfMonth/${month1 + 1}/$year1"
            // Define o texto do textViewData para exibir a data selecionada.
            textViewData.text = selectedDate
        }, year, month, day)
        // Exibe o diálogo de seleção de data na tela.
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        // Obtém uma instância do calendário com a hora atual.
        val calendar = Calendar.getInstance()
        // Extrai a hora e os minutos atuais do calendário.
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Cria uma instância do TimePickerDialog para o usuário escolher a hora.
        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute1 ->
            // Formata a hora selecionada pelo usuário como uma string "hora:minuto".
            val selectedTime = "$hourOfDay:${String.format("%02d", minute1)}"
            // Define o texto do textViewHora para exibir a hora selecionada.
            textViewHora.text = selectedTime
        }, hour, minute, true)
        // Exibe o diálogo de seleção de hora na tela.
        timePickerDialog.show()
    }
}

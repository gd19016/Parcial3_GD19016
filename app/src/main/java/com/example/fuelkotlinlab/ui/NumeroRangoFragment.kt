package com.example.fuelkotlinlab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.fuelkotlinlab.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result

class NumeroRangoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_numero_rango, container, false)
        val btn: Button = fragment.findViewById(R.id.convertirButton)
        val editText: EditText = fragment.findViewById(R.id.numberEditText)
        val resultText: TextView = fragment.findViewById(R.id.resultText)
        btn.setOnClickListener {
            val numero: Int? = editText.text.toString().toIntOrNull()

            if (numero == null) {
                "Debe especificar un número.".also { resultText.text = it }
                return@setOnClickListener
            }

            if (numero < 0) {
                "El número debe ser un valor positivo.".also { resultText.text = it }
                return@setOnClickListener
            }

            Fuel.get(
                "/parcial3/pares_en_rango_cerrado.php",
                listOf("numero" to editText.text)
            ).responseJson { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        resultText.text = result.getException().toString()
                    }
                    is Result.Success -> {
                        val data = result.get().obj()
                        ("Los números pares desde 0 hasta $numero (incluyendo el cero) son: " + data.getString("numero")).also { resultText.text = it }
                    }
                }
            }
        }
        return fragment
    }
}
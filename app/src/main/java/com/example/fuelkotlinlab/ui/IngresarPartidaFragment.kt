package com.example.fuelkotlinlab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fuelkotlinlab.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import java.lang.Exception

class IngresarPartidaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_ingresar_partida, container, false)
        val btn: Button = fragment.findViewById(R.id.guardarButton)
        val juego: EditText = fragment.findViewById(R.id.juegoEdit)
        val cantParticipantes: EditText = fragment.findViewById(R.id.participantesEdit)
        val ganador: EditText = fragment.findViewById(R.id.ganadorEdit)
        btn.setOnClickListener {
            Fuel.get(
                "/parcial3/ws_ingresar_partida.php",
                listOf(
                    "juego" to juego.text,
                    "participantes" to cantParticipantes.text,
                    "ganador" to ganador.text
                )
            ).responseJson { _, _, result ->
                requireActivity().runOnUiThread {
                    when (result) {
                        is Result.Failure -> {
                            Toast.makeText(context, result.getException().toString(),
                                Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            try {
                                val data = result.get().obj()
                                if (data.has("resultado") && data.getString("resultado") ==
                                    "1") {
                                    Toast.makeText(context, getString(R.string.ws_exito),
                                        Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, getString(R.string.ws_error),
                                        Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(context, result.get().content,
                                    Toast.LENGTH_SHORT).show()
                            }}}}
            }
        }
        return fragment
    }
}

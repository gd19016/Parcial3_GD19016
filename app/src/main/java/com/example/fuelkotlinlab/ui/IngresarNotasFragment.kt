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
class IngresarNotasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_ingresar_notas, container, false)
        val btn: Button = fragment.findViewById(R.id.guardarButton)
        val carnet: EditText = fragment.findViewById(R.id.carnetEdit)
        val codMat: EditText = fragment.findViewById(R.id.codMatEdit)
        val ciclo: EditText = fragment.findViewById(R.id.cicloEdit)
        val nota: EditText = fragment.findViewById(R.id.noteEdit)
        btn.setOnClickListener {
            Fuel.get(
                "/ws_nota_insert.php",
                listOf(
                    "carnet" to carnet.text,
                    "codmateria" to codMat.text,
                    "ciclo" to ciclo.text,
                    "notafinal" to nota.text
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

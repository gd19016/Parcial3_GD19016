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
class PromedioNotasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_promedio_notas,
            container, false)
        val btn: Button = fragment.findViewById(R.id.obtenerButton)
        val editText: EditText = fragment.findViewById(R.id.carnetEditText)
        val resultText: TextView = fragment.findViewById(R.id.promedioText)
        btn.setOnClickListener {
            Fuel.get(
                "/ws_db_carnet_group.php",
                listOf("carnet" to editText.text)
            ).responseJson { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        resultText.text = result.getException().toString()
                    }
                    is Result.Success -> {
                        val data = result.get().array()
                        if (data.length() > 0) {
                            resultText.text =
                                data.getJSONObject(0).getString("PROMEDIO")
                        } else {
                            resultText.text = getString(R.string.no_alumno)
                        }}}}
        }
        return fragment
    }
}

package com.example.dbaccessv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_find.*


class FindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find)
        etv_ID.addTextChangedListener(textWatcher)

        btn_Find.setOnClickListener() {
            val db = DataBaseHandler(this)

            etv_Name.setText(db.findData(etv_ID.text.toString().toInt()).name)
            etv_City.setText(db.findData(etv_ID.text.toString().toInt()).city)

            if (etv_Name.text.toString() == "" && etv_City.text.toString() == "") {
                Toast.makeText(this, "No Entry Found", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        }

        btn_Back.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            btn_Find.isEnabled = etv_ID.text.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}

package com.example.dbaccessv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        etv_Name.addTextChangedListener(textWatcher)
        etv_City.addTextChangedListener(textWatcher)
        val db = DataBaseHandler(this)

        btn_Save.setOnClickListener {
            if (etv_Name.text.toString().isNotEmpty() &&
                etv_City.text.toString().isNotEmpty()) {
                db.insertData(etv_Name.text.toString(), etv_City.text.toString())
            } else {
                Toast.makeText(this, "PLEASE FILL THE FIELDS FIRST", Toast.LENGTH_SHORT).show()
            }
            etv_Name.setText("")
            etv_City.setText("")
        }

        btn_Back.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            btn_Save.isEnabled = etv_Name.text.toString().isNotEmpty() && etv_City.text.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}

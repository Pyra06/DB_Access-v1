package com.example.dbaccessv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_delete.btn_Delete

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        etv_ID.addTextChangedListener(textWatcher)

        btn_Delete.setOnClickListener() {
            val db = DataBaseHandler(this)

            val name = (db.findData(etv_ID.text.toString().toInt()).name)
            val city = (db.findData(etv_ID.text.toString().toInt()).city)

            if (name == "" && city == "") {
                Toast.makeText(this, "Entry Not Found", Toast.LENGTH_SHORT).show()
            } else {
                db.deleteData(etv_ID.text.toString().toInt())
                Toast.makeText(this, "Entry Deleted", Toast.LENGTH_SHORT).show()
                etv_ID.setText("")
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
            btn_Delete.isEnabled = etv_ID.text.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}


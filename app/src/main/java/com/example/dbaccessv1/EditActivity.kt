package com.example.dbaccessv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        etv_ID.addTextChangedListener(textWatcher)
        etv_Name.addTextChangedListener(textWatcher)
        etv_City.addTextChangedListener(textWatcher)
        val db = DataBaseHandler(this)

        btn_Search.setOnClickListener() {
            etv_Name.setText(db.findData(etv_ID.text.toString().toInt()).name)
            etv_City.setText(db.findData(etv_ID.text.toString().toInt()).city)

            if (etv_Name.text.toString() == "" && etv_City.text.toString() == "") {
                Toast.makeText(this, "Entry Not Found", Toast.LENGTH_SHORT).show()
            }
        }

        btn_EditList.setOnClickListener() {
            val name = (db.findData(etv_ID.text.toString().toInt()).name)
            val city = (db.findData(etv_ID.text.toString().toInt()).city)

            if ((name == "" && city == "") || (etv_Name.text.toString() == "" && etv_City.text.toString() == "")) {
                Toast.makeText(this, "Entry Not Found", Toast.LENGTH_SHORT).show()
            } else {
                db.editData(etv_ID.text.toString().toInt(), userName = etv_Name.text.toString(), userCity = etv_City.text.toString())
                db.close()
                Toast.makeText(this, "Entry Edited Successfully", Toast.LENGTH_SHORT).show()
                etv_Name.setText("")
                etv_City.setText("")
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
            btn_EditList.isEnabled = etv_ID.text.toString().isNotEmpty() && etv_Name.text.toString().isNotEmpty() && etv_City.text.toString().isNotEmpty()
            btn_Search.isEnabled = etv_ID.text.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}

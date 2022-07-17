package com.example.dbaccessv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Add.setOnClickListener() {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        btn_Edit.setOnClickListener() {

            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        btn_Delete.setOnClickListener() {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        }

        btn_Find.setOnClickListener() {
            val intent = Intent(this, FindActivity::class.java)
            startActivity(intent)
        }

        btn_List.setOnClickListener() {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        btn_Quit.setOnClickListener() {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
    }
}

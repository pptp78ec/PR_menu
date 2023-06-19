package com.example.pr_menu

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val headerView =  findViewById<TextView>(R.id.selectedMenuItem)
        headerView.focusable = View.NOT_FOCUSABLE
        headerView.isFocusableInTouchMode = false
        headerView.isClickable = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val headerView = findViewById<TextView>(R.id.selectedMenuItem)
        return when(item.itemId){
            R.id.action_settings -> {
//                headerView.focusable = View.FOCUSABLE
//                headerView.isFocusableInTouchMode = true
//                headerView.isClickable = true
                val li = LayoutInflater.from(this)
                val promptsView: View = li.inflate(R.layout.alert_dialog, null)
                promptsView.findViewById<EditText>(R.id.etUserInput).text = findViewById<EditText>(R.id.selectedMenuItem).text

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Edit text...")
                builder.setCancelable(false)
                builder.setView(promptsView)
                builder.setPositiveButton("Save", fun(_: DialogInterface, _: Int){
                    findViewById<EditText>(R.id.selectedMenuItem).text = promptsView.findViewById<EditText>(R.id.etUserInput).text
                })
                builder.setNegativeButton("Cancel", fun(dialog :DialogInterface, _:Int){ dialog.cancel() })
                val dialog =  builder.create()
                dialog.show()
                true
            }
            R.id.save_settings -> {
                val li = LayoutInflater.from(this)
                val promptsView: View = li.inflate(R.layout.alert_dialog_read, null)
                promptsView.findViewById<TextView>(R.id.readonlyshow).text = findViewById<EditText>(R.id.selectedMenuItem).text

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Edit text...")
                builder.setCancelable(false)
                builder.setView(promptsView)
                builder.setPositiveButton("OK", fun(dialog: DialogInterface, _: Int){
                   dialog.cancel()
                })
                builder.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
package com.miba.shoppingcart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.*
import com.miba.shoppingcart.entities.ShoppingItem

import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_add_item.view.*

class MainActivity: AppCompatActivity() {

    private var firebaseDB: DatabaseReference? = null
    private val availableItems: List<ShoppingItem>? = null
    private lateinit var shoppingItemsDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        firebaseDB = FirebaseDatabase.getInstance().reference
        shoppingItemsDB = firebaseDB!!.child("shopping-items")

        btnSend2Firebase.setOnClickListener(View.OnClickListener {
            sendData()
        })

        btnDownloadFromFirebase.setOnClickListener(View.OnClickListener {
            downloadData()
        })

        shoppingItemsDB.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d(KApp.LOG_MIBA, "▲ WARNING: ValueListener.onCancelled")
            }

            override fun onDataChange(p0: DataSnapshot?) {
                Log.d(KApp.LOG_MIBA, "ValueListener.onDataChange")
                val children = p0!!.children
                Log.d(KApp.LOG_MIBA, "DB data count: " + children.count())
                Snackbar.make(findViewById(R.id.clMain), "Je evidováno " + children.count() + " záznamů.", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
            }

        })

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            move2RedScreen()
        }
    }

    private fun downloadData() {
        //shoppingItemsDB.get
    }

    fun sendData() {
        val addItemDialog = AlertDialog.Builder(this).create()
        addItemDialog.setTitle(R.string.dialog_add_title)
        addItemDialog.setMessage(getString(R.string.dialog_add_message))
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null, false)
        addItemDialog.setView(view)

        addItemDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.button_storno), {
            dialogInterface, i ->
            Snackbar.make(view, getString(R.string.action_interrupt), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
        })

        addItemDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.button_ok), {
            dialogInterface, i ->

            val name = view.etName.text
            val description = view.etDescription.text

            val shoppingItemData = HashMap<String, String>()
            shoppingItemData.put("name", name.toString().trim())
            shoppingItemData.put("description", description.toString().trim())

            shoppingItemsDB.push().setValue(shoppingItemData).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    Log.d(KApp.LOG_MIBA, "MainActivity -> uspesne ulozeno")
                    Snackbar.make(findViewById(R.id.clMain), "Úspěšně uloženo.", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show()
                } else {
                    Log.d(KApp.LOG_MIBA, "MainActivity -> nastala chyba")
                    Snackbar.make(findViewById(R.id.clMain), "Nastala chyba.", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show()
                }
            }
        })

        addItemDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else if (id == R.id.action_red_screen) {
            move2RedScreen()
        } else super.onOptionsItemSelected(item)
    }

    private fun move2RedScreen(): Boolean {
        val intent = Intent(this, RedScreen::class.java)
        startActivity(intent)
        return true
    }
}

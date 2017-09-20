package com.miba.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.miba.shoppingcart.adapters.ShopListAdapter
import com.miba.shoppingcart.entities.ShoppingItem
import kotlinx.android.synthetic.main.content_red_screen.*
import kotlinx.android.synthetic.main.dialog_add_item.*
import kotlinx.android.synthetic.main.dialog_add_item.view.*

class RedScreen : AppCompatActivity() {

    private var shoppingAdapter: ShopListAdapter? = null
    private var goods: ArrayList<ShoppingItem>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_red_screen)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        goods = ArrayList<ShoppingItem>()
        goods!!.add(ShoppingItem("Chleba", "Znacka FitBread"))
        goods!!.add(ShoppingItem("Mléko", "Znacka FitMilk"))
        Log.d(KApp.LOG_MIBA, "RedScreen -> goods: " + goods)
        Log.d(KApp.LOG_MIBA, "RedScreen -> goods 1: " + goods!!.get(0).name)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        shoppingAdapter = ShopListAdapter(this, goods!!, R.layout.list_simple)
        rvShopList.layoutManager = layoutManager
        rvShopList.adapter = shoppingAdapter

        srlRedScreen.setOnRefreshListener {
            shoppingAdapter!!.setData(goods!!)
            shoppingAdapter!!.notifyDataSetChanged()
            srlRedScreen.isRefreshing = false
        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            val addItemDialog = AlertDialog.Builder(this).create()
            addItemDialog.setTitle("Nova polozka")
            addItemDialog.setMessage("Zadejte nazev a popis nove polozky.")
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null, false)
            addItemDialog.setView(view)

            addItemDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Storno", {
                dialogInterface, i ->
                Snackbar.make(view, "Akce zrusena", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
            })

            addItemDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                dialogInterface, i ->
                val name = view.etName
                val description = view.etDescription
                goods!!.add(ShoppingItem(name.text.toString(), description.text.toString()))
                shoppingAdapter!!.setData(goods!!)
                Snackbar.make(view, "Byla přidána nová položka: " + goods!![goods!!.size - 1].name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            })

            addItemDialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_red, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else if (id == R.id.action_red_screen) {
            move2BlueScreen()
        } else super.onOptionsItemSelected(item)
    }

    private fun move2BlueScreen(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return true
    }
}

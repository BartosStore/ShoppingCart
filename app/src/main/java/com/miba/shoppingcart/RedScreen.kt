package com.miba.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.miba.shoppingcart.adapters.ShopListAdapter
import com.miba.shoppingcart.entities.ShoppingItem
import kotlinx.android.synthetic.main.content_red_screen.*

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
            Snackbar.make(view, "Number of items: " + shoppingAdapter!!.itemCount, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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

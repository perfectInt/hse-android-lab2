package io.sultanov.lab2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity: AppCompatActivity(), AddItemDialog.AddItemDialogListener,
                    RecyclerAdapter.OnDeleteItemClickListener,
                    RecyclerAdapter.OnEditItemClickListener,
                    EditItemDialog.EditItemDialogListener {

    private val adapter = RecyclerAdapter()
    private val products = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        adapter.setOnDeleteItemClickListener(this)
        adapter.setOnEditItemClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun openDialogMenu(view: View) {
        val dialog = AddItemDialog()
        dialog.show(supportFragmentManager, "AddItemDialog")
    }

    override fun onItemAdded(itemName: String, itemQuantity: Int) {
        val item = Item(itemName, itemQuantity)
        products.add(item)
        adapter.updateItems(products)
    }

    override fun onDeleteItemClick(position: Int) {
        products.removeAt(position)
        adapter.updateItems(products)
    }

    override fun onEditItemClick(position: Int) {
        val currentItem = products[position]
        val editDialog = EditItemDialog(currentItem.itemName, currentItem.itemQuantity)
        editDialog.show(supportFragmentManager, "editItemDialog")
    }

    override fun onEditItem(itemName: String, itemQuantity: Int) {
        val item = Item(itemName, itemQuantity)
        products[adapter.editingPosition] = item
        adapter.updateItems(products)
    }
}
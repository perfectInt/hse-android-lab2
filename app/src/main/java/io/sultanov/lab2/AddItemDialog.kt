package io.sultanov.lab2

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddItemDialog : DialogFragment() {

    interface AddItemDialogListener {
        fun onItemAdded(itemName: String, itemQuantity: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.add_item_dialog, null)

        val editTextItemName = view.findViewById<EditText>(R.id.itemName)
        val editTextItemQuantity = view.findViewById<EditText>(R.id.itemQuantity)

        builder.setView(view)
            .setPositiveButton("Add") {_, _ ->
                val itemName = editTextItemName.text.toString()
                val itemQuantity = editTextItemQuantity.text.toString().toInt()

                val listener = activity as AddItemDialogListener?
                listener?.onItemAdded(itemName, itemQuantity)
            }
            .setNegativeButton("Cancel") {_, _ ->
                dialog?.cancel()
            }

        return builder.create()
    }
}
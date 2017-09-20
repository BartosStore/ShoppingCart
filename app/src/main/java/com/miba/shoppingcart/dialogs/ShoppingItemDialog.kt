package com.miba.shoppingcart.dialogs

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import com.miba.shoppingcart.KApp

/**
 * Created by miba on 20.09.2017.
 */
class ShoppingItemDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(KApp.LOG_MIBA, "ShoppingItemDialog -> onCreateDialog")

        return super.onCreateDialog(savedInstanceState)
    }

}
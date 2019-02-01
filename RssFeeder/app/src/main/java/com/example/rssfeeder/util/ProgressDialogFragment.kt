package com.example.rssfeeder.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import com.example.rssfeeder.R

class ProgressDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = ProgressDialog(activity)
        dialog.setMessage(getString(R.string.loading_text))
        dialog.isIndeterminate = true
        dialog.setCancelable(false)

        // Disable the back button
        val keyListener = DialogInterface.OnKeyListener { dialog, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
        dialog.setOnKeyListener(keyListener)
        return dialog
    }

    companion object {


        fun newInstance(): ProgressDialogFragment {

            val frag = ProgressDialogFragment()
            return frag
        }
    }


}
package com.ridwan1214.challengech5

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class ResultDialogFragment : DialogFragment() {

    private var nama: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvResult).text = nama
        view.findViewById<TextView>(R.id.tvBtnMenu).setOnClickListener { activity?.finish() }
        view.findViewById<TextView>(R.id.tvBtnReplay).setOnClickListener { dialog?.dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


    companion object{
        @JvmStatic
        fun newInstance(param1: String) =
            ResultDialogFragment().apply {
                nama = param1
            }
    }

}
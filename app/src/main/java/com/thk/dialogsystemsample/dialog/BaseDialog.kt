package com.thk.dialogsystemsample.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.thk.dialogsystemsample.databinding.DialogBaseBinding

abstract class BaseDialog : DialogFragment() {
    private var _binding: DialogBaseBinding? = null
    private val binding
        get() = _binding!!

    val btnNegative
        get() = binding.btnNegative
    val btnPositive
        get() = binding.btnPositive

    abstract fun getContentView(): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contentView.addView(getContentView())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
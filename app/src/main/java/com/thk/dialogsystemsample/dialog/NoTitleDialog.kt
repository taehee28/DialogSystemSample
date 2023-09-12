package com.thk.dialogsystemsample.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thk.dialogsystemsample.databinding.DialogNoTitleBinding
import com.thk.dialogsystemsample.dialog.model.NoTitleDialogConfig

class NoTitleDialog : BaseDialog<NoTitleDialogConfig, DialogNoTitleBinding>() {
    override fun getContentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogNoTitleBinding {
        return DialogNoTitleBinding.inflate(inflater, container, false)
    }

    override fun initConfig(config: NoTitleDialogConfig) {
        binding.tvContent.text = config.content
    }
}
package com.thk.dialogsystemsample.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thk.dialogsystemsample.databinding.DialogTitleBinding
import com.thk.dialogsystemsample.dialog.model.TitleDialogConfig

class TitleDialog : BaseDialog<TitleDialogConfig, DialogTitleBinding>() {

    override fun getContentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogTitleBinding {
        return DialogTitleBinding.inflate(inflater, container, false)
    }

    override fun initConfig(config: TitleDialogConfig) {
        binding.apply {
            tvTitle.text = config.title
            tvContent.text = config.content
        }
    }
}
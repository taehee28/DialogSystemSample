package com.thk.dialogsystemsample.dialog.model
data class WorkingDialogConfig(
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : DialogConfig

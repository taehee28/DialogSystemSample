package com.thk.dialogsystemsample.dialog.model

import android.text.Spannable

/**
 * NoTitleDialog에 사용하는 데이터 클래스
 */
data class NoTitleDialogConfig(
    val content: Spannable,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : DialogConfig
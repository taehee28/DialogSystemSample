package com.thk.dialogsystemsample.dialog.model

import android.text.Spannable

/**
 * TitleDialog에 사용하는 데이터 클래스
 */
data class TitleDialogConfig(
    val title: Spannable,
    val content: Spannable,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : DialogConfig
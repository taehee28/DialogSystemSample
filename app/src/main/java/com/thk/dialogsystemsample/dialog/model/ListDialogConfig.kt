package com.thk.dialogsystemsample.dialog.model

import android.text.Spannable

/**
 * ListDialog에 사용하는 데이터 클래스
 */
data class ListDialogConfig<T>(
    val title: Spannable,
    val items: List<T>,
    val onReturnValue: ((selectedValue: T?) -> Unit)? = null,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = false
) : DialogConfig
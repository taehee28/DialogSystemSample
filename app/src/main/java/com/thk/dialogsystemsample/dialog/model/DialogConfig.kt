package com.thk.dialogsystemsample.dialog.model

import android.text.Spannable
import android.view.View

/**
 * 다이얼로그 버튼을 설정하기 위한 config 클래스.
 *
 * @property text 버튼을 표시하려면 텍스트를 반드시 설정해야 합니다.
 * @property onClick nullable
 */
data class DialogButtonConfig(
    val text: String,
    val onClick: ((view: View) -> Unit)? = null
)

interface BaseDialogConfig {
    val positiveButtonConfig: DialogButtonConfig?
    val negativeButtonConfig: DialogButtonConfig?
    val isCancelable: Boolean
}

data class TitleDialogConfig(
    val title: Spannable,
    val content: Spannable,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : BaseDialogConfig

data class NoTitleDialogConfig(
    val content: Spannable,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : BaseDialogConfig

data class ListDialogConfig<T>(
    val title: Spannable,
    val items: List<T>,
    val onDismissed: ((selectedValue: T?) -> Unit)? = null,
    override val positiveButtonConfig: DialogButtonConfig? = null,
    override val negativeButtonConfig: DialogButtonConfig? = null,
    override val isCancelable: Boolean = true
) : BaseDialogConfig
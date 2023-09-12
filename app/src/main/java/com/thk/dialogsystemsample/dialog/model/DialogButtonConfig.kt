package com.thk.dialogsystemsample.dialog.model

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
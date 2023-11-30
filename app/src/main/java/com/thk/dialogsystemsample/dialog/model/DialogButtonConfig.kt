package com.thk.dialogsystemsample.dialog.model

import androidx.fragment.app.DialogFragment

/**
 * 다이얼로그 버튼을 설정하기 위한 config 클래스.
 *
 * @property text 버튼을 표시하려면 텍스트를 반드시 설정해야 합니다.
 * @property onClick 람다 내부에서 다이얼로그 dismiss 호출 가능하도록 파라미터로 dialog를 전달합니다.
 */
data class DialogButtonConfig(
    val text: String,
    val onClick: ((dialog: DialogFragment) -> Unit)? = null
)
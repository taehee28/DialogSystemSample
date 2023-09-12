package com.thk.dialogsystemsample.dialog.model

/**
 * 기본 다이얼로그 설정 인터페이스
 */
interface DialogConfig {
    val positiveButtonConfig: DialogButtonConfig?
    val negativeButtonConfig: DialogButtonConfig?
    val isCancelable: Boolean
}
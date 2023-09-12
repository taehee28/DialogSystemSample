package com.thk.dialogsystemsample.dialog

// TODO: 더 효율적인 방법 찾기
object DialogFactory {
    val titleDialog
        get() = TitleDialog()

    val noTitleDialog
        get() = NoTitleDialog()

    val listDialog
        get() = ListDialog()
}
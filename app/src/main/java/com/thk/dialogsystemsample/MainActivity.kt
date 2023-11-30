package com.thk.dialogsystemsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.text.toSpannable
import com.thk.dialogsystemsample.databinding.ActivityMainBinding
import com.thk.dialogsystemsample.dialog.DialogFactory
import com.thk.dialogsystemsample.dialog.model.DialogButtonConfig
import com.thk.dialogsystemsample.dialog.model.ListDialogConfig
import com.thk.dialogsystemsample.dialog.model.NoTitleDialogConfig
import com.thk.dialogsystemsample.dialog.model.TitleDialogConfig
import com.thk.dialogsystemsample.dialog.model.WorkingDialogConfig

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnTitleDialog.setOnClickListener {
                DialogFactory
                    .titleDialog
                    .set(
                        config = TitleDialogConfig(
                            title = "안녕하세요.".toSpannable(),
                            content = "반갑습니다.".toSpannable(),
                            positiveButtonConfig = DialogButtonConfig(text = "확인")
                        )
                    )
                    .show(supportFragmentManager, null)
            }

            btnNoTitleDialog.setOnClickListener {
                DialogFactory
                    .noTitleDialog
                    .set(
                        config = NoTitleDialogConfig(
                            content = "내용입니다.".toSpannable(),
                            positiveButtonConfig = DialogButtonConfig("닫기"),
                            isCancelable = false
                        )
                    )
                    .show(supportFragmentManager, null)
            }

            btnListDialog.setOnClickListener {
                DialogFactory
                    .listDialog
                    .set(
                        config = ListDialogConfig(
                            title = "타이틀입니다.".toSpannable(),
                            items = listOf("첫번째", "두번째", "세번째", "네번째", "다섯번째"),
                            onReturnValue = {
                                Log.d("TAG", ">> selected value = $it")
                            },
                            positiveButtonConfig = DialogButtonConfig("확인"),
                            negativeButtonConfig = DialogButtonConfig("취소")
                        )
                    )
                    .show(supportFragmentManager, null)
            }

            btnWorkingDialog.setOnClickListener {
                DialogFactory
                    .workingDialog
                    .set(
                        config = WorkingDialogConfig(
                            positiveButtonConfig = DialogButtonConfig("확인"),
                            negativeButtonConfig = DialogButtonConfig("취소") {
                                it.dismiss()
                            }
                        )
                    )
                    .show(supportFragmentManager, null)
            }
        }
    }
}
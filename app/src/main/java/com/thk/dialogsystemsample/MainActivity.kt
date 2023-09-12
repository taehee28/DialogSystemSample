package com.thk.dialogsystemsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.text.toSpannable
import com.thk.dialogsystemsample.databinding.ActivityMainBinding
import com.thk.dialogsystemsample.dialog.ListDialog
import com.thk.dialogsystemsample.dialog.NoTitleDialog
import com.thk.dialogsystemsample.dialog.TitleDialog
import com.thk.dialogsystemsample.dialog.model.DialogButtonConfig
import com.thk.dialogsystemsample.dialog.model.ListDialogConfig
import com.thk.dialogsystemsample.dialog.model.NoTitleDialogConfig
import com.thk.dialogsystemsample.dialog.model.TitleDialogConfig

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnTitleDialog.setOnClickListener {
                TitleDialog()
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
                NoTitleDialog()
                    .set(
                        config = NoTitleDialogConfig(
                            content = "내용입니다.".toSpannable(),
                            positiveButtonConfig = DialogButtonConfig("닫기"),
                            isCancelable = false
                        )
                    )
                    .show(supportFragmentManager, null)
            }

            btnViewDialog1.setOnClickListener {
                ListDialog()
                    .set(
                        config = ListDialogConfig(
                            title = "타이틀입니다.".toSpannable(),
                            items = listOf("첫번째", "두번째", "세번째", "네번째", "다섯번째"),
                            onDismissed = {
                                Log.d("TAG", ">> selected value = $it")
                            },
                            positiveButtonConfig = DialogButtonConfig("확인")
                        )
                    )
                    .show(supportFragmentManager, null)
            }
        }
    }
}
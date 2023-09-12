package com.thk.dialogsystemsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.toSpannable
import com.thk.dialogsystemsample.databinding.ActivityMainBinding
import com.thk.dialogsystemsample.dialog.TitleDialog
import com.thk.dialogsystemsample.dialog.model.DialogButtonConfig
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
        }
    }
}
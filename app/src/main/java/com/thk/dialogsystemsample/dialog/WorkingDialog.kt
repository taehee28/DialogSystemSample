package com.thk.dialogsystemsample.dialog

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.thk.dialogsystemsample.databinding.DialogWorkingBinding
import com.thk.dialogsystemsample.dialog.model.DialogButtonConfig
import com.thk.dialogsystemsample.dialog.model.WorkingDialogConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * 시간이 걸리는 작업을 하는 ViewModel을 가지는 다이얼로그.
 */
class WorkingDialog : BaseDialog<WorkingDialogConfig, DialogWorkingBinding>() {

    private val viewModel: WorkingViewModel by viewModels()

    override fun getContentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogWorkingBinding {
        return DialogWorkingBinding.inflate(inflater, container, false)
    }

    override fun initConfig(config: WorkingDialogConfig) {
        // 버튼에 기능 끼워넣기
        val newConfig = WorkingDialogConfig(
            positiveButtonConfig = config.positiveButtonConfig?.let {
                DialogButtonConfig(
                    text = it.text
                ) { view ->
                    Log.d("TAG", ">> 확인버튼 기능 끼워넣기")

                    doWork()

                    it.onClick?.invoke(view)
                }
            },
            negativeButtonConfig = config.negativeButtonConfig,
            isCancelable = config.isCancelable
        )

        set(newConfig)
    }

    /**
     * 시작할 때 로딩 인디케이터 설정하고, 작업이 끝나면 인디케이터 숨기기
     */
    private fun doWork() = lifecycleScope.launch {
        isLoading = true
        isCancelable = false    // 작업이 시작되면 다이얼로그 끄기 불가능하도록
        viewModel.work().collectLatest {
            isLoading = false
            isCancelable = true
        }
    }
}

class WorkingViewModel : ViewModel() {
    fun work() = flow<Boolean> {
        delay(2000)
        emit(true)
    }
}
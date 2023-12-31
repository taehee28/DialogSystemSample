package com.thk.dialogsystemsample.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.thk.dialogsystemsample.databinding.DialogBaseBinding
import com.thk.dialogsystemsample.dialog.model.DialogConfig
import com.thk.dialogsystemsample.dialog.model.DialogButtonConfig
import com.thk.dialogsystemsample.util.getDeviceWidth
import kotlin.properties.Delegates

abstract class BaseDialog<DC : DialogConfig, VB : ViewBinding> : DialogFragment() {
    // 베이스 다이얼로그 레이아웃의 view binding
    private var _baseBinding: DialogBaseBinding? = null
    private val baseBinding
        get() = _baseBinding!!

    // 다이얼로그 contentView에 들어가는 레이아웃의 view binding
    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    // 다이얼로그 설정 값을 저장하는 변수
    private var dialogConfig: DC? = null

    // positive 버튼이 눌렸는지 여부
    protected var isPositiveClicked: Boolean = false

    // 로딩 인디케이터 표시 플래그
    protected var isLoading: Boolean by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            // FIXME: 버튼을 설정하지 않으면 버튼 visibility가 Gone인데
            //        인디케이터 플래그 때문에 안보여야 하는 버튼의 visibility가 설정됨.
            //        깔끔한 방법 찾기
            if (baseBinding.btnPositive.visibility != View.GONE) {
                baseBinding.btnPositive.visibility = View.INVISIBLE
            }
            if (baseBinding.btnNegative.visibility != View.GONE) {
                baseBinding.btnNegative.visibility = View.INVISIBLE
            }
            baseBinding.indicatorLoading.visibility = View.VISIBLE
        } else {
            if (baseBinding.btnPositive.visibility != View.GONE) {
                baseBinding.btnPositive.visibility = View.VISIBLE
            }
            if (baseBinding.btnNegative.visibility != View.GONE) {
                baseBinding.btnNegative.visibility = View.VISIBLE
            }
            baseBinding.indicatorLoading.visibility = View.GONE
        }
    }

    /**
     * [BaseDialog]의 contentView에 얹어질 레이아웃의 ViewBinding을 제공하는 메서드입니다.
     */
    protected abstract fun getContentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    /**
     * content 레이아웃 내부에서 view들을 설정할 수 있게 config를 제공합니다.
     * 버튼들에 대한 설정은 [BaseDialog] 내부에서 진행하므로 구현하지 않아도 됩니다.
     */
    protected abstract fun initConfig(config: DC)

    /**
     * 다이얼로그를 사용하는 곳에서 다이얼로그 종류에 맞게 설정할 수 있습니다.
     *
     * @param config [DialogConfig]를 구현하는 데이터 클래스
     * @return 바로 [show]를 호출할 수 있도록 다이얼로그를 반환합니다.
     */
    fun set(config: DC?): BaseDialog<DC, VB> {
        this.dialogConfig = config
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _baseBinding = DialogBaseBinding.inflate(inflater, container, false)
        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = getContentBinding(
            layoutInflater,
            baseBinding.contentView
        )

        dialogConfig?.also { config ->
            initConfig(config)
            isCancelable = config.isCancelable
        }

        baseBinding.contentView.addView(binding.root)


        // 호출하는 곳에서 onClick을 설정하지 않고 null로 줬을때만 dismiss 실행
        baseBinding.btnPositive.apply {
            setButtonConfig(button = this, config = dialogConfig?.positiveButtonConfig)
            setOnClickListener {
                isPositiveClicked = true
                dialogConfig?.positiveButtonConfig?.onClick?.invoke(this@BaseDialog) ?: dismiss()
            }
        }
        baseBinding.btnNegative.apply {
            setButtonConfig(button = this, config = dialogConfig?.negativeButtonConfig)
            setOnClickListener {
                dialogConfig?.negativeButtonConfig?.onClick?.invoke(this@BaseDialog) ?: dismiss()
            }
        }
    }

    /**
     * 다이얼로그 버튼을 설정하는 메서드.
     *
     * @param button
     * @param config null이면 버튼을 표시하지 않습니다.
     */
    private fun setButtonConfig(button: Button, config: DialogButtonConfig?) {
        button.apply {
            visibility = if (config == null) {
                View.GONE
            } else {
                text = config.text
                View.VISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        /*
        // 가로 화면 꽉차게 만들기
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        */

        // 가로 너비의 90퍼센트만 차지하게 만들기
        dialog?.window?.attributes = dialog?.window?.attributes?.apply {
            val deviceWidth = getDeviceWidth(requireContext())
            width = (deviceWidth * 0.9).toInt()
        }
    }

    override fun onDestroyView() {
        _baseBinding = null
        _binding = null
        dialogConfig = null
        super.onDestroyView()
    }
}
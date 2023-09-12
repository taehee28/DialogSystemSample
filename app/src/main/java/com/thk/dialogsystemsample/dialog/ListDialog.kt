package com.thk.dialogsystemsample.dialog

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thk.dialogsystemsample.databinding.DialogListBinding
import com.thk.dialogsystemsample.databinding.ItemDialogListBinding
import com.thk.dialogsystemsample.dialog.model.ListDialogConfig

class ListDialog : BaseDialog<ListDialogConfig<String>, DialogListBinding>() {
    // 다이얼로그가 사라질 때 선택한 값 반환하도록 함
    private var selectedValue: String? = null
    private var onReturnValue: ((String?) -> Unit)? = null

    override fun getContentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogListBinding {
        return DialogListBinding.inflate(inflater, container, false)
    }

    override fun initConfig(config: ListDialogConfig<String>) {
        onReturnValue = config.onReturnValue

        binding.tvTitle.text = config.title

        val adapter = DialogListAdapter { selectedValue = it }
        binding.rvList.adapter = adapter
        adapter.submitList(config.items)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (isPositiveClicked) {
            onReturnValue?.invoke(selectedValue)
        }
        onReturnValue = null
        selectedValue = null
        super.onDismiss(dialog)
    }
}


class DialogListAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<String, DialogListAdapter.ItemViewHolder>(ListDiffUtil()) {

    inner class ItemViewHolder(
        private val binding: ItemDialogListBinding
    ) : ViewHolder(binding.root) {

        init {
            binding.tvContent.setOnClickListener {
                onItemClick(binding.tvContent.text.toString())
            }
        }

        fun bind(value: String) {
            binding.tvContent.text = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemDialogListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ListDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
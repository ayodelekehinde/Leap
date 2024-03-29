package com.cherrio.leap.features.allnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cherrio.leap.databinding.LoadingFooterBinding
import com.cherrio.leap.utils.hide
import com.cherrio.leap.utils.show

class FooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        return FooterViewHolder(
            LoadingFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )

    }


    inner class FooterViewHolder(
        private val binding: LoadingFooterBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            print("Adapter $loadState")
            binding.footerLoading.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }
    }


}
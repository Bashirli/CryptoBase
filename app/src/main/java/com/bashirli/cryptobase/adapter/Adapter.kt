package com.bashirli.cryptobase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.databinding.RecyclercoinBinding

class Adapter(var arrayList: ArrayList<CryptoData>):RecyclerView.Adapter<Adapter.AdapterHolder>() {
    class AdapterHolder(var binding:RecyclercoinBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        var binding=RecyclercoinBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHolder(binding)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
       holder.binding.cryptoNameText.setText(arrayList.get(position).currency)
        holder.binding.cryptoPriceText.setText(arrayList.get(position).price)
    }

    fun updateList(newArrayList:ArrayList<CryptoData>){
        arrayList.clear()
        arrayList.addAll(newArrayList)
        notifyDataSetChanged()
    }

}
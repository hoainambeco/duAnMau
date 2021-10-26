package com.example.namnhph14161_asm_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.namnhph14161_asm_duanmau.Model.Top;
import com.example.namnhph14161_asm_duanmau.R;
import com.example.namnhph14161_asm_duanmau.fragment.frag_Top10;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    frag_Top10 fragment;
    private ArrayList<Top> lists;
    TextView tvSach, tvSL;

    public TopAdapter(@NonNull Context context, frag_Top10 fragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if (item != null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.tenSach);
            tvSL = v.findViewById(R.id.tvSL);
            tvSL.setText("Số lượng: "+item.soLuong);
        }
        return v;
    }
}

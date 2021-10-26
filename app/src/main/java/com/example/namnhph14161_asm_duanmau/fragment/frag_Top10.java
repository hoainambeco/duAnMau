package com.example.namnhph14161_asm_duanmau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.namnhph14161_asm_duanmau.Adapter.TopAdapter;
import com.example.namnhph14161_asm_duanmau.DAO.DAOthongke;
import com.example.namnhph14161_asm_duanmau.Model.Top;
import com.example.namnhph14161_asm_duanmau.R;

import java.util.ArrayList;

public class frag_Top10 extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frag_top10,container,false);
        lv= view.findViewById(R.id.lvTop);
        DAOthongke daOthongke = new DAOthongke(getActivity());
        list =(ArrayList<Top>) daOthongke.gettop();
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return view;
    }
}
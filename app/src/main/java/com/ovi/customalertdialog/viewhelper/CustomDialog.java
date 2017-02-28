package com.ovi.customalertdialog.viewhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovi.customalertdialog.callback.EnumDialogType;
import com.ovi.customalertdialog.R;
import com.ovi.customalertdialog.adapter.DialogAdapter;
import com.ovi.customalertdialog.callback.IClicked;
import com.ovi.customalertdialog.model.DialogModel;

import java.util.List;

/**
 * Created by BLACK HAT on 28-Feb-17.
 */

public class CustomDialog extends DialogFragment {
    private View mainView;
    private DialogAdapter adapter;
    private List<DialogModel> listItem;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private IClicked listener;
    private EnumDialogType enumDialogType;


    public void setData(Context context, List<DialogModel> listItem, IClicked listener, EnumDialogType enumDialogType){
        this.context = context;
        this.listItem = listItem;
        this.listener = listener;
        this.enumDialogType = enumDialogType;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.layout_custom_dialog_radio_button,container,false);

        initView(mainView);

        return mainView;
    }

    private void initView(View view){
        adapter = new DialogAdapter(getActivity(), listItem, listener, enumDialogType);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

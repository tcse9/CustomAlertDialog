package com.ovi.customalertdialog.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovi.customalertdialog.callback.EnumDialogType;
import com.ovi.customalertdialog.R;
import com.ovi.customalertdialog.callback.IClicked;
import com.ovi.customalertdialog.model.Batch;
import com.ovi.customalertdialog.model.DialogModel;

import java.util.List;

/**
 * Created by BLACK HAT on 28-Feb-17.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder>{

    private Context context;
    private List<DialogModel> listItem;
    private IClicked listener;
    private EnumDialogType enumDialogType;


    public DialogAdapter(Context context, List<DialogModel> listItem, IClicked listener, EnumDialogType enumDialogType) {
        this.context = context;
        this.listItem = listItem;
        this.listener = listener;
        this.enumDialogType = enumDialogType;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        if(enumDialogType == EnumDialogType.BATCH){
            itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.row_custom_dialog, parent, false);
        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if(enumDialogType == EnumDialogType.BATCH){
            final Batch item = (Batch) listItem.get(position);
            holder.btnRadio.setText(item.getName());
            holder.btnRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClicked(item.getId());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatRadioButton btnRadio;


        public ViewHolder(View itemView) {
            super(itemView);
            btnRadio = (AppCompatRadioButton)itemView.findViewById(R.id.btnRadio);
        }
    }
}

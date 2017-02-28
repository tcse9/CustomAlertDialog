package com.ovi.customalertdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ovi.customalertdialog.callback.EnumDialogType;
import com.ovi.customalertdialog.callback.IClicked;
import com.ovi.customalertdialog.model.DialogModel;
import com.ovi.customalertdialog.model.ModelBase;
import com.ovi.customalertdialog.retrofit.ApiClient;
import com.ovi.customalertdialog.retrofit.NetworkCallInterface;
import com.ovi.customalertdialog.viewhelper.CustomDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private String selectedBatchId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginDummy();

    }


    private void showDialog(){
        Retrofit retrofit = ApiClient.getInstance(this);
        NetworkCallInterface networkCallInterface = retrofit.create(NetworkCallInterface.class);
        networkCallInterface.getBatch("bda9fb37923069069b53f4b95d012b350faf27b7c339a90f7d8d24c121b191c0").enqueue(new Callback<ModelBase>() {
            @Override
            public void onResponse(Call<ModelBase> call, Response<ModelBase> response) {
                if(response.body() != null && response.isSuccessful()){
                    List<DialogModel> batchList = (List<DialogModel>)(List<?>) response.body().getData().getBatches();
                    Log.e("BATCH_ID", "is: "+batchList.size());

                    final CustomDialog customDialog = new CustomDialog();
                    customDialog.setData(MainActivity.this, batchList, new IClicked() {
                        @Override
                        public void onClicked(String id) {
                            selectedBatchId = id;
                            customDialog.dismiss();
                            Log.e("BATCH_ID", "is: "+selectedBatchId);
                        }
                    }, EnumDialogType.BATCH);
                    customDialog.show(getSupportFragmentManager(), "dialog");
                }
            }

            @Override
            public void onFailure(Call<ModelBase> call, Throwable t) {

            }
        });
    }

    private void loginDummy(){
        Retrofit retrofit = ApiClient.getInstance(this);
        NetworkCallInterface networkCallInterface = retrofit.create(NetworkCallInterface.class);
        networkCallInterface.auth("chs-t003", "123456").enqueue(new Callback<ModelBase>() {
            @Override
            public void onResponse(Call<ModelBase> call, Response<ModelBase> response) {
                if(response.body() != null && response.isSuccessful()){
                    showDialog();
                }
            }

            @Override
            public void onFailure(Call<ModelBase> call, Throwable t) {

            }
        });
    }

}

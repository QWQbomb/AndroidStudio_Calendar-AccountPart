package com.example.calendarview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calendarview.R;

import java.util.List;

public class AFragment extends Fragment {

    private TextView mTvTitle;
    private Activity mActivity;
    private ListView lv_account;
    ArrayAdapter accountArrayAdapter;
    private String tmp_date;
    DataBaseHelper dataBaseHelper;

    public static AFragment newInstance(String title){
        AFragment fragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);//当fragment被重新构建时，也会重新设为此处的参数
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;/*返回fragment的view*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        lv_account = view.findViewById(R.id.accountList);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        dataBaseHelper = new DataBaseHelper(getActivity());
        tmp_date = ((MainActivity)getActivity()).getDate();

        ShowAccountsOnListView(dataBaseHelper);

        if(getArguments() != null){
            mTvTitle.setText(getArguments().getString("title"));/*把传过来的值设置进去*/
        }
        /*Toast.makeText(getActivity(), allAccount.toString(), Toast.LENGTH_SHORT).show();*/

        //sth. went wrong here. Time:2020/9/10 1:04
        lv_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AccountModel clickedAccount = (AccountModel) parent.getItemAtPosition(position);
                //
                dataBaseHelper.deleteOne(clickedAccount, tmp_date);
                dataBaseHelper.close();
                ShowAccountsOnListView(dataBaseHelper);
                Toast.makeText(getActivity(), "You deleted an account!", Toast.LENGTH_SHORT).show();
                /*Toast.makeText(getActivity(), tmp_date, Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void ShowAccountsOnListView(DataBaseHelper dataBaseHelper2) {
        accountArrayAdapter = new ArrayAdapter<AccountModel>(getActivity(),
                android.R.layout.simple_list_item_1, dataBaseHelper.getAll());
        lv_account.setAdapter(accountArrayAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        /*mActivity = (Activity)context;*/
        if(getActivity() != null){
            // TODO: 2020/9/9
        }
        else{

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }

}
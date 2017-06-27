package com.sxj.carloan.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxj.carloan.R;
import com.sxj.carloan.bean.Loan;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.net.Api;
import com.sxj.carloan.net.ApiServiceModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ItemFragment extends Fragment {

    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private  ItemRecyclerViewAdapter itemRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_list, container, false);
        View view = root.findViewById(R.id.list);
        View add = root.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("state",1);
                intent.setClass(getActivity(),InfomationActivity.class);
                startActivity(intent);
            }
        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(null,this);
            recyclerView.setAdapter(itemRecyclerViewAdapter);
        }
        return root;
    }

    public void fromServer(){
        new ApiServiceModel().PageWork(" * from t_case ", "10","1").subscribe(new Subscriber<ServerBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ServerBean serverBean) {

            }
        });
    }


    public List<Loan> getLoanList(ServerBean serverBean){
        List<Loan>  list = new ArrayList<Loan>();
        if(serverBean.getRows() != null){
            for(ServerBean.RowsBean rowsBean :serverBean.getRows()){
                Loan loan = new Loan();
                rowsBean.getAdvance_payment();
                list.add(loan);
            }
        }
        return list;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void gotoDetails(Loan loan){
        Intent intent = new Intent(getActivity(),InfomationActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("data",loan);
        intent.putExtras(extras);
        getActivity().startActivity(intent);
    }
}

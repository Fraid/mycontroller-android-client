package com.ht.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ht.home.R;
import com.ht.home.bl.GatewayManager;
import com.ht.home.bl.adapter.GatewayListAdapter;
import com.ht.home.bo.Gateway;
import com.ht.home.util.GenericCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GatewayListFragment extends Fragment implements GenericCallback {

    @BindView(R.id.rv_gateway)
    RecyclerView mRecyclerView;

    public GatewayListFragment() {
        // Required empty public constructor
    }

    public static GatewayListFragment newInstance() {
        GatewayListFragment fragment = new GatewayListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GatewayManager.getInstance().getAllGateways(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gateway_list, container, false);

        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager storeLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(storeLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onSuccess(Object response) {
        List<Gateway> gatewayList = (List<Gateway>) response;

        GatewayListAdapter adapter = new GatewayListAdapter(getContext(), gatewayList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {

    }
}

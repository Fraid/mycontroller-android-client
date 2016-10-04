package org.mycontroller.standalone.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mycontroller.standalone.android.bl.GatewayManager;
import org.mycontroller.standalone.android.bl.adapter.GatewayListAdapter;
import org.mycontroller.standalone.android.bo.Gateway;
import org.mycontroller.standalone.android.util.GenericCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GatewayListFragment extends Fragment implements GenericCallback {

    @BindView(org.mycontroller.standalone.android.R.id.rv_gateway)
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
        View view =  inflater.inflate(org.mycontroller.standalone.android.R.layout.fragment_gateway_list, container, false);

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

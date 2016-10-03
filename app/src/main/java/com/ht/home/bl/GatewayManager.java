package com.ht.home.bl;

import com.ht.home.bl.api.ApiManager;
import com.ht.home.bl.api.RetrofitCallback;
import com.ht.home.bl.api.RetrofitResponse;
import com.ht.home.bo.Gateway;
import com.ht.home.bo.response.GatewayResponse;
import com.ht.home.util.GenericCallback;
import com.ht.home.util.LOG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tibi on 28/09/16.
 */
public class GatewayManager {

    private static GatewayManager instance;

    public static GatewayManager getInstance() {
        if (instance == null)
            instance = new GatewayManager();

        return instance;
    }

    public void getAllGateways(final GenericCallback<List<Gateway>> callback) {

        ApiManager.getInstance().API.getAllGateways().enqueue(new RetrofitCallback<>(new RetrofitResponse<GatewayResponse>() {
            @Override
            public void onSuccess(GatewayResponse response) {
                List<Gateway> gwList = new ArrayList<Gateway>();
                for (Gateway gw:response.data) {
                    gwList.add(gw);
                }
                callback.onSuccess(gwList);
            }

            @Override
            public void onFailure(String error) {
                LOG.debug(error);
                callback.onError(error);
            }
        }));

    }
}

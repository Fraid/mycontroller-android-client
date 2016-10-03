package com.ht.home.bl;

import com.ht.home.bl.api.ApiManager;
import com.ht.home.bl.api.RetrofitCallback;
import com.ht.home.bl.api.RetrofitResponse;
import com.ht.home.bo.Node;
import com.ht.home.bo.response.NodeResponse;
import com.ht.home.util.GenericCallback;
import com.ht.home.util.LOG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tibi on 28/09/16.
 */
public class NodeManager {

    private static NodeManager instance;

    public static NodeManager getInstance() {
        if (instance == null)
            instance = new NodeManager();

        return instance;
    }

    public void getAllNodes(final GenericCallback<List<Node>> callback) {

        ApiManager.getInstance().API.getAllNodes().enqueue(new RetrofitCallback<>(new RetrofitResponse<NodeResponse>() {
            @Override
            public void onSuccess(NodeResponse response) {
                List<Node> nodeList = new ArrayList<Node>();
                for (Node node:response.data) {
                    nodeList.add(node);
                }
                callback.onSuccess(nodeList);
            }

            @Override
            public void onFailure(String error) {
                LOG.debug(error);
                callback.onError(error);
            }
        }));

    }
}

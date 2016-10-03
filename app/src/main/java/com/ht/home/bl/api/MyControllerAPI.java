package com.ht.home.bl.api;

import com.ht.home.bo.Login;
import com.ht.home.bo.response.GatewayResponse;
import com.ht.home.bo.response.NodeResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyControllerAPI {

    //================================================================================
    // Gateway Services
    //================================================================================

    @GET("/mc/rest/gateways")
    Call<GatewayResponse> getAllGateways();

    //================================================================================
    // Node Services
    //================================================================================

    @GET("/mc/rest/nodes")
    Call<NodeResponse> getAllNodes();

    //================================================================================
    // Authentication Service
    //================================================================================

    @POST("mc/rest/authentication/login")
    Call<Login> login(@Body RequestBody request);

}

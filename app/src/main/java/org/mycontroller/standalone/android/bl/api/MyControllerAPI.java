package org.mycontroller.standalone.android.bl.api;

import org.mycontroller.standalone.android.bo.Login;
import org.mycontroller.standalone.android.bo.response.GatewayResponse;
import org.mycontroller.standalone.android.bo.response.NodeResponse;

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

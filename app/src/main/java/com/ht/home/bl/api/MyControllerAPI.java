package com.ht.home.bl.api;

import com.ht.home.bo.Login;
import com.ht.home.bo.request.LoginRequest;
import com.ht.home.bo.response.GatewayResponse;
import com.ht.home.bo.response.NodeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

//    @POST("customers/login")
//    Call<BaseResponse<Login>> login(@Query("email") String email,
//                                    @Query("password") String pwd,
//                                    @Query("push_token") String push_token,
//                                    @Query("push_os") String push_os);
//
//    @POST("customers/register")
//    Call<BaseResponse<Login>> register(@Query("customer[name]") String name,
//                                       @Query("customer[email]") String email,
//                                       @Query("customer[password]") String pwd,
//                                       @Query("customer[push_token]") String push_token,
//                                       @Query("customer[push_os]") String push_os);
//
//    @DELETE("customers/logout")
//    Call<BaseResponse<EmptyResponse>> logout();
//
//    @GET("customers/search")
//    Call<BaseListResponse<Customer>> searchCustomer(@Query("query") String query);
//
//    @GET("customers/{id}")
//    Call<BaseResponse<Profile>> getUserProfile(@Path("id") int id);
//
//    @POST("customers/{id}/followers")
//    Call<Void> follow(@Path("id") int id);
//
//    @DELETE("following/{id}")
//    Call<Void> unFollow(@Path("id") int id);
//
//    @GET("customers/{id}/followers")
//    Call<BaseListResponse<Follow>> getCustomerFollowers(@Path("id") int id);
//
//    @GET("customers/{id}/followings")
//    Call<BaseListResponse<Follow>> getCustomerFollowings(@Path("id") int id);
//
//    @POST("customers/forgot")
//    Call<Void> sendForgotPwdMail(@Query("email") String email);
//
//    //================================================================================
//    // Inbox
//    //================================================================================
//
//    @GET("inbox_notifications")
//    Call<BaseListResponse<Inbox>> inbox();
//
//    //================================================================================
//    // Omniauth
//    //================================================================================
//
//    @POST("omniauth/facebook")
//    Call<BaseResponse<SocialLogin>> loginWithFacebook(@Query("facebook[access_token]") String accesToken,
//                                                      @Query("push_token") String push_token,
//                                                      @Query("push_os") String push_os);
//
//    @POST("omniauth/google")
//    Call<BaseResponse<SocialLogin>> loginWithGoogle(@Query("google[code]") String code,
//                                                    @Query("push_token") String push_token,
//                                                    @Query("push_os") String push_os);
//
//    //================================================================================
//    // Locations
//    //================================================================================
//
//    @GET("locations/nearby")
//    Call<BaseListResponse<Locations>> getNearbyLocations(@Query("lat") float latitude,
//                                                         @Query("lng") float longitude,
//                                                         @Query("distance") double distance,
//                                                         @Query("query") String query,
//                                                         @Query("category") String category,
//                                                         @Query("open_now") Integer openNow,
//                                                         @Query("order") String order);
//
//    @GET("locations/{id}")
//    Call<BaseResponse<StoreDetail>> getLocation(@Path("id") String id);
//
//    @GET("locations/last_visited")
//    Call<BaseResponse<StoreDetail>> getLastVisitedLocation();
//
//    //================================================================================
//    // Profile
//    //================================================================================
//
//    @GET("profile")
//    Call<BaseResponse<Profile>> getProfile();
//
////    @Multipart
////    @PUT("profile")
////    Call<BaseResponse<Profile>> updateProfile(@Query("profile[name]") String name,
////                                              @Query("profile[bio]") String bio,
////                                              @Query("profile[privacy]") String privacy),
////                                              @Part("profile[picture]") RequestBody file);
//
//    @Multipart
//    @POST("profile")
//    Call<BaseResponse<Profile>> updateProfile(@Part("profile[name]") RequestBody name,
//                                              @Part("profile[email]") RequestBody email,
//                                              @Part("profile[bio]") RequestBody bio,
//                                              @Part("profile[privacy]") RequestBody privacy,
//                                              @Part("profile[push_token]") RequestBody push_token,
//                                              @Part("profile[push_os]") RequestBody push_os,
//                                              @Part MultipartBody.Part file);
//
//    @Multipart
//    @POST("profile")
//    Call<BaseResponse<Profile>> updateProfilePicture(@Part MultipartBody.Part file);
//
//    //================================================================================
//    // Locations - Customer Receipts
//    //================================================================================
//
//    @Multipart
//    @POST("locations/{id}/customer_receipts")
//    Call<BaseResponse<Status>> uploadReceipt(@Path("id") String lid,
//                                             @Part("location_id") RequestBody id,
//                                             @Part MultipartBody.Part file);
//
//    //================================================================================
//    // Awards
//    //================================================================================
//
//    @GET("award_receipts")
//    Call<BaseListResponse<Award>> getAwards();
//
//    @GET("award_receipts/{id}")
//    Call<BaseResponse<Award>> getAwardById(@Path("id") String id);
//
//    //================================================================================
//    // My Locations
//    //================================================================================
//
//    @GET("my_locations")
//    Call<BaseListResponse<Locations>> getMyLocations();
//
//    //================================================================================
//    // Locations - Rewards
//    //================================================================================
//
//    @GET("locations/{id}/rewards")
//    Call<RewardListResponse> getLocationRewards(@Path("id") String id);
//
//    //================================================================================
//    // Locations - Campaigns
//    //================================================================================
//
//    @GET("locations/{id}/campaigns")
//    Call<BaseListResponse<Offer>> getLocationOffers(@Path("id") String id,
//                                                    @Query("in_store") String in_store,
//                                                    @Query("ts") String ts);
//
//    //================================================================================
//    // Notification Settings
//    //================================================================================
//
//    @GET("notification_settings")
//    Call<BaseListResponse<NotificationSetting>> getNotificationSettings();
//
//    @PATCH("notification_settings/{code}")
//    Call<Void> updateNotificationSettings(@Path("code") String code,
//                                          @Body RequestBody enabled);
//
//    @PUT("notification_settings/{code}")
//    Call<Void> addNotificationSettings(@Path("code") String code,
//                                       @Body RequestBody enabled);
//
//    //================================================================================
//    // Locations - Cart items
//    //================================================================================
//
//    @GET("locations/{id}/cart_items")
//    Call<BaseListResponse<CartItem>> getCartItemsByLocation(@Path("id") String id,
//                                                            @Query("ts") String ts);
//
//    @DELETE("locations/{id}/cart_items/empty")
//    Call<Void> deleteCartItemByLocation(@Path("id") String id);
//
//    @POST("locations/{id}/cart_items/claim")
//    Call<BaseListResponse<CartItem>> addCartItemByLocation(@Path("id") String id);
//
//    //================================================================================
//    // Cart items
//    //================================================================================
//
//    @DELETE("cart_items/{id}")
//    Call<Void> removeCartItem(@Path("id") String id);
//
//    @POST("cart_items")
//    Call<Void> addCartItem(@Query("cart_item[campaign_id]") String id);
//
//    //================================================================================
//    // Favorite locations
//    //================================================================================
//
//    @GET("favorite_locations")
//    Call<BaseListResponse<Locations>> getFavouriteLocations();
//
//    @POST("favorite_locations")
//    Call<Void> addFavouriteLocation(@Query("id") String id);
//
//    @DELETE("favorite_locations/{id}")
//    Call<Void> removeFavouriteLocation(@Path("id") String id);
//
//    //================================================================================
//    // Customers - Favorite locations
//    //================================================================================
//
//    @GET("customers/{id}/favorite_locations")
//    Call<BaseListResponse<Locations>> getCustomerFavouriteLocations(@Path("id") int id);
//
//    //================================================================================
//    // Customers - Customer Activities
//    //================================================================================
//
//    @GET("customers/{id}/customer_activities")
//    Call<BaseListResponse<Activity>> getCustomerActivitiesByCustomer(@Path("id") int customer_id);
//
//    //================================================================================
//    // Customer Activities
//    //================================================================================
//
//    @GET("customer_activities")
//    Call<BaseListResponse<Activity>> getCustomerActivities(@Query("filter") String filter);
//
//    //================================================================================
//    // Followers
//    //================================================================================
//
//    @GET("followers")
//    Call<BaseListResponse<Follow>> getFollowers();
//
//    @GET("followers/pending")
//    Call<BaseListResponse<Follow>> getPendingFollowers();
//
//    @POST("followers/{id}/accept")
//    Call<Void> acceptRequest(@Path("id") int id);
//
//    @DELETE("followers/{id}")
//    Call<Void> declineRequest(@Path("id") int id);
//
//    //================================================================================
//    // Followings
//    //================================================================================
//
//    @GET("following")
//    Call<BaseListResponse<Follow>> getFollowings();
//
//    @GET("following/pending")
//    Call<BaseListResponse<Follow>> getPendingFollowings();
//
//    //================================================================================
//    // Rewards
//    //================================================================================
//
//    @POST("rewards/{id}/favorite")
//    Call<Void> addRewardToFavorite(@Path("id") String id);
//
//    @POST("rewards/{id}/unfavorite")
//    Call<Void> removeRewardFromFavorite(@Path("id") String id);
//
//    //================================================================================
//    // Punch cards - Redemptions
//    //================================================================================
//
//    @POST("punch_cards/{id}/redemptions")
//    Call<PunchCard> getPunchCardData(@Path("id") String id);
//
//    //================================================================================
//    // Punch cards - Share link
//    //================================================================================
//
//    @POST("punch_cards/{id}/share_links")
//    Call<BaseResponse<Share>> sharePunchCardData(@Path("id") String id);
//
//    //================================================================================
//    // Requested locations
//    //================================================================================
//
//    @POST("requested_locations")
//    Call<Void> notifyMe(@Query("requested_location[location_id]") String id);
//
//    //================================================================================
//    // Share reward links
//    //================================================================================
//
//    @POST("rewards/{id}/share_links")
//    Call<BaseResponse<Share>> shareRewardLink(@Path("id") String id);
//
//    //================================================================================
//    // Share offer links
//    //================================================================================
//
//    @GET("share_links/{id}")
//    Call<BaseResponse<Share>> shareOfferLink(@Path("id") String id);
//
//    //================================================================================
//    // Get business club cards
//    //================================================================================
//
//    @GET("businesses/{id}/club_cards")
//    Call<BaseListResponse<ClubCard>> getClubCardsByBusiness(@Path("id") String id);
//
//    //================================================================================
//    // Create business club cards
//    //================================================================================
//
//    @Multipart
//    @POST("businesses/{id}/club_cards")
//    Call<BaseResponse<ClubCard>> createClubCardsByBusiness(@Path("id") String id,
//                                                           @Part("club_card[name]") RequestBody name,
//                                                           @Part("club_card[card_id]") RequestBody card_id,
//                                                           @Part MultipartBody.Part frontCard,
//                                                           @Part MultipartBody.Part backCard);
//
//
//    //================================================================================
//    // Get all club cards for customer
//    //================================================================================
//
//    @GET("club_cards")
//    Call<BaseListResponse<ClubCard>> getAllClubCardOfCustomer();
//
//    //================================================================================
//    // Delete specified club card
//    //================================================================================
//
//    @DELETE("club_cards/{id}")
//    Call<Void> deleteClubCard(@Path("id") String id);
//
//    //================================================================================
//    // Get location from beacon UUID
//    //================================================================================
//
//    @GET("beacons/{uuid}/location")
//    Call<BaseResponse<BeaconBusiness>> getBusinessFromBeaconData(@Path("uuid") String pathUUID,
//                                                                 /*@Query("uuid") String uuid,*/
//                                                                 @Query("major") String major,
//                                                                 @Query("minor") String minor);
//
//    //================================================================================
//    // Checkins - check into store
//    //================================================================================
//
//    @POST("locations/{id}/checkins")
//    Call<CheckInResponse> checkIntoStore(@Path("id") String id);
//
//    //================================================================================
//    // Checkins - check out from store
//    //================================================================================
//
//    @DELETE("checkins/{id}")
//    Call<Void> checkOutFromStore(@Path("id") String id);
//
//    //================================================================================
//    // Punch Card - favorite / unfavorite
//    //================================================================================
//
//    @POST("rewards/{id}/favorite")
//    Call<Void> addPunchCardToFavorite(@Path("id") String id);
//
//    @POST("rewards/{id}/unfavorite")
//    Call<Void> removePunchCardFromFavorite(@Path("id") String id);
//
//    //================================================================================
//    // Reward redemption
//    //================================================================================
//
//    @POST("rewards/{reward_id}/redemptions")
//    Call<BaseResponse<Redemption>> redeemReward(@Path("reward_id") String id);
//
//    //================================================================================
//    // Claim campaign
//    //================================================================================
//
//    @POST("campaigns/{campaign_id}/claims")
//    Call<BaseResponse<OfferWithPos>> claimCampaign(@Path("campaign_id") String id);
//
//    //================================================================================
//    // Code redemption
//    //================================================================================
//
//    @POST("points_codes/{token}/redeem")
//    Call<Void> redeemPoint(@Path("token") String token);
//
//    //================================================================================
//    // Share enterance activity
//    //================================================================================
//
//    @POST("checkins/{id}/publish")
//    Call<Void> shareEnterance(@Path("id") String checkInId);
//
//    @GET("campaigns/{id}")
//    Call<BaseResponse<Offer>> getCampaign(@Path("id") int id);

}

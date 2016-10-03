package com.ht.home.bl;

import com.google.gson.Gson;
import com.ht.home.bc.SharedPreferencesConnector;

import org.eclipse.paho.client.mqttv3.internal.websocket.Base64;

public class PreferencesManager {

    private static final String PREF_LOGIN_HASH = "home.login.hash";
    private static final String PREF_HOST = "home.host";

    private Gson gson = new Gson();

    private static PreferencesManager mInstance;

    public static PreferencesManager getInstance() {
        if (mInstance == null)
            mInstance = new PreferencesManager();

        return mInstance;
    }

    private PreferencesManager() {
    }

    public boolean hasLoginHash() {
        return getLoginHash() != null;
    }

    public String getLoginHash() {
        return SharedPreferencesConnector.getInstance().getString(PREF_LOGIN_HASH, null);
    }

    public void saveLoginHash(String username, String password) {
        String stringToHash = username+":"+password;
        SharedPreferencesConnector.getInstance().saveString(PREF_LOGIN_HASH, Base64.encodeBytes(stringToHash.getBytes()));
    }

    public void removeLoginHash() {
        SharedPreferencesConnector.getInstance().removeValue(PREF_LOGIN_HASH);
    }

    public void saveHost(String host) {
        SharedPreferencesConnector.getInstance().saveString(PREF_HOST, host);
    }

    public String getHost() {
        return SharedPreferencesConnector.getInstance().getString(PREF_HOST, null);
    }
//    public LoginParams getLoginParams() {
//        String loginParamsJson = SharedPreferencesConnector.getInstance().getString(PREF_LOGIN_PARAMS, null);
//        if (loginParamsJson != null) {
//            return gson.fromJson(loginParamsJson, LoginParams.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void setLoginParams(LoginParams loginParams) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_LOGIN_PARAMS, gson.toJson(loginParams));
//    }
//
//    public void removeLoginParams() {
//        SharedPreferencesConnector.getInstance().removeValue(PREF_LOGIN_PARAMS);
//    }
//
//    public void saveFilterObject(FilterObject obj) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_FILTER_OBJECT, gson.toJson(obj));
//    }
//
//    public FilterObject getFilterObject() {
//        String filterJson = SharedPreferencesConnector.getInstance().getString(PREF_FILTER_OBJECT, null);
//        if (filterJson != null) {
//            return gson.fromJson(filterJson, FilterObject.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void saveLastBeaconDetection(BeaconDetection beaconDetection) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_BEACON_OBJECT, gson.toJson(beaconDetection));
//    }
//
//    public BeaconDetection getLastBeaconDetection() {
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_BEACON_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, BeaconDetection.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void removeLastDetection() {
//        SharedPreferencesConnector.getInstance().saveString(PREF_BEACON_OBJECT, null);
//    }
//
//
//    public void saveCurrentBusinessData(BeaconBusiness beaconBusiness) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_CURRENT_BUSINESS_OBJECT, gson.toJson(beaconBusiness));
//    }
//
//
//    public BeaconBusiness getCurrentBusinessData() {
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_CURRENT_BUSINESS_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, BeaconBusiness.class);
//        } else {
//            return null;
//        }
//    }
//
//
//    public void saveInStoreBusinessData(BeaconBusiness beaconBusiness) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_IN_STORE_BUSINESS_OBJECT, gson.toJson(beaconBusiness));
//    }
//
//    public BeaconBusiness getInStoreBusinessData() {
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_IN_STORE_BUSINESS_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, BeaconBusiness.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void setInStoreShouldBeVisible(boolean result) {
//        SharedPreferencesConnector.getInstance().saveBoolean(PREF_IN_STORE_VISIBLE, result);
//    }
//
//    public boolean isInStoreShouldBeVisible() {
//        return SharedPreferencesConnector.getInstance().getBoolean(PREF_IN_STORE_VISIBLE, false);
//    }
//
//    public void savePushToken(String token) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_PUSH_TOKEN, token);
//    }
//
//    public String getPushToken() {
//        return SharedPreferencesConnector.getInstance().getString(PREF_PUSH_TOKEN, "");
//    }
//
//    public void saveCurrentCheckIn(CheckInResponse checkInResponse) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_CURRENT_CHECK_IN_OBJECT, gson.toJson(checkInResponse));
//    }
//
//    public CheckInResponse getCurrentCheckIn() {
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_CURRENT_CHECK_IN_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, CheckInResponse.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void saveCheckOutTime(long value) {
//        SharedPreferencesConnector.getInstance().saveLong(PREF_CHECK_OUT_TIME, value);
//    }
//
//    public long getCheckOutTime() {
//        return SharedPreferencesConnector.getInstance().getLong(PREF_CHECK_OUT_TIME, 0);
//    }
//
//    public void saveCurrentRedeemObj(Redemption redemption) {
//        SharedPreferencesConnector.getInstance().saveString(PREF_CURRENT_REDEEM_OBJECT, gson.toJson(redemption));
//    }
//
//    public Redemption getCurrentRedeemObj() {
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_CURRENT_REDEEM_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, Redemption.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void setClaimNoitfy(boolean value) {
//        SharedPreferencesConnector.getInstance().saveBoolean(PREF_NOTIFY_ON_CLAIM, value);
//    }
//
//    public boolean needToNotifyOnClaim() {
//        return SharedPreferencesConnector.getInstance().getBoolean(PREF_NOTIFY_ON_CLAIM, true);
//    }
//
//    public void setFirstStart(boolean value){
//        SharedPreferencesConnector.getInstance().saveBoolean(PREF_IS_FIRST_START, value);
//    }
//
//    public boolean isFirstStart(){
//        return SharedPreferencesConnector.getInstance().getBoolean(PREF_IS_FIRST_START, true);
//    }
//
//
//
//    public void setDontForgetShouldShow(boolean value){
//        SharedPreferencesConnector.getInstance().saveBoolean(PREF_IS_DON_T_FORGET_SHOULD_SHOW, value);
//    }
//
//    public boolean isDontForgetShouldShow(){
//        return SharedPreferencesConnector.getInstance().getBoolean(PREF_IS_DON_T_FORGET_SHOULD_SHOW, false);
//    }
//
//    public void setLastCheckIn(CheckInResponse value){
//        SharedPreferencesConnector.getInstance().saveString(PREF_LAST_CHECK_IN_RESPONSE, gson.toJson(value));
//    }
//
//    public CheckInResponse getLastCheckIn(){
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_LAST_CHECK_IN_RESPONSE,null);
//        if (json != null) {
//            return gson.fromJson(json, CheckInResponse.class);
//        } else {
//            return null;
//        }
//    }
//
//    public void setCurrentStoreDetailObject(StoreDetail value){
//        SharedPreferencesConnector.getInstance().saveString(PREF_MORE_BUSINESS_INFO_OBJECT, gson.toJson(value));
//    }
//
//    public StoreDetail getCurrentStoreDetailObject(){
//        String json = SharedPreferencesConnector.getInstance().getString(PREF_MORE_BUSINESS_INFO_OBJECT, null);
//        if (json != null) {
//            return gson.fromJson(json, StoreDetail.class);
//        } else {
//            return null;
//        }
//    }
//
//
//    public void setCurrentHTTPCode(int statusCode){
//        SharedPreferencesConnector.getInstance().saveInt(PREF_CURRENT_HTTP_STATUS_CODE, statusCode);
//    }
//
//    public int getCurrentHTTPCode(){
//        return SharedPreferencesConnector.getInstance().getInt(PREF_CURRENT_HTTP_STATUS_CODE,0);
//    }

}

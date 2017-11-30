package com.paymentwall.java;

import java.util.ArrayList;
import java.util.HashMap;

public class WidgetBuilder {
    String userId;
    String widgetCode;
    ArrayList<Product> products = new ArrayList<Product>();
    HashMap<String, String> extraParams = new HashMap<String, String>();
    Config config;
    private UserProfile userProfile;

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public WidgetBuilder(String userId_, String widgetCode_) {
        this(Config.getInstance(), userId_, widgetCode_);
    }

    public WidgetBuilder(Config config_, String userId_, String widgetCode_) {
        config = config_;
        userId = userId_;
        widgetCode = widgetCode_;
    }

    public Widget build() {
        if(userProfile == null) {
            return new Widget(config, userId, widgetCode, products, extraParams);
        } else {
            HashMap<String, String> allParams = new HashMap<String, String>();
            allParams.putAll(extraParams);
            allParams.putAll(userProfile.toParameters());
            return new Widget(config, userId, widgetCode, products, allParams);
        }
    }

    public void setProducts(ArrayList<Product> products_) { products = products_; }

    public void setProduct(final Product products_) { products = new ArrayList<Product>(){{add(products_);}}; }

    public void setExtraParams(HashMap<String,String> extraParams_) { extraParams = extraParams_; }
}

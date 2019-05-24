package com.amisoft.apiregistry.constant;

import org.springframework.stereotype.Component;

@Component
public class ApiRegistryConstant {

    public final static String QUEUE_NAME = "apiregistry";
    public final static String ROUTING_KEY = QUEUE_NAME;
    public final static String EXCHANGE_NAME = "apiregistry_exchange";



}

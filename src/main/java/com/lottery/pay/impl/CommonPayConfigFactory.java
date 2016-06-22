package com.lottery.pay.impl;

import com.lottery.common.contains.pay.PayChannel;
import org.springframework.stereotype.Service;

/**
 * Created by fengqinyun on 15/5/29.
 */
@Service
public class CommonPayConfigFactory  extends AbstractPayConfigFactory {

    @Override
    protected void init() {
        payFactoryMap.put(PayChannel.zfbpay, this);
        payFactoryMap.put(PayChannel.yeebao, this);
        payFactoryMap.put(PayChannel.zfbwappay, this);
        payFactoryMap.put(PayChannel.zfbwebpay, this);
        payFactoryMap.put(PayChannel.zfbdraw, this);
        payFactoryMap.put(PayChannel.zfbbankdraw, this);
        payFactoryMap.put(PayChannel.elinkpay, this);
        payFactoryMap.put(PayChannel.elinkpcpay, this);
        payFactoryMap.put(PayChannel.elinkpcwypay, this);
        payFactoryMap.put(PayChannel.elinknewpay, this);
        payFactoryMap.put(PayChannel.elinkwappay, this);
        payFactoryMap.put(PayChannel.yeebaowap, this);
        payFactoryMap.put(PayChannel.xfwap, this);
    }
}

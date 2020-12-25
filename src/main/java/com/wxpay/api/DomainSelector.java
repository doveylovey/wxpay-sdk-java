package com.wxpay.api;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 域名选择器，主从域名选择器
 *
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
@Data
public class DomainSelector {
    /**
     * 上次切换到备用域名时间
     */
    private long lastSwitchAssistantDomainTime = 0;

    /**
     * 切换到备域名后多久尝试切换到主域名（默认十分钟）
     */
    private long switchMasterDomainMilliSec = 10 * 60 * 1000;
    /**
     * 上次上报错误时间
     */
    private long lastReportErrorTime = 0;

    /**
     * 当前域名上报数量
     */
    private AtomicLong reportErrorCount = new AtomicLong(0);

    /**
     * 上报有效期(毫秒)
     */
    private Long reportExpired = 3000L;

    /**
     * 切换域名阀值
     */
    private long switchCount = 4;

    /**
     * 主域名
     */
    private final static String MASTER_DOMAIN = "https://api.mch.weixin.qq.com";
    /**
     * 备用域名
     */
    private final static String ASSISTANT_DOMAIN = "https://api2.mch.weixin.qq.com";

    private String domain;

    private static DomainSelector singleton;

    private DomainSelector() {
    }

    public static DomainSelector getInstance() {
        if (singleton == null) {
            synchronized (DomainSelector.class) {
                if (singleton == null) {
                    singleton = new DomainSelector();
                    singleton.setDomain(MASTER_DOMAIN);
                }
            }
        }
        return singleton;
    }

    public String getDomain() {
        if (ASSISTANT_DOMAIN.equals(this.domain)) {
            if (System.currentTimeMillis() - lastSwitchAssistantDomainTime > switchMasterDomainMilliSec) {
                swith();
            }
        }
        return domain;
    }

    /**
     * 上报异常的域名
     *
     * @param domain
     */
    public void reportErrorDomain(String domain) {
        if (this.domain.equals(domain)) {
            long cur = System.currentTimeMillis();
            reportErrorCount.incrementAndGet();
            if (cur - lastReportErrorTime < reportExpired) {
                lastReportErrorTime = cur;
                if (reportErrorCount.get() >= this.switchCount) {
                    swith();
                }
            } else {
                reportErrorCount.set(0);
                lastReportErrorTime = cur;
            }
        }
    }


    private void swith() {
        if (MASTER_DOMAIN.equals(this.domain)) {
            switchAssistantDomain();
        } else if (ASSISTANT_DOMAIN.equals(this.domain)) {
            switchMasterDomain();
        }
        this.lastReportErrorTime = 0;
        reportErrorCount.set(0);
    }

    /**
     * 切换为主域名
     */
    public void switchMasterDomain() {
        domain = MASTER_DOMAIN;
        lastSwitchAssistantDomainTime = 0;
    }

    /**
     * 切换为备用域名
     */
    public void switchAssistantDomain() {
        domain = ASSISTANT_DOMAIN;
        lastSwitchAssistantDomainTime = System.currentTimeMillis();
    }
}

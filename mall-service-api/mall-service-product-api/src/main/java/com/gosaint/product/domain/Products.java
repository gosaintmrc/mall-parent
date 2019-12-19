package com.gosaint.product.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: gosaint
 * @Description: 商品组合对象
 * @Date Created in 21:37 2019/12/19
 * @Modified By:
 */
public class Products implements Serializable {

    private Spu spu;

    private List<Sku> skuList;

    public Products(final Spu spu, final List<Sku> skuList) {
        this.spu=spu;
        this.skuList=skuList;
    }

    public Products() {
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(final Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(final List<Sku> skuList) {
        this.skuList = skuList;
    }
}

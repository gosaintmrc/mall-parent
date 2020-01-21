package com.gosaint.service;

import java.util.Map;

/**
 * @Author: gosaint
 * @Description:
 * @Date Created in 20:53 2019/12/22
 * @Modified By:
 */
public interface SkuService {

    /**
     * 导入SKU数据到索引库
     */
    void imports();

    /**
     * 条件搜索
     * @param search
     * @return
     */
    Map<String,Object> search(Map<String,String> search);
}

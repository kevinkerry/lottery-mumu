package com.lottery.common.cache;

import com.lottery.common.exception.CacheNotFoundException;
import com.lottery.common.exception.CacheUpdateException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: fengqinyun
 */
public abstract class AbstractKeyValueCacheModel<K  extends Serializable, V  extends Serializable> implements IKeyValueCacheModel<K , V> {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected Lock lock = new ReentrantLock();

    /**
     * 设置缓存
     * @param key 缓存的key
     * @param value 缓存的value
     * @throws CacheUpdateException
     */
    abstract protected void setCache(K key, V value) throws CacheUpdateException;

    /**
     * 从缓存里获取值
     * @param key 缓存的key
     * @return 缓存的对象值，如果缓存中未找到，返回异常
     */
    abstract protected V getFromCache(K key) throws CacheNotFoundException;

    /**
     * 从实际数据来源处获取值
     * @param key 缓存的key
     * @return 实际的对象值，如果未找到返回null，如果查找过程中出错则抛出异常
     */
    abstract protected V getFromSource(K key) throws Exception;

    protected Map<K, V> mgetFromCache(List<K> keyList) {
        Map<K, V> cachedValueMap = new HashMap<K, V>();

        for (K key : keyList) {
            V value = null;
            try {
                value = this.getFromCache(key);
                // 如果找到了缓存，直接使用
                cachedValueMap.put(key, value);
            } catch (CacheNotFoundException e) {
                //logger.info("未找到缓存, key={}", key);
                //logger.info(e.getMessage(), e);
            }
        }

        return cachedValueMap;
    }

    protected Map<K, V> mgetFromSource(List<K> keyList) throws Exception {
        Map<K, V> nocachedValueMap = new HashMap<K, V>();

        for (K key : keyList) {
            try {
                V value = this.getFromSource(key);
                nocachedValueMap.put(key, value);
            } catch (Exception e) {
             logger.error("从数据源获取数据时出错,key={},message={}", key,e.getMessage());
               // logger.error(e.getMessage(), e);
            }
        }

        return nocachedValueMap;
    }

    @Override
    public Map<K, V> mget(List<K> keyList) throws CacheNotFoundException, CacheUpdateException {
        Map<K, V> valueMap = new HashMap<K, V>();

        // 先取出已缓存的结果
        Map<K, V> cachedValueMap = this.mgetFromCache(keyList);
        valueMap.putAll(cachedValueMap);

        // 比对未缓存结果，准备待查key列表
        List<K> nocachedKeyList = new ArrayList<K>();
        for (K key : keyList) {
            if (!cachedValueMap.containsKey(key)) {
                nocachedKeyList.add(key);
            }
        }

        if (nocachedKeyList.isEmpty()) {
            // 已全部从缓存中读取，直接返回
            return valueMap;
        }

        // 从数据源获取数据
        try {
            Map<K, V> nocachedValueMap = this.mgetFromSource(nocachedKeyList);
            valueMap.putAll(nocachedValueMap);
        } catch (Exception e) {
            logger.error("从数据源批量获取数据出错, keyList={}", StringUtils.join(keyList, ","));
           // logger.error(e.getMessage(), e);
        }
        return valueMap;
    }

    @Override
    public V get(K key) throws CacheNotFoundException, CacheUpdateException {
        V value = null;
        try {
            value = this.getFromCache(key);
            // 如果找到了缓存，直接使用并返回
            return value;
        } catch (CacheNotFoundException e) {
            logger.info("未找到缓存, key={}", key);
            //logger.info(e.getMessage(), e);
        }

        // 否则进行加锁并尝试重建缓存
        lock.lock();
        // 在加锁状态下重新获取值，确保并发情况下不会重复创建缓存
        try {
        	
            value = this.getFromCache(key);
            // 如果已经存在缓存，释放锁并返回结果
            lock.unlock();
            return value;
        } catch (CacheNotFoundException e) {
            logger.info("未找到缓存, key={}", key);
          //  logger.info(e.getMessage(), e);
        }
        // 实际获取值
        try {
            value = this.getFromSource(key);
            // 找到并设置缓存
            this.setCache(key, value);
            return value;
        } catch (CacheUpdateException e) {
            logger.info("从数据源查找到需要的缓存，但是在设置缓存时出错, key={}, value={}", key, value);
            //logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.info("从数据源查找需要换存的数据时出错,key={},message={}", key,e.getMessage());
          //  logger.error(e.getMessage(), e);
            throw new CacheNotFoundException("从数据源查找需要换存的数据时出错，" + e.getMessage());
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

}

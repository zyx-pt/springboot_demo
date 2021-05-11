package com.zyx.demo.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 分布式锁的使用
 * @ClassName com.zyx.demo.controller.DistributedLockController
 * @Author zhengyongxian
 * @Date 2021/1/5 21:26
 */
public class DistributedLockController {
    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    /**
     * @Description: Redisson实现扣减库存
     *
     * @Author: zhengyongxina
     * @Date: 2021/1/6 9:37
     * @param
     * @return: java.lang.String
     */
    public String deductStock3(){
        String lockKey = "lockKey";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(realStock));
                System.out.println("扣减成功，剩余库存" + realStock);
            }else{
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
        }
        return "end";
    }


    /**
     * @Description: Redis实现扣减库存
     *
     * @Author: zhengyongxina
     * @Date: 2021/1/6 9:37
     * @param
     * @return: java.lang.String
     */
    public String deductStock2(){
        String lockKey = "lockKey";
        String clientId = UUID.randomUUID().toString();
        try {
            // Boolean setLockResult = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "abcdefg");
            // stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);
            // jedis.setnx(lockKey, "abcdefg");
            Boolean setLockResult = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!setLockResult) {
                return "error";
            }
            // jedis.get("stock");
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                // jedis.set("stock", realStock);
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(realStock));
                System.out.println("扣减成功，剩余库存" + realStock);
            }else{
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            // stringRedisTemplate.delete(lockKey);
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "end";
    }

    /**
     * Zookeeper使用Curator实现分布式锁
     *
     * 1.请求进来，直接在/lock节点下创建一个临时顺序节点
     * 2.判断自己是不是lock节点下最小的节点
     *   a. 是最小，获得锁
     *   b. 不是，对前面的节点进行监听
     * 3.获得锁的请求，处理完释放锁，即delete节点，后继第一个节点将收到通知，重复第2步判断
     */

    public String deductStock1(Integer id)throws Exception{
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_" + id);
        try {
            interProcessMutex.acquire();
            // 数据库扣减库存
        }catch (Exception e){

        }finally {
            interProcessMutex.release();
        }
        return "end";
    }

}

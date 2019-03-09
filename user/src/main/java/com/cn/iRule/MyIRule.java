package com.cn.iRule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyIRule extends AbstractLoadBalancerRule{
    Random rand;

    //当前下标
    private int nowIndex=-1;
    //上次下标
    private int lastIndex=-1;
    //跳过下标
    private int skipIndex=-1;

    public MyIRule() {
        rand = new Random();
    }

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            /**
             * 跳过两次相同下标算法
             */
            int index = rand.nextInt(serverCount);
            System.out.println("当前下标"+index);
            if(skipIndex==index){
                System.out.println("需要跳过下标"+skipIndex);
                index = rand.nextInt(serverCount);
                System.out.println("跳过之后");
            }
            skipIndex=-1;
            nowIndex=index;
            if(nowIndex==lastIndex){

                skipIndex=nowIndex;
            }
            lastIndex=index;
            System.out.println("上次下标"+lastIndex);
            server = upList.get(index);

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}

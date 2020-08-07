package com.myproject.myRule;

/**
 * Created by 书一 on 2020/7/12.
 */
public class MyRandomRule {

    //自定义规则，每个服务访问5次，再访问下一个
//    public Server choose(ILoadBalancer lb, Object key) {
//        if(lb == null) {
//            return null;
//        } else {
//            Server server = null;
//
//            while(server == null) {
//                if(Thread.interrupted()) {
//                    return null;
//                }
//
//                List<Server> upList = lb.getReachableServers(); //获取活着的服务
//                List<Server> allList = lb.getAllServers();  //获取全部服务
//                int serverCount = allList.size();
//                if(serverCount == 0) {
//                    return null;
//                }
//
//                int index = this.rand.nextInt(serverCount); //生成区间随机数
//                server = (Server)upList.get(index);  //从活着的服务随机获取一个
//                if(server == null) {
//                    Thread.yield();
//                } else {
//                    if(server.isAlive()) {
//                        return server;
//                    }
//
//                    server = null;
//                    Thread.yield();
//                }
//            }
//
//            return server;
//        }
//    }
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//
//    }
//
//    @Override
//    public Server choose(Object o) {
//        return null;
//    }
}

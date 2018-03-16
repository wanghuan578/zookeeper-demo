package com.spirit.zookeeper_test;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;

public class TestGetData implements Watcher {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static ZooKeeper zooKeeper;
	private static Stat stat = new Stat();

	public String getData() throws IOException, InterruptedException, KeeperException { 
		
		zooKeeper = new ZooKeeper("192.168.207.128:2181,192.168.207.129:2181,192.168.207.131:2181", 5000, new TestGetData());
        
		countDownLatch.await();
        
		String path = "/tasks/888";
        //zooKeeper.create(path,"555".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 异步读取节点内容
        zooKeeper.getData(path,true,new MyDataCallback(),null);
        zooKeeper.getChildren(path, true, new DirRecursiveData(), null);
        
        //zooKeeper.setData(path,"123".getBytes(),-1);
        
		return "";
	}
		
	public void process(WatchedEvent event) {
		
		System.out.println("--------Watcher Notify process");
		
		// TODO Auto-generated method stub
		if (Event.KeeperState.SyncConnected == event.getState()) 
		{
            if(Event.EventType.None == event.getType() && null == event.getPath()){ // 连接时的监听事件
                countDownLatch.countDown();
            }
            else if (event.getType() == Event.EventType.NodeDataChanged)
            { // 子节点内容变更时的监听
                try {
                    System.out.println("wanghuan data change listen：data="
                            + new String(zooKeeper.getData(event.getPath(),true,stat)));
                    System.out.println("监听获得通知Stat：czxid=" + stat.getCzxid()
                            + ";mzxid=" + stat.getMzxid() + ";version="  + stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (event.getType() == Event.EventType.NodeChildrenChanged)
            {
            	System.out.println("wanghuan node children change");
            	
            	Stat stat = new Stat();
            	try {
            		List<String> children = zooKeeper.getChildren(event.getPath(), true, stat);
            		
            		System.out.println("children change Stat：czxid=" + stat.getCzxid() + ";mzxid=" + stat.getMzxid() + ";version="  + stat.getVersion());
            		
            		for (String node : children) {
            			
            			System.out.println("children name: " + node);
            		}
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
        }
	}
}

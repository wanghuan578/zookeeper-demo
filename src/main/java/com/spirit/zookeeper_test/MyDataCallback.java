package com.spirit.zookeeper_test;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

public class MyDataCallback implements AsyncCallback.DataCallback {

	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		// TODO Auto-generated method stub
		System.out.println("异步返回结果：rc=" + rc + ";path=" + path + ";data=" + new String(data));
        System.out.println("异步读取Stat：czxid=" + stat.getCzxid()
                + ";mzxid=" + stat.getMzxid() + ";version="  + stat.getVersion());
	}

}

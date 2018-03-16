package com.spirit.zookeeper_test;

import java.util.List;

import org.apache.zookeeper.AsyncCallback;

public class DirRecursiveData implements AsyncCallback.ChildrenCallback{

	public void processResult(int rc, String path, Object ctx, List<String> children) {
		// TODO Auto-generated method stub
		System.out.println("异步返回结果：rc=" + rc + ";path=" + path + ";children list =" + children);
	}

}

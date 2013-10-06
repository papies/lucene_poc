package org.noip.papies;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LuceneIndexerQueue {

	private static LuceneIndexerQueue instance;
	private Queue<File> documentQueue;
	
	public static LuceneIndexerQueue getInstance(){
		if(instance == null){
			instance = new LuceneIndexerQueue();
		}
		return instance;
	}
	
	private LuceneIndexerQueue(){
		this.documentQueue = new ConcurrentLinkedQueue<File>();
	}
	
	public synchronized boolean queueDocument(File document){
		this.documentQueue.add(document);
		System.out.println("Queueing document; queue size: "+ this.documentQueue.size());
		notify();
		return true;
	}
	
	public synchronized File getDocument() throws InterruptedException{
		while(this.documentQueue.isEmpty()){
			wait();
		}
		File document = this.documentQueue.poll();
		System.out.println("Polling document; queue size: "+ this.documentQueue.size());
		return document;
	}
	
}

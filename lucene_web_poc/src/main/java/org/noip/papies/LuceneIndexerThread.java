package org.noip.papies;

public class LuceneIndexerThread extends Thread{

	@Override
	public void run() {
		LuceneIndexer.getInstance().startIndexing();
	}
	
}

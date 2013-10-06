package org.noip.papies.lucene;

public class LuceneIndexerThread extends Thread{

	@Override
	public void run() {
		LuceneIndexer.getInstance().startIndexing();
	}
	
}

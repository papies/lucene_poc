package org.noip.papies;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LuceneIndexerContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent arg0) {
		new LuceneIndexerThread().start();
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		LuceneIndexer.getInstance().stopIndexing();
	}
}

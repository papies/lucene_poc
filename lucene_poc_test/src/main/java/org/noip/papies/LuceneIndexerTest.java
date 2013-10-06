package org.noip.papies;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import junit.framework.TestCase;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class LuceneIndexerTest extends TestCase {

	public void testIndex(){
		LuceneIndexerTest luceneIndexerTest = new LuceneIndexerTest();
		assertTrue(this.requestIndex());
	}

	private boolean requestIndex(){
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
		Client client = ClientBuilder.newClient(cc);
		WebTarget target = client.target("http://localhost:8080/lucene_poc").path("rest").path("index");

		//	Form form = new Form();
		//	form.param("documentPath", "foo");

		//	MyJAXBBean bean = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), MyJAXBBean.class);
		File randomFile = null;
		while(randomFile == null || getFileSize(randomFile)>1000000){
			randomFile = this.getRandomFile();
		}
		IndexRequest indexRequest = new IndexRequest(randomFile.getAbsolutePath());
		System.out.println(target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(indexRequest, MediaType.APPLICATION_JSON_TYPE)));
		return true;
	}

	private File getRandomFile(){
		//File[] roots = File.listRoots();
		//File randomRoot = roots[(int)Math.floor(Math.random() * (roots.length + 1))]; 
		File randomRoot = new File("c:\\");
		while(!randomRoot.isFile()){
			File[] randomChildren = randomRoot.listFiles();
			if(randomChildren != null && randomChildren.length > 0){
				File randomChild = randomChildren[(int)Math.floor(Math.random() * (randomChildren.length))];
				randomRoot = randomChild;
			}else{
				randomRoot = randomRoot.getParentFile();
			}
		}
		return randomRoot;
	}

	private long getFileSize(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return fis.getChannel().size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100000000;
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		LuceneIndexerTest luceneIndexerTest = new LuceneIndexerTest();
		luceneIndexerTest.requestIndex();
	}

}

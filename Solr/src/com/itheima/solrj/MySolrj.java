package com.itheima.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class MySolrj {
  @Test
  public void  testAdd() throws SolrServerException, IOException{
	 String baseURL="http://localhost:8080/solr";
	SolrServer solrServer = new  HttpSolrServer(baseURL);
	SolrInputDocument doc = new SolrInputDocument();
	doc.setField("id", "11");
	doc.setField("name","赵丽颖");
	 solrServer.add(doc);
	 solrServer.commit();
	
  }
  @Test
  public void update() throws SolrServerException, IOException{
	 // String baseURL="http://localhost:8080/solr";
	  String baseURL="http://localhost:8080/solr";
	SolrServer server = new HttpSolrServer(baseURL);
	SolrInputDocument doc= new SolrInputDocument();
	doc.setField("id", "10");
	doc.setField("name","林更新");
	server.add(doc);
	server.commit();
  }
  @Test
  public void delete() throws SolrServerException, IOException{
	  String baseURL="http://127.0.0.1:8080/solr";
	  SolrServer solrServer = new HttpSolrServer(baseURL);
	  SolrInputDocument doc = new SolrInputDocument();
	 // solrServer.deleteById("10");
	  solrServer.deleteByQuery("name:林更新");
	  solrServer.commit();
  }
  @Test
  public void search() throws SolrServerException{
	  String beanURL="http://127.0.0.1:8080/solr";
	  SolrServer solrSever = new HttpSolrServer(beanURL);
	  SolrQuery params =new  SolrQuery();
	  params.set("q", "*:*");
	QueryResponse response = solrSever.query(params);
	SolrDocumentList results = response.getResults();
	//一共几条数据
	long l = results.getNumFound();
	System.out.println(l);
	for (SolrDocument doc : results) {
		System.out.println("id:"+doc.get("id"));
		System.out.println("name:"+doc.get("name"));
	}
	  
  }
}

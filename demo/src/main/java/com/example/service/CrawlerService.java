package com.example.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
    @SuppressWarnings("finally")
    public  String getPage() {
        //配置代理，预处理
        String content = null;
        HttpHost proxy = new HttpHost("127.0.0.1", 1080);
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        String url = "https://bandwagonhost.com/vps-hosting.php";
        HttpGet request = new HttpGet(url);
        request.setConfig(config);
        CloseableHttpResponse response = null;
        
        //执行请求操作
        try {
            response = client.execute(request);
            
            //获得response的实体内容
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                content = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            return content;
        }
    }
    public boolean hasGood() {
        String content = getPage();
        boolean hasGood = false;
        if (content != null) {
            Document doc = Jsoup.parse(content);
            Elements div = doc.select("div.bronze").first().select("div ul:gt(1)");
//            Elements div = doc.select("div.bronze ").eq(1).select("div ul:gt(1)");
            //字符小于69说明至少有一个ul没有了，有货         
            hasGood = div.toString().length() != 69;
            System.out.println("是否有货物：" + hasGood);
        }
        return hasGood;
    }
}

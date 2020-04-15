package com.youjiu.demo;

//json的jar包 下载地址 http://central.maven.org/maven2/org/json/json/20180813/json-20180813.jar

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ApiDemo {
    protected String app_id;
    protected String app_secret;
    protected String base_url = "https://open.youjiuhealth.com";
    protected String access_token = "";

    /**
     * 获取访问令牌
     *
     * @return String
     */
    public String getToken() {
        if (this.access_token == null || this.access_token.equals("")) {
            //判断外部缓存是否过期
            //读取外部缓存

            //否则请求获取令牌
            String param = "app_id=" + this.app_id + "&app_secret=" + this.app_secret;
            //写入本次缓存
            this.access_token = this.doRequest("/session", param, false).getString("access_token");
        }
        //
        return this.access_token;
    }


    // 获取报告列表
    public JSONObject getReportsList(String query) {
        return this.doRequest("/reports", query, true);
    }

    // 获取报告详情
    public JSONObject getReportsDetail(String id) {
        return this.doRequest("/reports/" + id, "", true);
    }

    public String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer " + this.access_token);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
//            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public String doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer " + this.access_token);
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
//            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    public JSONObject doRequest(String httpUrl, String param, Boolean isGet) {
        httpUrl = this.base_url + httpUrl;
        String res = "";
        if (isGet) {
            res = doGet(httpUrl + "?" + param);
        } else {
            res = doPost(httpUrl, param);
        }
        //System.out.println(res);
        //转换JSON
        //json的jar包 下载地址 http://central.maven.org/maven2/org/json/json/20180813/json-20180813.jar
        return new JSONObject(res);
    }


    public ApiDemo(String app_id, String app_secret, Boolean isMch) {
        this.app_id = app_id;
        this.app_secret = app_secret;
        this.base_url = this.base_url + (isMch ? "/mch" : "/api");
    }

    public ApiDemo(String app_id, String app_secret, String base_url, Boolean isMch) {
        this.app_id = app_id;
        this.app_secret = app_secret;
        this.base_url = base_url + (isMch ? "/mch" : "/api");
    }
}

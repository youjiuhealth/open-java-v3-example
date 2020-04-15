import com.youjiu.demo.ApiDemo;

public class Main {

    public static void main(String[] args) {


//        Pool PoolObj = Pool.getInstance();
//        PoolObj.in(200);
//        PoolObj.out(200);
//        System.out.println("Hello World!");
//{"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9vcGVuLnd4M2NpdHkuY29tXC9hcGlcL3Nlc3Npb24iLCJpYXQiOjE1NjQ1NzQzNTIsImV4cCI6MTU2NDU3Nzk1MiwibmJmIjoxNTY0NTc0MzUyLCJqdGkiOiJIZkowa09lZVg5Rm0xMTBxIiwic3ViIjoxMDA1MSwicHJ2IjoiZGMwOTQ0NWY1ZTAzMjg4OTczYWRlMTUzOGE5YWViYTJlMmZjYWRlMyJ9.pMqhwwsbLSsDgALZ3YmdxkRC4IsGCnDvQY9YGCA9ZLg","token_type":"bearer","expires_in":3600}
//        ApiDemo ApiDemo = new ApiDemo(
//                "861541000186538",
//                "NmRhNjQ0MDg4MDgzNDlmNmNhMzcyOGZlOTYwYzU3ZTM4OTkzNTIyZA",
//                "http://open.wx3city.com",
//                true
//        );
//        System.out.println("获取访问令牌：" + ApiDemo.getToken());
//        System.out.println("获取报告列表：" + ApiDemo.getReportsList("client_id=3215991").toString());
//        System.out.println("获取报告详情：" + ApiDemo.getReportsDetail("20078070").toString());


        ApiDemo ApiDemo = new ApiDemo(
                "8615***********",
                "NmRhNjQ0MDg4MDgzNDlmNmNh*************************",
                "http://open.wx3city.com",
                false
        );

        System.out.println("获取访问令牌：" + ApiDemo.getToken());
        System.out.println("获取报告列表：" + ApiDemo.getReportsList("client_id=3215991").toString());
        System.out.println("获取报告详情：" + ApiDemo.getReportsDetail("20078070").toString());
        System.out.println("获取报告详情：" + ApiDemo.getReportsDetail("1527898").toString());


    }
}

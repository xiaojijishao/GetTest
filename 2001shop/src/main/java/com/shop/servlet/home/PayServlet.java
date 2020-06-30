package com.shop.servlet.home;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/home/pay")
public class PayServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("a");

        switch (action){
            case "ok":payOK(req,resp);break;
            case "pay":pay(req,resp);break;
        }

    }

    private void pay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oid = req.getParameter("oid");
        String sum = req.getParameter("sum");

        alipay(oid,sum,resp);
    }

    private void payOK(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("支付成功！");
    }

    public void alipay(String outtradeno, String money, HttpServletResponse resp) throws IOException {

        //应用ID
        String APP_ID = "2016080600180243";
        //应用的私钥
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDCaTf9vw0ImuELMt4Mmv6eeounRBI6ddCHY07NvjRLKcjpFZkl+tudelDuEki+AmsK/RYF8W+/RKdwhHSK7tBshnEfbNrMol8YxdbISygpEKwt5/dQywitBJi14MgA4nRWhgkOnrDT+QJ9Se8WNKH5+/q8im64uJhLgF+6btkPQUlc/X27xX1qX6o8zG54KTvqOnXX4HimtMLiQI1Ox+BbDYaIMvchl/LBaPdl4ZlRPPtHbtMUlU6qez9vjEwhriO8U0q73D+4OaBQ54cbAq597dLVNK0AV1fJSoJCxRKFcleS6VqpUUcQzC2aAxRw+1moO80uTjkrOCLyv71/ebVhAgMBAAECggEAC7w2OQbzTnWq5/YmGTSx8fcLvwkI4KaD8+g2tYyps2/OROOc3wIJh7P+dqB765xRwdQNJEayrCUExnH4ZvqLckGbSn5Tps6WVvR6RpTi07fB7PN3plTbLTZ90VzENel1hFOK8Gr72Y/Kaq2v1PNQY7qmJANnM5dQ+h3BqYtPZvA68vTPGXFlPeLhm21V+nQaQMOJCrNb49SWKFexGbhmFfSXVN6K0fgoVyNBUBtVdE8JE4Inln6qWw5OpzNFeWli7fGMxPrAfoKPMTzVUN75gTG9h6QK+KgxqNv0dyGxDdMixJ3bN0kyXoomvSed3FTsWiyn22BYQX5iEJQ+cDalwQKBgQDun4DO7W7YS2jtKYPnGxvReHu5tRJoNKIdNeTsQa0Y+QOs9+p1trfZAzzVK2YWM7MlAXRlQ/lFcdZKC6d08mC1uoN5KRAL6iWReUmv27GJdodhTB81nJ7xQgCMn1Ewe4kUmv1AV8wv5Bq07aFvvYCfZAaCvh7eP9Wlkm2mtF8J3QKBgQDQkX+n7iUIDA9con6JcqkyN+gInC2rjC+CaPAdNN69IrTdWhAYz1/SWLr8/ufeQLXAXZVHNGD2u1HCCneQN2aEDW3MfdysWJ93bgFU79UAx4OEV4rQY0Gy0f3Fz5NzSIo57MBeUkUMUe7+itZyfPD6AFMvhBppjgdncP8QCKO7VQKBgBBtqtCUSC1cA71QPmSK2T9yrBH9BWdnOEIKk9gwLVqqyjX+W4X/Lmkib+29LyoYJR1HHFxccaz2SWqPotfKWB4q2SJeUVyAolWBpDEXTwHHx0pLk1p9jfPON3t2/H/6AVYJ1aW4ZbZmXfBW6+ZlhZVzrX8S1801V+AuVJ5FjMWBAoGBALhX8ZG27ionpqhEwzyvCJbnTQHXMJKQbCmCoL/fzAM1mtCOWu3i1JmxbUxR7RTG8x3cWSW/SPiq5f9wiu7/2G9dAzAcCqCWEBRaOXkeJZDzxu2BBqNbJ2VrLzbYWkYp1mqONBTp+DeEb42xnwtAHSmKgfvccg/bxfWKrRY53KWlAoGAJI9uNW+FxXEwUjgyHYQ8fJPRfAs4KpYwoRlKO5BMB4Wt+oJgsnbS/nIsXH0MosZGnxO07lFPJwdKYCxfUaiHnXe0+IriKnh6N1zIbL6/UDqTK/pFIqwXE6Vyej5qlO9uTR8RAKogZbg7wMhYFYUoU/zHD++DOK96Hc8z3BXmUJk=";
        String CHARSET = "UTF-8";
        //支付宝的公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoWSSeXShSS1OLKYvJyXBFsCd7VURJG96ovt8+Hq7w5uvf1sfV7vY3caj7DDQjB3fg+oyFDmtXBbhZB9HBqvPLJ7oSwITjX6ktjenYELPlbLMyC/hzdY+YsKXGpJ85KErBWi9GyvnTydH/MdB78ySEy5aOlm876U0m4RpF/Kq53TomOA7O9F33Ye37BWiQnEmv0f28XZzAdUgQIh+uL5jSWNpezKp/kuQPN5qU9YGB2Q+Kxrg4nlY6krDY8TbTT9LT1zy2J175tFB2KLaJIdOM4Thp06yMVlMlVkEbi3vXN3opmF3MWcNstiEPDT/7PMEL2BrpA5fbTWCxf8FkuAnDQIDAQAB";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");

        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://localhost:8080/api/home/pay?a=ok" );
        alipayRequest.setNotifyUrl( "http://localhost:8080/api/home/pay?a=ok" ); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+outtradeno+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+money+","  +
                "    \"subject\":\"网站支付\","  +
                "    \"body\":\"购买商品\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        resp.setContentType( "text/html;charset="  + CHARSET);
        resp.getWriter().write(form); //直接将完整的表单html输出到页面
        resp.getWriter().flush();
        resp.getWriter().close();

       /* //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(outtradeno);//订单号
        model.setTimeoutExpress("30m");
        model.setTotalAmount(money);//金额
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://localhost:8080/home/pay?a=ok");//支付成功后回调地址
        try {
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
             e.printStackTrace();
        }*/
    }

}

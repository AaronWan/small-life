package com.smalllife.webchat.resource;

import com.smalllife.qq.AesException;
import com.smalllife.qq.XMLParse;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.*;

import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

/**
 * Created by Aaron on 27/03/2017.
 */
@Service
@Path("/webchat")
@Slf4j
public class WebchatMessageResource {
    @Path("/")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveMessage(@QueryParam("signature") String signature,
                                 @QueryParam("openid") String openId,
                                 @QueryParam("timestamp") String timestamp,
                                 @QueryParam("nonce") String nonce,@RequestBody byte[] body) throws AesException {
        Object[] content=XMLParse.extract(new String(body));
        log.info(Arrays.asList(content).toString());
        return null;
    }


    public void xx(InputStream is) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 解析xml，将获取到的返回结果xml进行解析成我们习惯的文字信息
        SAXReader reader = new SAXReader();// 第三方jar:dom4j【百度：saxreader解析xml】
        Document document = null;
        try {
            document = reader.read(is);
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 测试输出
        Set<String> keySet = map.keySet();
        // 测试输出解析后用户发过来的信息
        System.out.println(TAG + "：解析用户发送过来的信息开始");
        for (String key : keySet) {
            System.out.println(key + ":" + map.get(key));
        }
        System.out.println(TAG + "：解析用户发送过来的信息结束");

        /*
         * 该部分我们尝试按照文档的要求格式给用户回复文本信息、图文消息。重点：按照文档要求构造需要的参数。特别注意：参数区分大小写。
         */

        // //实例1：发送普通文本消息,请查看文档关于“回复文本消息”的xml格式
        //
        // // 第一步：按照回复文本信息构造需要的参数
        // TextMsg textMsg = new TextMsg();
        // textMsg.setToUserName(map.get("FromUserName"));// 发送和接收信息“User”刚好相反
        // textMsg.setFromUserName(map.get("ToUserName"));
        // textMsg.setCreateTime(new Date().getTime());// 消息创建时间 （整型）
        // textMsg.setMsgType("text");// 文本类型消息
        // textMsg.setContent("我是服务器回复给用户的信息");
        //
        // // // 第二步，将构造的信息转化为微信识别的xml格式【百度：xstream bean转xml】
        // XStream xStream = new XStream();
        // xStream.alias("xml", textMsg.getClass());
        // String textMsg2Xml = xStream.toXML(textMsg);
        // System.out.println(textMsg2Xml);
        //
        // // // 第三步，发送xml的格式信息给微信服务器，服务器转发给用户
        // PrintWriter printWriter = resp.getWriter();
        // printWriter.print(textMsg2Xml);

        // //实例2，发送图文消息。请查看文档关于“回复图文消息”的xml格式

        // 第一步：按照回复图文信息构造需要的参数
//        List<Article> articles = new ArrayList<Article>();
//        Article a = new Article();
//        a.setTitle("我是图片标题");
//        a.setUrl("www.baidu.com");// 该地址是点击图片跳转后
//        a.setPicUrl("http://b.hiphotos.baidu.com/image/pic/item/08f790529822720ea5d058ba7ccb0a46f21fab50.jpg");// 该地址是一个有效的图片地址
//        a.setDescription("我是图片的描述");
//        articles.add(a);
//        PicAndTextMsg picAndTextMsg = new PicAndTextMsg();
//        picAndTextMsg.setToUserName(map.get("FromUserName"));// 发送和接收信息“User”刚好相反
//        picAndTextMsg.setFromUserName(map.get("ToUserName"));
//        picAndTextMsg.setCreateTime(new Date().getTime());// 消息创建时间 （整型）
//        picAndTextMsg.setMsgType("news");// 图文类型消息
//        picAndTextMsg.setArticleCount(1);
//        picAndTextMsg.setArticles(articles);
//        // 第二步，将构造的信息转化为微信识别的xml格式【百度：xstream bean转xml】
//        XStream xStream = new XStream();
//        xStream.alias("xml", picAndTextMsg.getClass());
//        xStream.alias("item", a.getClass());
//        String picAndTextMsg2Xml = xStream.toXML(picAndTextMsg);
//        System.out.println(picAndTextMsg2Xml);
//        // 第三步，发送xml的格式信息给微信服务器，服务器转发给用户
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.print(picAndTextMsg2Xml);
    }}

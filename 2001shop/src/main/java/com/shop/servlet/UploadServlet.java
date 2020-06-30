package com.shop.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/api/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存上传图片 到文件中
        //添加文件上传，修改了post的编码格式为multipart/form-data request.getParameter()接受不到参数。

        //tomcat 运行目录
        String path = this.getServletContext().getRealPath("/static/upload");
        // 本地磁盘目录
        //String path = "E:\\otherIdeaProjects\\2001shop\\src\\main\\webapp\\static\\upload";

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        Map<String,String> responseMap = new HashMap<>();
        try {

            //将请求 转换成 FileItem 的集合
            List<FileItem> items = upload.parseRequest(req);

            for(FileItem item : items){
                if(!item.isFormField()){///说明是文件上传，将上传的临时文件 保存到 服务器的某个目录中

                    //得到当前服务器运行的物理路径
                    //最好保存到 代码的路径，这样每次部署，不会丢图片

                    File pathFile = new File(path);
                    if(!pathFile.exists()){//判断该路径是否存在，不存在则创建
                        pathFile.mkdirs();
                    }
                    //文件名重名 会覆盖的问题，以及文件后缀获取

                    //获取后缀
                    String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                    //拼接名字
                    //uuid通用唯一识别码
                    String newFileName = UUID.randomUUID() +suffix;
                    //拼接保存路径
                    File file = new File(path+File.separator+ newFileName);
                    //将上传的临时文件 保存到 预定的路径中
                    item.write(file);

                    responseMap.put("state","SUCCESS");
                    responseMap.put("url","/static/upload/"+newFileName);
                    responseMap.put("title",newFileName);
                    responseMap.put("original",newFileName);

                    resp.setCharacterEncoding("utf-8");
                    resp.getWriter().print(JSON.toJSONString(responseMap));
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

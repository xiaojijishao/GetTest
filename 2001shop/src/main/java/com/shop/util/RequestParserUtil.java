package com.shop.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestParserUtil {

    public static Map<String,String> parse(HttpServletRequest req,String absoluteSavePath,String uploadGetPath){

        //文件上传，修改了post的编码格式为multipart/form-data request.getParameter()接受不到参数。

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        Map<String,String> parameterMap = new HashMap<>();
        try {
            //将请求 转换成 FileItem 的集合
            List<FileItem> items = upload.parseRequest(req);

            for(FileItem item : items){
                //System.out.println(item.getFieldName()+":"+item.getString());
                if(item.isFormField()){//普通数据

                    //因为文件上传 改变了编码格式，request设置字符集失效，此处需要手动转码
                    parameterMap.put(item.getFieldName(),new String(item.getString().getBytes("ISO8859-1"),"utf-8"));

                }else{//说明是文件上传，将上传的临时文件 保存到 服务器的某个目录中

                    if(absoluteSavePath != null){
                        //得到当前服务器运行的物理路径
                        //最好保存到 代码的路径，这样每次部署，不会丢图片
                        File pathFile = new File(absoluteSavePath);
                        if(!pathFile.exists()){//判断该路径是否存在，不存在则创建
                            pathFile.mkdirs();
                        }

                        //获取后缀
                        String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                        //拼接名字
                        //uuid通用唯一识别码
                        String newFileName = UUID.randomUUID() +suffix;
                        //拼接保存路径
                        File file = new File(absoluteSavePath+File.separator+ newFileName);
                        //将上传的临时文件 保存到 预定的路径中
                        item.write(file);

                        parameterMap.put(item.getFieldName(),uploadGetPath+newFileName);
                    }

                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parameterMap;
    }

}

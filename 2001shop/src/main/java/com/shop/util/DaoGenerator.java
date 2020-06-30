package com.shop.util;

import java.io.*;

public class DaoGenerator {

    public static void main(String[] args) {
        //生成的路径
        String daoIntePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\dao\\inte";
        String daoImplPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\dao\\impl";
        //模板的路径
        String daoInteTemplatePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\util\\DaoInte.template";
        String daoImplTemplatePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\util\\DaoImpl.template";

        String[] classes = {"Admin","Client","Address","CartItem","OrderItem","Orders","Product","Shop","Type"};
        for(String clz : classes){
            make(daoIntePath,daoInteTemplatePath,clz,"DaoInte");
            make(daoImplPath,daoImplTemplatePath,clz,"DaoImpl");
        }



    }
    //将读到的内容 {?} 替换成 对应的类名
    public static void make(String daoPath,String templatePath,String className,String suffix){

        //生成的路径
        //String daoPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\dao\\impl";

        //模板的路径
        //String templatePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\shop\\util\\DaoImpl.template";

        //String className = "Client";


        File template = new File(templatePath);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(template));
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line+"\r\n");
            }

            //将读到的内容 {?} 替换成 对应的类名
            String content = sb.toString().replaceAll("\\{\\?\\}",className);
            //System.out.println(content);

            //写出文件
            PrintWriter pw = new PrintWriter(new FileWriter(daoPath+"\\"+className+suffix+".java"));
            pw.println(content);
            pw.flush();
            pw.close();

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

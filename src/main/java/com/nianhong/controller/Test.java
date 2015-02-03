package com.nianhong.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class Test {
    // @Autowired
    // TemplateService templateService;
    @Autowired
    CommonsMultipartResolver multipartResolver;

    //@ResponseBody
    @RequestMapping(value = "tempimg")
    public void addCategory(HttpServletRequest request, String path1,
            String path2, HttpServletResponse actioncontext) {
        System.out.println("添加图片");
        String re=null;
        String sub=null;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile imgFile1 = multipartRequest.getFile("file");// 获取上次文件名
        System.out.println("----->" + imgFile1.getOriginalFilename());
        List<String> fileTypes = new ArrayList<String>();
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
        fileTypes.add("gif");
        if (!(imgFile1.getOriginalFilename() == null || "".equals(imgFile1
                .getOriginalFilename()))) {
            String uploadfile = request.getServletContext().getRealPath(
                    "imgfile");
            System.out.println("--->" + path1 + "--->" + path2);
            System.out.println("上传路径---->" + uploadfile);
            
            File file1 = this.getFile(imgFile1, uploadfile, fileTypes);
            System.out.println("完成上传------>" + file1);
            re=file1.toString();
            //int i=re.lastIndexOf("\\");
            sub=re.substring(re.lastIndexOf("\\")+1);
            System.out.println("截取--->"+sub);
        }
        actioncontext.setContentType("text/html");
        PrintWriter out;
        try {
            out = actioncontext.getWriter();
            out.println("/imgfile/" + sub);
            //out.flush();
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "imgfile/"+sub;
    }
    
    // private File getFile(MultipartFile imgFile, String typeName,
    private File getFile(MultipartFile imgFile, String brandName, List fileTypes) {
        String fileName = imgFile.getOriginalFilename();
        // 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        System.out.println("获取扩展名---->" + ext);
        // 对扩展名进行小写转换
        ext = ext.toLowerCase();

        File file = null;
        if (fileTypes.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
            file = this.creatFolder(brandName, fileName);
            System.out.println("完整路径--->" + file);
            try {
                imgFile.transferTo(file); // 保存上传的文件
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private File creatFolder(String brandName, String fileName) {
        File file = null;

        File firstFolder = new File(brandName);
        System.out.println("文件路径--->" + firstFolder);

        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        System.out.println("截取文件后缀--->" + suffix);
        String prefix = System.currentTimeMillis() + "";
        String newfileName = prefix + suffix;
        if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹
            System.out.println("现在路径---->" + brandName);
            file = new File(brandName + "/" + newfileName);
        } else { // 如果一级不存在，则创建一级文件夹
            firstFolder.mkdir();
            System.out.println("现在路径---->" + brandName);
            file = new File(brandName + "/" + newfileName);
        }
        return file;
    }
    
    /**
     * 通过url请求返回图像的字节流
     */
    @RequestMapping("test.do")
    public void getIcon(
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {


//        if(StringUtils.isEmpty(cateogry)) {
//            cateogry = "";
//        }
//
//        String fileName = request.getSession().getServletContext().getRealPath("/")
//                + "resource\\icons\\auth\\"
//                + cateogry.toUpperCase().trim() + ".png";

        String fileName = "/Users/yqw/Documents/workspace/eclipse_luna/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nianhong/upload/17_10251381216312211.jpg";
        File file = new File(fileName);

        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
            file = new File(request.getSession().getServletContext().getRealPath("/")
                    + "resource/icons/auth/root.png");
        }

        FileInputStream inputStream = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        int length = inputStream.read(data);
        inputStream.close();

        response.setContentType("image/png");

        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
}
package com.chihuo.food.interfaces.facade;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/file")
public class FileApi {
    //支持的图片类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/gif", "image/jpeg","image/png", "image/bmp");
    private static final Logger LOGGER = LoggerFactory.getLogger(FileApi.class);
    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private String port;
    @Value("${file.upload.resource}")
    private String fileUploadResource;
    @Value("${file.upload.path}")
    private String fileUploadPath;

    /**
     * 普通文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
    	//文件原始名称
        String originalFilename = file.getOriginalFilename();
        //建议文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPE.contains(contentType)) {
            LOGGER.info("文件类型不合法:{}", originalFilename);
            return "文件类型不合法";
        }
        try {
            //检验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("文件内容不合法:{}", originalFilename);
                return "文件内容不合法";
            }
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));  // 后缀名
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName; // 新文件名
            //保存文件
            file.transferTo(new File(fileUploadPath + newFileName));
            //返回图片路径
            String fileAccess = "http://" + host + ":" + port + fileUploadResource + newFileName;
            return fileAccess;
        } catch (IOException e) {
            LOGGER.error("服务器内部错误->:{}", originalFilename);
            e.printStackTrace();
        }
        return "文件上传失败";
    }
    
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
    
    /**
     * kindeditor上传
     * @param request
     * @param response
     * @param dir
     * @throws Exception
     */
    @RequestMapping("/uploadJson")
    public void uploadJson(HttpServletRequest request, HttpServletResponse response, String dir) throws Exception {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        //文件保存目录路径
        String savePath = fileUploadPath;
        //定义允许上传的文件扩展名
        Map<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
 
        response.setContentType("text/html; charset=UTF-8");
 
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println(getError("请选择文件。"));
            return;
        }
        //检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.isDirectory()) {
            out.println(getError("上传目录不存在。"));
            return;
        }
        //检查目录写权限
        if (!uploadDir.canWrite()) {
            out.println(getError("上传目录没有写权限。"));
            return;
        }
 
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            out.println(getError("目录名不正确。"));
            return;
        }
        //创建文件夹
        savePath += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
 
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        //此处是直接采用Spring的上传
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileFullname = mf.getOriginalFilename();
            fileFullname = fileFullname.replace('&', 'a');
            fileFullname = fileFullname.replace(',', 'b');
            fileFullname = fileFullname.replace('，', 'c');
            //检查扩展名
            String fileExt = fileFullname.substring(fileFullname.lastIndexOf(".") + 1).toLowerCase();
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
 
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
 
            File uploadFile = null;
            if (extMap.get("file").contains(fileExt)) {
                uploadFile = new File(savePath + fileFullname);
            } else {
                uploadFile = new File(savePath + newFileName);
            }
            try {
                FileCopyUtils.copy(mf.getBytes(), uploadFile);
                JSONObject obj = new JSONObject();
                obj.put("error", 0);
                obj.put("url", "http://" + host + ":" + port + fileUploadResource + dirName + "/" + ymd + "/" + newFileName);
                out.println(obj.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                out.println(getError("上传文件失败。"));
                return;
            }
        }
    }

}

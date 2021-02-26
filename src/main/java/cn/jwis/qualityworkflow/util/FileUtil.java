package cn.jwis.qualityworkflow.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu
 * Created date 2019/8/7 15:55
 */
public class FileUtil {
    //固定文件读取的长度
    private  static  byte[] bytes = new  byte[2048];
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir
     *            将要删除的文件目录
     * @return
     */
    public static boolean deleteDir(File dir) {
        //判断是否是文件夹
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null && children.length > 0) {
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        return dir.delete();
    }

    /**
     * 文件输出流转成字节数组（运用场景：在MQ发送消息的时候可以不能直接发送文件和流所以需要做一个这个操作）
     * @param inputStream 需要转成字节数组的文件流
     * @return  转换之后的字节数组
     * @throws IOException
     */
    public static byte[] readSteam(InputStream inputStream) throws IOException {
        //创建一个字节数组的输出流
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个读取的最大字节数组
        byte[] buffer = bytes;
        int len = 0;
        while ((len = inputStream.read(buffer))!= -1){
             outStream.write(buffer,0,len);
        }
        outStream.close();
        inputStream.close();
        return  outStream.toByteArray();
    }

    /**
     *
     * @Description: 将byte[]转换成文件（fileUtils）字节流转成文件可以直接用缓冲输出流读取不用定义一个标准的字节数组
     * @param fileName
     * @param bytes
     * @return
     * @throws IOException
     */
    public static File getFileByBytes(String fileName, byte[] bytes) throws IOException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = FileUtil.newFile(fileName);
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } finally {
            if (bos != null) {
                bos.close();
            }
        }
        return file;
    }

    /**
     * @Description: 规定一个文件存储的路径（标准）
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File newFile(String fileName) throws IOException {
        //选择target下面的classes文件夹下面的fileUpload作为文件的存储地址
        String fileUploadPath = ResourceUtils.getURL("classpath:").getPath() + "/fileUpload";
        File f = new File(fileUploadPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String filepath = fileUploadPath + "/" + fileName;
        return new File(filepath);
    }

    /**
     * @Description: 获取文件夹下面的所有文件
     * @param filePath
     * @param list
     * @return
     */
    public static List<File> traverseFolder(String filePath,List<File> list){
        File file = new File(filePath);
        //判断这个文件是否存在
        if (file.exists()){
           File [] files = file.listFiles();
           //判断是否是个空文件夹
           if (files == null || files.length == 0){
               return list;
           }else {
               for (File file1: files) {
                   //判断是否是文件夹
                   if (file1.isDirectory()){
                      traverseFolder(file1.getAbsolutePath(),list);//如果是文件夹，递归调用方法获取文件
                   }else {
                       list.add(file1);
                   }
               }
           }
        }else {    //不存在就直接返回list
            return  list;
        }
        return list;
    }

    /**
     * @Description: 筛选出相同类型的文件
     * @param list
     * @param suffix
     * @return
     */
    public static List<File> getSameTypeFile(List<File> list, String suffix){
       List<File> sameFiles = new ArrayList<>();
       for (File file :list){
           if (file.getName().endsWith(suffix)){
               sameFiles.add(file);
           }
       }
       return  sameFiles;
    }

    /**
     * 流转成文件
     * @param is  文件的输入流
     * @param fileName 文件名
     * @throws IOException
     */
    public static void  streamToFile(InputStream is,String fileName) throws IOException {
       File file =  newFile(fileName);
       FileOutputStream fos =  new FileOutputStream(file);
       BufferedOutputStream bos = new BufferedOutputStream(fos);
       byte[] buffer = bytes;
       int len = 0;
       while ((len = is.read(buffer))!= -1){
           bos.write(buffer,0,len);
       }
       bos.close();
       is.close();
    }

    /**
     * 输入流转文件（参数与上面不一致）
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MultipartFile 转 File
     * @param file
     * @return
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file != null) {
            try (InputStream ins = file.getInputStream()) {
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("服务器未找到传入文件");
            }
        }
        return toFile;
    }
    
    /**
	 * 删除临时文件
	 */
	public  static  void  deleteFile(String fileName){
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

}

package com.yinjunbiao.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.util.UUID;

public class UploadUtil {

    private static String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static String accessKeyId = "LTAI5tE4PY85ciPUzkmPKTwd";
    private static String accessKeySecret = "wU9qgJ0hHK2OCec982LrauSLFp3rma";
    // 填写Bucket名称，例如examplebucket。
    private static String bucketName = "souln";


    public static String upload(InputStream inputStream){
        String fileName = "java/" + UUID.randomUUID().toString() + ".png";

        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileName,inputStream);

        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
    }
}

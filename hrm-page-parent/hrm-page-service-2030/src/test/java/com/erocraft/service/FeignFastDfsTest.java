package com.erocraft.service;


import com.erocraft.PageServiceApplication2030;
import com.erocraft.client.FastDfsClient;
import com.erocraft.util.AjaxResult;
import feign.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PageServiceApplication2030.class)
public class FeignFastDfsTest {

    @Autowired
    private FastDfsClient fastDfsClient;

    @Test
    public void download() throws Exception {
        Response response = fastDfsClient.download("/group1/M00/00/04/rAEABG6TNQWACj5qAACAMM9O0Ak762.zip");
        InputStream inputStream = response.body().asInputStream();
        IOUtils.copy(inputStream,new FileOutputStream("d://test.zip"));

    }


    @Test
    public void upload() {
        MultipartFile file = new CommonsMultipartFile(createFileItem(new File("d://Pager.vue")));
        AjaxResult ajaxResult = fastDfsClient.upload(file);
        System.out.println(ajaxResult.getResultObj());
    }

    private FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem("file", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}

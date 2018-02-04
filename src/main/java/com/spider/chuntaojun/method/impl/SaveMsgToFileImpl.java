package com.spider.chuntaojun.method.impl;

import com.spider.chuntaojun.enity.FoodBook;
import com.spider.chuntaojun.method.SaveMsgToFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chuntaojun
 */
public class SaveMsgToFileImpl implements SaveMsgToFile {

    private static final String FILE_PATH = "/Volumes/Extended/code/IdeaProjects/Spider-practice/IMG";

    @Override
    public void savePic(FoodBook foodBook) {
        File file = new File(FILE_PATH + "/" + foodBook.getFoodName());
        file.mkdirs();
        int i = 0;
        int length;
        for (String picUrl : foodBook.getFoodPicUrls()) {
            try {
                URL url = new URL(picUrl);
                DataInputStream dis = new DataInputStream(url.openStream());
                String fileName = FILE_PATH + "/" + foodBook.getFoodName() + "/" + "picture_" + i + ".jpg";
                FileOutputStream fos = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                while ((length = dis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                dis.close();
                fos.close();
                i += 1;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

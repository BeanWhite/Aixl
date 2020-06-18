package com.aixl.m.component;


import com.alibaba.druid.support.logging.Log;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.util.*;


/**
 * 定时清空服务器缓存
 */
@Component
public class DeleteFileTimer {
    public DeleteFileTimer() throws ParseException {
        init();
    }

    public void init() throws ParseException {
        Timer t = new Timer();
        //设置日期
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //将当前日期放入日历
        calendar.add(Calendar.DATE, 1);//日历向后移动一天，负数为向前移动
        date = calendar.getTime();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        //日期格式
//        String s = "2020-03-31 21:00:00";
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       Date d2 = format.parse(s);
        //t.schedule(DeleteFolder(), d);
        t.schedule(new DeleteFolder(), date);
    }

    private class DeleteFolder extends TimerTask {

        @Override
        public void run() {
            File file = new File("D:\\nginx-1.17.7\\html\\xlrzpc\\img\\cacheImg");
            File file1 = new File("D:\\nginx-1.17.7\\html\\xlrzpc\\js\\pdfDocument");
            ArrayList<File> arrayList = new ArrayList<>();
            arrayList.add(file);
            arrayList.add(file1);
            try {
                deleteFile(arrayList);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public void deleteFile(ArrayList<File> arrayList) throws ParseException {
            for (File file : arrayList)
                if (file.exists()) {
                    File[] farlay = file.listFiles();
                    if (farlay != null) {
                        for (File f : farlay) {
                            if (f.isDirectory()) {
                                //deleteFile();
                            } else {
                                f.delete();
                            }
                            System.out.println(f.toPath());
                        }
                        // file.delete();
                    } else {
                     
                        System.out.println("删除失败");
                    }
                }
            init();
        }
    }
}

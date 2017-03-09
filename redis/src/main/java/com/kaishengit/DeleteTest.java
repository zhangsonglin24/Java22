package com.kaishengit;

import org.junit.Test;

import java.io.File;

public class DeleteTest {
    public void del(String path) {

          File file = new File(path);
          if (file.isFile()){
            file.delete();
        }else {
              File[] files = file.listFiles();
              if(files != null){
                  for (File subFile : files){
                        del(subFile.getAbsolutePath());
                  }
                  file.delete();
              }
          }
        System.out.println("删除成功！！！");
    }
    @Test
    public void test(){
        del("D:/del");
    }
}

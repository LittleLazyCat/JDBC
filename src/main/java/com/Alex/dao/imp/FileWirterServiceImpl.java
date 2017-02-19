package com.Alex.dao.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Alex.dao.FileWriterService;

//����һ��Bean��id
@Component("fileWriterService")
public class FileWirterServiceImpl implements FileWriterService{

  //ͨ�������ļ���ȡ������ע�뵽FileWirterServiceImpl��
  @Value("${filePath}")
  private File file;
  private FileWriter fw;
  //����ʱ���ã���һ���ļ�����ȡ�����
  @PostConstruct
  public void init() {
      try {
          //�ж��ļ��Ƿ���ڣ��粻�����򴴽��ļ�
          if (!file.exists()) {
              file.createNewFile();
          }
          //���ļ�
          fw = new FileWriter(file, true);
          System.out.println("���ļ�");
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  public void write(String content) {
      try {
          //���ļ���д�����ݣ�������
          fw.write(content+"\n");
          System.out.println("�ļ�д��:"+content);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  //����ǰ���ã��ر��ļ���������ͷ��ļ�
  @PreDestroy
  public void destory() {
      try {
          //�ر��ļ��������
          if (fw != null) {
              fw.flush();
              fw.close();
          }
          System.out.println("�ر��ļ�");
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}

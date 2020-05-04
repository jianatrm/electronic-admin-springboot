
package com.electronic.service.impl;

import com.electronic.contants.FileConstants;
import com.electronic.dto.FileConvertResultDTO;
import com.electronic.service.PreviewService;
import com.electronic.utils.FileUtil;
import org.jodconverter.DocumentConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.office.OfficeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;

@Service
public class PreviewServiceImpl implements PreviewService {

  public static  final Logger LOGGER = LoggerFactory.getLogger(PreviewServiceImpl.class);


  @Value("${jodconverter.store.path}")
  private String storePath;

  @Autowired
  private DocumentConverter documentConverter;

  @Override
  public FileConvertResultDTO convertFile2pdf(File sourceFile, String fileExt) {
    FileConvertResultDTO fileConvertResultDTO = new FileConvertResultDTO();
    try {
      fileExt = fileExt.toLowerCase();
      String fileName = FileUtil.getWithoutExtension(sourceFile.getName());
      String targetFileExt = getTargetFileExt(fileExt);
      File targetFile = new File(storePath+ FileUtil.SLASH_ONE + fileName + FileUtil.DOT + targetFileExt);
      documentConverter.convert(sourceFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(fileExt))
          .to(targetFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(targetFileExt)).execute();
      fileConvertResultDTO.setStatus("success");
      fileConvertResultDTO.setTargetFileName(targetFile.getName());
    } catch (OfficeException e) {
      LOGGER.error("convertFile2pdf error : " + e.getMessage(),e);
      fileConvertResultDTO.setStatus("fail");
    }
    return fileConvertResultDTO;

  }

  @Override
  public FileConvertResultDTO convertInputStream2pdf(InputStream in, String fileName, String fileExt) {
    FileConvertResultDTO fileConvertResultDTO = new FileConvertResultDTO();
    try {
      fileExt = fileExt.toLowerCase();
      fileName = FileUtil.getWithoutExtension(fileName);
      String targetFileExt = getTargetFileExt(fileExt);
      File targetFile = new File(storePath+ FileUtil.SLASH_ONE + fileName + FileUtil.DOT + targetFileExt);
      documentConverter.convert(in).as(DefaultDocumentFormatRegistry.getFormatByExtension(fileExt))
          .to(targetFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(targetFileExt)).execute();
      fileConvertResultDTO.setStatus("success");
      fileConvertResultDTO.setTargetFileName(targetFile.getName());
    } catch (OfficeException e) {
      LOGGER.error("convertInputStream2pdf error : " + e.getMessage(),e);
      fileConvertResultDTO.setStatus("fail");
    }catch (Exception e){
      LOGGER.error("convertInputStream2pdf error : " + e.getMessage(),e);
      fileConvertResultDTO.setStatus("fail");
    }
    return fileConvertResultDTO;
  }

/**
   * 获取想要转换的格式类型
   * @return*/


  private String getTargetFileExt(String originFileExt){
     if(FileConstants.fileType2Htmls.contains(originFileExt)){
       return FileUtil.HTML;
     }
     return FileUtil.PDF;
  }

  @PostConstruct
  private void init(){
    File targetDir = new File(storePath);
    if(!targetDir.exists()){
      targetDir.mkdirs();
    }
  }
}

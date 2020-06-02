package com.electronic.controller;

import com.electronic.base.BaseResponse;
import com.electronic.base.UploadFileResponse;
import com.electronic.contants.BusinessConstants;
import com.electronic.dao.mapper.bo.DocHistory;
import com.electronic.dao.mapper.interfaces.DocHistoryMapper;
import com.electronic.service.FileService;
import com.electronic.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    DocHistoryMapper docHistoryMapper;

    @RequestMapping("/uploadFile")
    public BaseResponse uploadFile(@RequestParam("file") MultipartFile file){
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
        String fileName = fileService.storeFile(file);
        String extension = FileUtil.getExtension(fileName);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile/")
                .path(fileName.split("&&")[0]+"."+extension)
                .toUriString();

        baseResponse.setResult(new UploadFileResponse(fileName,fileDownloadUri,extension,0));
        return baseResponse;
    }


    @PostMapping("/uploadMultipleFiles")
    public List<BaseResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        try{
            DocHistory docHistory = docHistoryMapper.selectByPrimaryKey(FileUtil.getWithoutExtension(fileName));
            fileName = docHistory.getDocName();
        }catch (Exception e){}

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName, "utf-8")))
                .body(resource);
    }
}

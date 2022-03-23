package com.filemanage.hardtech.service;

import com.filemanage.hardtech.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentServie {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}

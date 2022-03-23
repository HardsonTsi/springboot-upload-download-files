package com.filemanage.hardtech.service;

import com.filemanage.hardtech.entity.Attachment;
import com.filemanage.hardtech.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImplImpl implements AttachmentServie {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence" + fileName);
            }
            String fileType = file.getContentType();
            byte[] data = file.getBytes();
            Attachment attachment = new Attachment(fileName, fileType, data);
            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could't save file" + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(() -> new Exception("File not found with  Id" + fileId));
    }
}

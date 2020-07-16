package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    private FilesMapper filesMapper;

    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public boolean isFilenameAvailable(String fileName, Integer userId) {
        return filesMapper.getFileByFileName(fileName, userId) == null;
    }

    public void saveFile(UserFile file) {
        Integer fileId = filesMapper.saveFile(file);
    }

    public List<UserFile> getFilesByUserId(Integer userId) {
        return filesMapper.getFilesByUserId(userId);
    }

    public UserFile getFileByFileId(Integer fileId) {
        return filesMapper.getFileByFileId(fileId);
    }

    public void deleteFile(Integer fileId) {
        filesMapper.deleteFileByFileId(fileId);
    }
}

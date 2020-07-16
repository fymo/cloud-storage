package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{fileName} AND userid = #{userId}")
    UserFile getFileByFileName(String fileName, Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    UserFile getFileByFileId(Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer saveFile(UserFile file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<UserFile> getFilesByUserId(Integer userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteFileByFileId(Integer fileId);
}

package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public List<UserCredential> getCredentialsByUserId(Integer userId) {
        return credentialsMapper.getCredentialsByUserId(userId);
    }

    public Integer saveCredential(UserCredential credential) {
        Integer credentialId = credential.getCredentialId();
        if(credentialId == null){
            credentialId = credentialsMapper.saveCredential(credential);
        }else{
            credentialsMapper.updateCredential(credential);
        }
        return credentialId;
    }

    public void deleteCredential(Integer credentialId) {
        credentialsMapper.deleteCredentialById(credentialId);
    }

    public UserCredential getCredentialById(Integer credentialId) {
        return credentialsMapper.getCredentialById(credentialId);
    }
}

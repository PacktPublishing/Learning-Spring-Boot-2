package com.packt.learning.spring.boot.d02s02.service;

import com.packt.learning.spring.boot.d02s02.RunProfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(RunProfiles.PROD)
public class ProdFileSavingService implements FileSavingService {

    @Override
    public void saveFile(String fileName) {
        System.out.println("Saving the file on an SFTP server...");
    }
}

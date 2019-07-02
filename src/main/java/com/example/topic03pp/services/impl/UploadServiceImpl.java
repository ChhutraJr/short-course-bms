package com.example.topic03pp.services.impl;

import com.example.topic03pp.services.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@PropertySource("classpath:/bms.properties")
public class UploadServiceImpl implements UploadService {


    @Value("${file.client.path}")
    private String client_path;

    @Value("${file.server.path}")
    private String server_path;

    @Override
    public String singleFileUpload(MultipartFile file, String folder) {

        if (file.isEmpty())
            return null;


        if (folder == null)
            folder = "";

        File path = new File(server_path + folder);

        if (!path.exists())
            path.mkdirs();


        String filename = file.getOriginalFilename();

        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        System.out.println(filename);
        System.out.println(extension);


        filename = UUID.randomUUID() + "." + extension;


        System.out.println(filename);

        try {
            Files.copy(file.getInputStream(), Paths.get(server_path + folder, filename));
        } catch (IOException e) {

        }

        return (client_path + folder + filename);
    }


    @Override
    public List<String> multipleFileUpload(List<MultipartFile> files, String folder) {
        List<String> filenames = new ArrayList<>();

        files.forEach(file -> {
            filenames.add(this.singleFileUpload(file, folder));
        });

       /* for (MultipartFile file :
                files) {
            filenames.add(this.singleFileUpload(file, folder));
        }*/

        return filenames;
    }

    @Override
    public String upload(MultipartFile file, String folder) {
        return this.singleFileUpload(file, folder);
    }

    @Override
    public String upload(MultipartFile file) {
        return this.singleFileUpload(file, null);
    }

    @Override
    public List<String> upload(List<MultipartFile> files, String folder) {
        return this.multipleFileUpload(files, folder);
    }

    @Override
    public List<String> upload(List<MultipartFile> files) {
        return this.multipleFileUpload(files, null);
    }
}

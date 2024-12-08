package com.example.ecommerce.services.impl;

import com.example.ecommerce.services.IImageService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

@Service
public class ImageServiceImpl implements IImageService {

    private final MinioClient minioClient;


    @Value("${minio.bucketName}")
    private String bucketName;

    public ImageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadImage(MultipartFile file) throws Exception{
        String objectName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }
       String url =  minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .method(Method.GET)
                        .build());

        return transformUrl(url);

    }

    private String transformUrl(String originalUrl) {
        try {
            // Parsear la URL original para extraer las partes
            URI originalUri = new URI(originalUrl);

            // Crear una nueva URI con el dominio deseado
            String newHost = "casasfriocalor.com.ar";
            URI transformedUri = new URI(
                    "https",                     // Esquema (https)
                    originalUri.getUserInfo(),   // Información del usuario (normalmente null)
                    newHost,                     // Nuevo host
                    -1,                          // Puerto (usar puerto predeterminado para https)
                    originalUri.getPath(),       // Mantener el path original
                    null,                        // Sin query (si quieres mantenerla, usa originalUri.getQuery())
                    null                         // Sin fragmento
            );

            return transformedUri.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error transformando la URL", e);
        }
    }
}



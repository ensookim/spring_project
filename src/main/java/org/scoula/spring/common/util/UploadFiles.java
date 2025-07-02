package org.scoula.spring.common.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class UploadFiles {


    public static String upload(String baseDir, MultipartFile part) throws IOException {
// 기본 디렉토리가 있는지 확인, 없으면 새로 생성
        File base = new File(baseDir);
        if(!base.exists()) {
            base.mkdirs(); // 중간에 존재하지 않는 디렉토리까지 모두 생성
        }
        String fileName = part.getOriginalFilename();
        File dest = new File(baseDir, UploadFileName.getUniqueName(fileName));
        part.transferTo(dest); // 지정한 경로로 업로드 파일 이동
        return dest.getPath(); // 저장된 파일 경로 리턴
    }


    public static void downloadImage(HttpServletResponse response, File file) {
        try {
            Path path = Path.of(file.getPath());
            String mimeType = Files.probeContentType(path);
            response.setContentType(mimeType);
            response.setContentLength((int) file.length());
            try (OutputStream os = response.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(os)) {
                Files.copy(path, bos);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void download(HttpServletResponse response, File file, String filename) {
        try {
            if (!file.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Content-Type 설정
            response.setContentType("application/octet-stream");
            response.setContentLengthLong(file.length());

            // 파일 이름 인코딩 (한글, 특수문자 등 처리)
            String encodedFileName = java.net.URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

            // 헤더 설정
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

            // 파일 스트림 전송
            Path path = file.toPath();
            try (OutputStream os = response.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(os)) {
                Files.copy(path, bos);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 중 오류 발생", e);
        }
    }
}
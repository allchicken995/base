package com.ronllan.modules.sys.controller;

import com.ronllan.common.utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件操作
 *
 * @author xyj godlikexyj@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("sys/file")
@Tag(name = "文件操作")
public class SysFileController {

    @Value("${upload.file.dir}")  // 配置文件存储路径
    private String uploadDir = "D:\\Data";

    @Value("${upload.file.url}")  // 配置文件访问 URL
    private String fileUrl = "http://192.168.0.116/uploads/";

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result().error("文件为空");
        }
        Map<String, String> result = new HashMap<>(8);
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffix;
        File newFile;
        // 根据文件后缀判断是图片还是视频
        // 目标文件路径
        if (".mp4".equals(suffix)){
            newFile = new File(uploadDir + File.separator + "video", newFileName);
        }else {
            newFile = new File(uploadDir + File.separator + "jpg", newFileName);
        }
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
            // 如果是视频文件则生成封面
            if (".mp4".equals(suffix)){
                // 生成视频封面
                String thumbnailPath = uploadDir + File.separator + "video" + File.separator + "title" + File.separator + newFileName.replaceAll("\\.mp4$", ".jpg");
                generateThumbnail(newFile.getAbsolutePath(), thumbnailPath);
                result.put("type", "video");
                result.put("title", fileUrl + "video" + File.separator + "title" + File.separator + thumbnailPath);
                result.put("title", fileUrl + "video" + File.separator + newFileName);
            }else {
                result.put("type", "image");
                result.put("jpg", fileUrl + newFileName);
            }
            // 返回文件 URL
            return new Result().ok(result);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result().error("文件上传失败");
        }
    }

    /**
     * 生成视频封面
     */
    private void generateThumbnail(String videoPath, String imagePath) {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();

            // 读取第一帧图像
            Frame frame = grabber.grabImage();

            if (frame != null) {
                // 将帧转换为 BufferedImage
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage image = converter.convert(frame);

                // 保存图片
                ImageIO.write(image, "jpg", new File(imagePath));
            }

            grabber.stop();
        } catch (Exception e) {
            throw new RuntimeException("生成视频封面失败: " + e.getMessage(), e);
        }
    }

}

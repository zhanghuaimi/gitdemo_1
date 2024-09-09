package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.aop.LogCheck;
import com.example.demo.pojo.Result;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@CrossOrigin
public class FileUploadController {


	@Value("${web.profile}")
	private String path1;


	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result newUploadFile(@RequestParam List<MultipartFile> file, HttpServletRequest request,
								HttpServletResponse response) throws IOException {

		String uuid = UUID.randomUUID().toString().replace("-", "");

		Result result=new Result();
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		final String format = simpleDateFormat.format(date);

		String path=path1+format+"/";

		FileOutputStream output = null;
		try {
			if (file != null && file.size() > 0) {
				for (MultipartFile fileObj : file) {
					String filename=fileObj.getOriginalFilename();
					String fileType=filename.substring(filename.lastIndexOf("."));  // 后缀名
					String fileName = uuid + fileType; // 新文件名
					File dest = new File(path + fileName);
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					output = new FileOutputStream(path + fileName);
					String url = "/"+format+"/";
					int copy = IOUtils.copy(fileObj.getInputStream(), output);

					result.setData(url+fileName);
					result.setCode(200);
					result.setMsg("success");
				}
			}
		} catch (Exception e) {
			result.setCode(500);
			result.setMsg("error");
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return result;
	}
}

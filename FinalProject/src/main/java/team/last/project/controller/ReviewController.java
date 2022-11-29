package team.last.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.ReviewDto;
import team.last.project.entity.Img;
import team.last.project.entity.Member;
import team.last.project.entity.Review;
import team.last.project.entity.Room;
import team.last.project.service.ImgService;
import team.last.project.service.MemberService;
import team.last.project.service.ReviewService;
import team.last.project.service.RoomService;

@Controller
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {
	@Value("${upload.path}")
	private String uploadPath;
	private final MemberService memberService;
	private final RoomService roomService;
	private final ReviewService reviewService;
	private final ImgService imgService;
	
@GetMapping("/write")
public String reviewwriteform(int roomid, Authentication authentication, Model model) {
	System.out.println(roomid);
	model.addAttribute("roomid",roomid);
	model.addAttribute("reviewDto",new ReviewDto());
	String name = memberService.memgetName(authentication.getName());
	model.addAttribute("name", name);
	return "/review/reviewwrite";
}
@PostMapping("/write")
public String reviewwrite(int roomid,Authentication authentication, @Valid ReviewDto reviewDto, BindingResult bindingResult,
		Model model,@RequestParam(value = "images") MultipartFile[] images) {
	if (bindingResult.hasErrors()) {
		System.out.println("에러");
		return "/review/reviewwrite";
	}
	try {
		Member mem = memberService.memgetInfo(authentication.getName());
		Room room = roomService.roomget(roomid).get();
		Review review = Review.createReview(reviewDto, mem, room);
		reviewService.write(review);
		for(MultipartFile file : images) {
			// 실제 파일 이름이 IE나 Edge는 전체 경로가 들어오므로
			String originalName = file.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			System.out.println("fileName: " + fileName);
			System.out.println(uploadPath);
			
			// 날짜 폴더 생성
			String folderPath = makeFolder();

			// UUID
			String uuid = UUID.randomUUID().toString();
			uuid=uuid.replace("-", "");
			System.out.println(uuid);
			
			// 저장할 파일 이름 중간에 "_"를 이용해서 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator +
			uuid +"_" + fileName;
			//String saveName = uploadPath + File.separator + folderPath + File.separator + "_" + fileName;
			Path savePath = Paths.get(saveName);
			System.out.println("forderPath :" + folderPath);
			System.out.println("path :" + savePath.toString());
			try {
				// 원본 파일 저장
				file.transferTo(savePath);
				Img img = Img.createImg(fileName,uuid,folderPath,review);
				imgService.add(img);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} // end for
	} catch (IllegalStateException e) {
		model.addAttribute("errorMessage", e.getMessage());
		return "/review/reviewwrite";
	}
	return "redirect:/mypage";
}

private String makeFolder() {

	String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

	String folderPath = str.replace("//", File.separator);

	// make folder --------
	File uploadPathFolder = new File(uploadPath, folderPath);

	if (uploadPathFolder.exists() == false) {
		uploadPathFolder.mkdirs();
	}
	return folderPath;
}
};

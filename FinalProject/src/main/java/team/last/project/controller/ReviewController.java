package team.last.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.last.project.dto.ReviewDto;
import team.last.project.entity.Img;
import team.last.project.entity.Member;
import team.last.project.entity.Reserve;
import team.last.project.entity.Review;
import team.last.project.entity.Room;
import team.last.project.service.ImgService;
import team.last.project.service.MemberService;
import team.last.project.service.ReserveService;
import team.last.project.service.ReviewService;

@Slf4j
@Controller
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {
	@Value("${upload.path}")
	private String uploadPath;
	private final MemberService memberService;
	private final ReviewService reviewService;
	private final ImgService imgService;
	private final ReserveService reserveService;

	@GetMapping("/write")
	public String reviewwriteform(Long resid, Authentication authentication, Model model) {
		Review review = reviewService.reviewByreserveId(resid);
		String name = memberService.memgetName(authentication.getName());
		if (review == null) {
			model.addAttribute("resid", resid);
			model.addAttribute("reviewDto", new ReviewDto());
			model.addAttribute("name", name);
			return "/review/reviewwrite";
		} else {
			model.addAttribute("name", name);
			model.addAttribute("review", review);
			return "/review/reviewupdate";
		}
	}

	@PostMapping("/write")
	public String reviewwrite(Long resid, Authentication authentication, @Valid ReviewDto reviewDto,
			BindingResult bindingResult, Model model, @RequestParam(value = "images") MultipartFile[] images) {
		if (bindingResult.hasErrors()) {
			return "/review/reviewwrite";
		}
		Reserve reserve = reserveService.get(resid);
		Member mem = memberService.memgetInfo(authentication.getName());
		Room room = reserve.getRoom();
		Review review = Review.createReview(reviewDto, mem, room, reserve);
		reviewService.write(review);
		if (images[0].getSize() != 0) {

			try {
				for (MultipartFile file : images) {
					// ?????? ?????? ????????? IE??? Edge??? ?????? ????????? ???????????????
					String originalName = file.getOriginalFilename();
					String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

					// ?????? ?????? ??????
					String folderPath = makeFolder();

					// UUID
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.replace("-", "");

					// ????????? ?????? ?????? ????????? "_"??? ???????????? ??????
					String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
					// String saveName = uploadPath + File.separator + folderPath + File.separator +
					// "_" + fileName;
					Path savePath = Paths.get(saveName);
					try {
						// ?????? ?????? ??????
						file.transferTo(savePath);
						Img img = Img.createImg(fileName, uuid, folderPath, review);
						imgService.add(img);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} // end for
			} catch (IllegalStateException e) {
				model.addAttribute("errorMessage", e.getMessage());
				return "/review/reviewwrite";
			}
		}
		return "redirect:/mypage";
	}

	@DeleteMapping("/delete")
	public String reviewDelete(@RequestParam(value = "del_id") int id) {
		List<Img> imglist = imgService.getimglist(id);
		for (Img img : imglist) {
			String imgPath = File.separator + img.getPath().replace("/", File.separator) + File.separator
					+ img.getUuid() + "_" + img.getName();

			File delfile = new File(uploadPath, imgPath);
			if (delfile != null) {
				delfile.delete();
			}
		}
		reviewService.reviewDelete(id);
		return "redirect:/mypage";
	}

	@PostMapping("/update")
	public String reviewupdate(int id, ReviewDto params, Model model,
			@RequestParam(value = "images") MultipartFile[] images) {
		reviewService.reviewUpdate(id, params);
		Review review = reviewService.reviewGet(id);
		if (images[0].getSize() != 0) {

			try {
				for (MultipartFile file : images) {
					// ?????? ?????? ????????? IE??? Edge??? ?????? ????????? ???????????????
					String originalName = file.getOriginalFilename();
					String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

					// ?????? ?????? ??????
					String folderPath = makeFolder();

					// UUID
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.replace("-", "");

					// ????????? ?????? ?????? ????????? "_"??? ???????????? ??????
					String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
					// String saveName = uploadPath + File.separator + folderPath + File.separator +
					// "_" + fileName;
					Path savePath = Paths.get(saveName);
					try {
						// ?????? ?????? ??????
						file.transferTo(savePath);
						Img img = Img.createImg(fileName, uuid, folderPath, review);
						imgService.add(img);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} // end for
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return "redirect:/mypage";
			}
		}
		return "redirect:/mypage";
	}
	// ????????? ????????? ??????????????? ??????
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/img") public String getImg(@RequestParam("review_id")
	 * Integer review_id) { Img img = imgService.getimg(review_id); String imgPath =
	 * File.separator +"image" + File.separator + img.getPath() + File.separator +
	 * img.getUuid() + "_" + img.getName(); return imgPath;
	 * 
	 * }
	 */

	// DB?????? review??? ?????? ????????? ????????? ??????
	@ResponseBody
	@RequestMapping("/imglist")
	public List<String> getImgList(@RequestParam("review_id") Integer review_id) {
		List<Img> imglist = imgService.getimglist(review_id);
		List<String> imgPathList = new ArrayList<>();
		for (Img img : imglist) {
			String imgPath = File.separator + "image" + File.separator + img.getPath() + File.separator + img.getUuid()
					+ "_" + img.getName();
			imgPathList.add(imgPath);
		}
		return imgPathList;

	}

	// DB?????? review??? ?????? ????????? id ?????? ??????
	@ResponseBody
	@RequestMapping("/imgidlist")
	public List<Map<String, Object>> getImgIdList(@RequestParam("review_id") Integer review_id) {
		List<Img> imglist = imgService.getimglist(review_id);
		List<Map<String, Object>> imgidList = new ArrayList<>();
		for (Img img : imglist) {
			Map<String, Object> imgmap = new HashMap<>();
			String imgPath = File.separator + "image" + File.separator + img.getPath() + File.separator + img.getUuid()
					+ "_" + img.getName();
			int imgid = img.getId();
			imgmap.put("id", imgid);
			imgmap.put("path", imgPath);
			imgidList.add(imgmap);
		}
		return imgidList;
	}

	@ResponseBody
	@RequestMapping("/imgdel")
	public String getImgdel(@RequestParam("imgid") Integer imgid) {
		Img img = imgService.getimg(imgid);
		String name = img.getName();
		if (img != null) {
			String imgPath = File.separator + img.getPath().replace("/", File.separator) + File.separator
					+ img.getUuid() + "_" + img.getName();

			imgService.imgdelete(img);
			File delfile = new File(uploadPath, imgPath);
			if (delfile != null) {
				delfile.delete();
			}
		}
		return name;

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

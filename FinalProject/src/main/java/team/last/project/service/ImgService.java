package team.last.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.last.project.entity.Img;
import team.last.project.repository.ImgRepository;

@Service
@RequiredArgsConstructor
public class ImgService {
	private final ImgRepository imgRepository;

	public void add(Img img) {
		imgRepository.save(img);
	}
	
	public Img getimg(int imgid) {
		return imgRepository.findById(imgid).orElse(null);
	}
	public List<Img> getimglist(int reviewid) {
		return imgRepository.getAllByReviewId(reviewid);
	}

	public void imgdelete(Img img) {
		imgRepository.delete(img);
	}
}

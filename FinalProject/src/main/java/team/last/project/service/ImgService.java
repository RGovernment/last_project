package team.last.project.service;

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
}

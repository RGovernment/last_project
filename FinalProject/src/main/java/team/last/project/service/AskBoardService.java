package team.last.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.AskBoardDto;
import team.last.project.entity.AskBoard;
import team.last.project.repository.AskBoardRepository;

@Service
@RequiredArgsConstructor
public class AskBoardService {
	private final AskBoardRepository askBoardRepository;

	public void write(AskBoard askBoard) {
		askBoardRepository.save(askBoard);
	}

	public Page<AskBoard> boardList(Pageable pageable) {
		return askBoardRepository.findAll(pageable);
	}
	
	public Page<AskBoard> boardListNoAnswer(Pageable pageable) {
		return askBoardRepository.findAll(pageable);
	}
	public Page<AskBoard> boardListAnswer(Pageable pageable) {
		return askBoardRepository.findAll(pageable);
	}


	public AskBoard boardView(Integer id) {
		return askBoardRepository.findById(id).get();
	}

	public void boardDelete(Integer id) {
		askBoardRepository.deleteById(id);
	}

	@Transactional
	public Integer boardUpdate(Integer id, final AskBoardDto params) {
		AskBoard askBoard = askBoardRepository.findById(id).orElseThrow();
		askBoard.update(params.getTitle(), params.getContent(), params.getAnswer());
		return id;
	}

	public Page<AskBoard> boardUserList(Long id, Pageable pageable) {
		return askBoardRepository.findALLByMemberId(id, pageable);
	}
}

package team.last.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.last.project.dto.BoardDto;
import team.last.project.entity.Board;
import team.last.project.repository.BoardRepository;


@Service
@RequiredArgsConstructor
public class BoardService {
private final BoardRepository boardRepository;
public void write(Board board) {
	boardRepository.save(board);
}
public Page<Board> boardList(Pageable pageable) {
	return boardRepository.findAll(pageable);
}
public Board boardView(Integer id) {
	return boardRepository.findById(id).get();
}
public void boardDelete(Integer id) {
	boardRepository.deleteById(id);
}
@Transactional
public Integer boardUpdate(Integer id, final BoardDto params) {
	Board board = boardRepository.findById(id).orElseThrow();
	board.update(params.getTitle(), params.getContent());
	return id;
}
public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
	return boardRepository.findByTitleContaining(searchKeyword, pageable);
}
}

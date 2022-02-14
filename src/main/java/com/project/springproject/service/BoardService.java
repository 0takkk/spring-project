package com.project.springproject.service;

import com.project.springproject.domain.Board;
import com.project.springproject.domain.Member;
import com.project.springproject.dto.BoardDto;
import com.project.springproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(BoardDto boardDto, Member loginMember){
        log.info("BoardService saveBoard");
        boardDto.setWriter(loginMember.getName());
        Board board = boardDto.toEntity();
        boardRepository.save(board);
        return board.getId();
    }

    public List<BoardDto> getBoardList(){
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreateDate())
                    .modifiedDate(board.getModifiedDate())
                    .build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public BoardDto getBoard(Long id){
        Board board = boardRepository.findById(id);

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent())
                .createdDate(board.getCreateDate())
                .modifiedDate(board.getModifiedDate())
                .build();

        return boardDto;
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }


}

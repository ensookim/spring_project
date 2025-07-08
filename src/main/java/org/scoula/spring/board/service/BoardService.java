package org.scoula.spring.board.service;

import org.scoula.spring.board.domain.BoardAttachmentVO;
import org.scoula.spring.board.dto.BoardDTO;
import org.scoula.spring.common.pagination.Page;
import org.scoula.spring.common.pagination.PageRequest;

import java.util.List;

public interface BoardService {

    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public BoardDTO create(BoardDTO board);
    public BoardDTO update(BoardDTO board);
    public BoardDTO delete(Long no);
    public BoardAttachmentVO getAttachment(Long no);
    public boolean deleteAttachment(Long no);
    Page<BoardDTO> getPage(PageRequest pageRequest);
}

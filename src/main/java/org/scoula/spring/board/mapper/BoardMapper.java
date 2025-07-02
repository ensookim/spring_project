package org.scoula.spring.board.mapper;

import org.mapstruct.Mapper;
import org.scoula.spring.board.domain.BoardAttachmentVO;
import org.scoula.spring.board.domain.BoardVO;

import java.util.List;

@Mapper
public interface BoardMapper {

//    @Select("select  * from tbl_board order by no desc")
    public List<BoardVO> getList();
    public BoardVO get(Long no);
    public void create(BoardVO board);
    public int update(BoardVO board);
    public int delete(Long no);
    public void createAttachment(BoardAttachmentVO attach);
    public List<BoardAttachmentVO> getAttachmentList(Long bno);
    public BoardAttachmentVO getAttachment(Long no);
    public int deleteAttachment(Long no);
}

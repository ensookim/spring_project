package org.scoula.spring.board.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.spring.board.domain.BoardAttachmentVO;
import org.scoula.spring.board.dto.BoardDTO;
import org.scoula.spring.board.service.BoardService;
import org.scoula.spring.common.pagination.Page;
import org.scoula.spring.common.pagination.PageRequest;
import org.scoula.spring.common.util.UploadFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Api(tags = "게시글 관리")
@Log4j2
public class BoardController {
    private final BoardService service;
//    @GetMapping("")
//    public List<BoardDTO> getList() {
//        return service.getList();
//    }
//
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })

    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList() {
        return ResponseEntity.ok(service.getList());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(@ApiParam(value = "게시글 ID", required = true, example = "1")
                                            @PathVariable Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    @ApiOperation(value = "게시글 생성", notes = "게시글 생성 API")
//    @PostMapping("")
//    public ResponseEntity<BoardDTO> create(@ApiParam(value = "게시글 객체", required = true)
//                                               @RequestBody BoardDTO board) {
//        return ResponseEntity.ok(service.create(board));
//    }
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(BoardDTO board) {
         return ResponseEntity.ok(service.create(board));
    }

//    @PutMapping("/{no}")
//    public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO board) {
//        return ResponseEntity.ok(service.update(board));
//    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long no, BoardDTO board) {
        return ResponseEntity.ok(service.update(board));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {
        return ResponseEntity.ok(service.delete(no));
    }


    @GetMapping("/download/{no}")
    public void download(@PathVariable Long no, HttpServletResponse response) throws Exception {
        BoardAttachmentVO attachment = service.getAttachment(no);
        File file = new File(attachment.getPath());
        UploadFiles.download(response, file, attachment.getFilename());
    }
    @DeleteMapping("/deleteAttachment/{no}")
    public ResponseEntity<Boolean> deleteAttachment(@PathVariable Long no) throws Exception {
        return ResponseEntity.ok(service.deleteAttachment(no));
    }

    @GetMapping("")
    public ResponseEntity<Page> getList(PageRequest pageRequest) {
        return ResponseEntity.ok(service.getPage(pageRequest));
    }
}

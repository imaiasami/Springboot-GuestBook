package com.example.guestbook.repository;

import com.example.guestbook.model.entity.GuestBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuestBookMapper {
    // 방명록 등록
    void addGuestBook(GuestBook guestBook);

    // 등록된 방명록 전체 목록
    List<GuestBook> findAllGuestBooks();

    // 방명록 읽기
    GuestBook findGuestBook(Long guestbook_id);

    // 방명록 수정
    void updateGuestBook(GuestBook guestBook);

    // 방명록 삭제
    void deleteGuestBook(Long guestbook_id);
}

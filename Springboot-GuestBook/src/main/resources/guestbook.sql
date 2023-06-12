// 테이블 및 시퀀스 생성 쿼리

create table member (
member_id varchar2(20) primary key,
password varchar2(20) not null,
name varchar2(50) not null
);

create table guestbook (
guestbook_id number primary key,
contents varchar2(4000) not null,
member_id varchar2(20) references member(member_id),
created_time date default sysdate
);

create sequence seq_guestbook;

commit;

select * from member;

select * from guestbook;

create table STATUSUSER.LS_DXHK (
        DXHKID NUMBER(10) NOT NULL,
        TDBH   CHAR(4 BYTE),
        DXID   VARCHAR2(100 BYTE) INLINE,
        PCH    VARCHAR2(100 BYTE) INLINE,
        ZT     CHAR(1 BYTE),
        CLJG   VARCHAR2(500 BYTE) NOT INLINE,
        YYSLSH VARCHAR2(100 BYTE) INLINE,
        FSBZ   CHAR(1 BYTE) NOT NULL DEFAULT '0',
        DLFLSH VARCHAR2(100 BYTE) INLINE,
        FSHM   VARCHAR2(11 BYTE) INLINE,
        DLID   NUMBER(10),
        FHMS   VARCHAR2(200 BYTE) NOT INLINE,
        FHSJ   VARCHAR2(14 BYTE) INLINE,
        FHRQ   VARCHAR2(8 BYTE) INLINE,
        TJSJ   TIMESTAMP(6) default sysdate not null)
AGING USE TJSJ LIFETIME 48 hours CYCLE 20 seconds ON;
        
alter table STATUSUSER.LS_DXHK add constraint PK_LS_DXHKID primary key
        (DXHKID);
        
-- Database is in Oracle type mode
create sequence STATUSUSER.SID_LS_DXHK
    increment by 1
    minvalue 1
    maxvalue 9999999999
    start with 2901
    cache 20
    cycle;
    create index STATUSUSER.LS_DXHK_DLID on STATUSUSER.LS_DXHK (DLID);
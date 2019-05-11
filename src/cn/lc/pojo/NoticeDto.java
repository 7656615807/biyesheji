package cn.lc.pojo;

/**
 * @description:
 * @author: lc
 * @date: 2019-05-10 01:28
 **/

public class NoticeDto {
    private Notice notice;
    private boolean isFirstRead;

    public NoticeDto(){
        this.isFirstRead = false;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public boolean isFirstRead() {
        return isFirstRead;
    }

    public void setFirstRead(boolean firstRead) {
        isFirstRead = firstRead;
    }
}

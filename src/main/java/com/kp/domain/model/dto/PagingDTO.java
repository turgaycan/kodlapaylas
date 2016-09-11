package com.kp.domain.model.dto;

import com.kp.domain.Article;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Created by turgaycan on 9/30/15.
 */
public class PagingDTO implements Serializable {

    private static final long serialVersionUID = 3723829320759260716L;
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 8;
    public static final int DEFAULT_PAGE_SLICE = 4;

    private int first;
    private int begin;
    private int current;
    private int end;
    private int totalPages;
    private int prev;
    private int next;

    public PagingDTO() {
    }

    public PagingDTO(int first, int begin, int prev, int current, int next, int end, int totalPages) {
        this.first = first;
        this.begin = begin;
        this.prev = prev;
        this.current = current;
        this.next = next;
        this.end = end;
        this.totalPages = totalPages;
    }

    public static PagingDTO buildPagingDTO(Page<Article> articlePages) {
        int current = articlePages.getNumber();
        int next = current > 1 ? current + 1 : 1;
        int prev = current > 1 ? current - 1 : 1;
        int begin = Math.max(1, current - DEFAULT_PAGE_SLICE);
        int end = Math.min(begin + DEFAULT_PAGE_SIZE, articlePages.getTotalPages());
        return new PagingDTO(DEFAULT_PAGE, begin, prev, current, next, end, articlePages.getTotalPages());
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}

package com.kp.domain.spec;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by turgaycan on 9/29/15.
 */
public class PageSpec {

    public PageSpec() {
    }

    public static Pageable buildPageSpecificationByFieldDesc(int pageIndex, int pageSize, String field) {
        Sort sortSpec = buildSortAndOrderDesc(field);
        return new PageRequest(pageIndex, pageSize, sortSpec);
    }

    public static Sort buildSortAndOrderDesc(String field) {
        return new Sort(new Sort.Order(Sort.Direction.DESC, field));
    }
}

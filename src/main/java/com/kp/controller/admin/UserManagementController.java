package com.kp.controller.admin;

import com.kp.controller.util.KpControllerUtil;
import com.kp.domain.User;
import com.kp.dto.UserUpdateInfo;
import com.kp.service.user.UserService;
import com.kp.util.KpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by tcan on 13/02/17.
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class UserManagementController {

    private static final int DEFAULT_PAGEINDEX = 1;
    private static final int DEFAULT_PAGESIZE = 10;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView list() {
        final List<User> users = userService.getAll();
        ModelAndView mav = new ModelAndView("/admin/users");
        mav.addObject("users", users);
        return mav;
    }

    @RequestMapping(value = {"/users/{pageIndex:\\d+$}/{pageSize:\\d+$}"}, method = RequestMethod.GET)
    public ModelAndView list(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {

        if (pageIndex <= 0) {
            pageIndex = DEFAULT_PAGEINDEX;
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGESIZE;
        }

        final Page<User> users = userService.findUsersAsPageable(pageIndex - 1, pageSize);
        ModelAndView mav = new ModelAndView("/admin/users");
        mav.addObject("users", users.getContent());
        return mav;
    }

    @RequestMapping(value = "/show/user/{id:\\d+$}", method = RequestMethod.GET)
    public ModelAndView showOne(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/user");
        final User user = userService.getOne(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public ModelAndView updateOne(@Valid @ModelAttribute("userUpdateInfo") UserUpdateInfo userUpdateInfo, BindingResult bindingResult) {
        final User persisted = userService.getOne(userUpdateInfo.getId());

        if (bindingResult.hasErrors()) {
            final ModelAndView mav = new ModelAndView("/admin/user");
            mav.addObject("user", persisted);
            return KpControllerUtil.buildErrorMav(bindingResult, mav);
        }

        persisted.setUserStatus(userUpdateInfo.getUserStatus());
        persisted.setWebsite(userUpdateInfo.getWebsite());
        persisted.setRole(userUpdateInfo.getRole());
        persisted.setFullname(userUpdateInfo.getFullname());

        userService.merge(persisted);

        final String showArticleUrl = "/show/user/" + userUpdateInfo.getId();
        return KpUtil.redirectToMAV(showArticleUrl);
    }
}

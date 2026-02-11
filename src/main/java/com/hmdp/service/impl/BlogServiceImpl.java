package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.entity.Blog;
import com.hmdp.entity.User;
import com.hmdp.mapper.BlogMapper;
import com.hmdp.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.service.IUserService;
import com.hmdp.utils.SystemConstants;
import com.hmdp.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Resource
    private IUserService userService;

    @Override
    public Result queryHotBlog(Integer current) {
        //根据用户查询
        Page<Blog> page = query()
                .orderByDesc("liked")
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));

        //获取当前页数据
        List<Blog> records = page.getRecords();
        //查询用户
        records.forEach(this::queryBlogUser);
        return Result.ok(records);
    }

    @Override
    public Result queryBlogById(Long id) {
        //1.查询blog
        Blog blog = getById(id);
        if (blog == null) {
            return Result.fail("用户不存在");
        }
        
        //2.查询blog有关用户
        queryBlogUser(blog);

        return Result.ok(blog);
    }

    @Override
    public Result likeBlog(Long id) {
        //1.获取登录用户
        Long userId = UserHolder.getUser().getId();

        //2.判断当前登录是否已经点赞


        //3.如果未点赞
        //数据库点赞+1

        //保存用户到redis的set集合

        //4.如果已点赞
        //数据库点赞数-1

        //把用户从redis的set集合移除

        return null;
    }

    private void queryBlogUser(Blog blog) {
        Long userID = blog.getUserId();
        User user = userService.getById(userID);
        blog.setName(user.getNickName());
        blog.setIcon(user.getIcon());
    }
}

package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommenService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommenService commenService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commenService.add(comment);
        return new Result(true, StatusCode.OK,"发表成功");
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Result dele(@RequestBody Comment comment){
        commenService.dele(comment);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    public Result findByArticleid(@PathVariable String article){
        return new Result(true,StatusCode.OK,"查询成功",commenService.findByArticleid(article));
    }
}

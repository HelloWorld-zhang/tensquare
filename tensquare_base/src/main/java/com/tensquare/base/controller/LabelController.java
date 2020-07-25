package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return  new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result add(@PathVariable Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  Result update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"插叙成功",labelService.findSearch(searchMap));
    }


    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap ,@PathVariable int page ,@PathVariable int size){
        Page<Label> pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }








}

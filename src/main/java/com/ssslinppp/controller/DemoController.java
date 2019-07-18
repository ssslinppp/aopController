package com.ssslinppp.controller;

import com.ssslinppp.model.ResultEntity;
import com.ssslinppp.model.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：<br/>
 * User: ssslinppp <br/>
 * Date: 2017/9/27 <br/>
 * Time: 19:25 <br/>
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/aop")
public class DemoController {

  /**
   * 测试无参请求
   *
   * @return
   */
  @GetMapping(value = "/noArgs")
  public ResultEntity noArgs() {
    UserVo userVo = new UserVo();
    userVo.setAge(23);
    userVo.setName("张三");

    return ResultEntity.success(userVo);
  }

  /**
   * 测试有参请求
   *
   * @param name
   * @param age
   * @return
   */
  @GetMapping(value = "/withArgs")
  public ResultEntity withArgs(String name, int age) {
    UserVo userVo = new UserVo();
    userVo.setName(name);
    userVo.setAge(age);

    return ResultEntity.success(userVo);
  }

  /**
   * 测试有参请求
   *
   * @param name
   * @param age
   * @return
   */
  @PostMapping(value = "/withArgsPost")
  public ResultEntity withArgsPost(String name, int age) {
    UserVo userVo = new UserVo();
    userVo.setName(name);
    userVo.setAge(age);

    return ResultEntity.success(userVo);
  }

  /**
   * 测试异常请求
   *
   * @return
   */
  @GetMapping(value = "/withException")
  public ResultEntity throwExceptioin() {
    throw new RuntimeException("发生了 RuntimeException异常");
  }
}

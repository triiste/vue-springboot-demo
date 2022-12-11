package com.example.demo.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            UserMapper userMapper;
    @Resource//引入mapper 但不规范 一般controller引入service service引入mapper
            GroupMapper groupMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/login")  //登录
    public Result<?> login(@RequestBody User user){//把前台json转换为java对象  //前台和数据库进行比对
//        System.out.println(user.getUserid());
//        System.out.println(user.getPassword());
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid,user.getUserid()).eq(User::getPassword,user.getPassword()).eq(User::getRole,user.getRole()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }

        return Result.success(res);
    }

    @GetMapping("/edit")  //登录
    public Result<?> find(@RequestParam String userjobid,@RequestParam String password0,@RequestParam String password1) {//把前台json转换为java对象
        //在Project表中查找本项目ID
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserid,userjobid).eq(User::getPassword,password0));
        if (res == null) {
            return Result.error("-1", "原密码错误！！！");
        }
        else{
            res.setPassword(password1);

            System.out.println(res.getPassword());
            userMapper.updateById(res);
            return Result.success();
        }
    }
    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //List<User> list = reader.readAll(User.class);
        //System.out.println(list);
               //     // 方式2：忽略表头的中文，直接读取表的内容
                    List<List<Object>> list = reader.read(1);
                    List<User> users = CollUtil.newArrayList();
                    for (List<Object> row : list) {
                        User user = new User();
                        user.setOfficeid(Integer.parseInt(row.get(0).toString()));//所属科室
                       user.setUserid(Integer.parseInt(row.get(1).toString()));//用户工号
//                        user.setUserjobid(row.get(2).toString());
                        user.setUsername(row.get(2).toString());//用户名
                        user.setTelephone(row.get(3).toString());//手机号
                        user.setAge(Integer.parseInt(row.get(4).toString()));//年龄
                        user.setSex(row.get(5).toString()); //性别
                        user.setAddress(row.get(6).toString());//地址
                        user.setPosition(row.get(7).toString());//职称
                        user.setMartialStatus(row.get(8).toString());//军衔
                        user.setPermission(row.get(9).toString());      //单位
                        user.setPassword("123456");
                        users.add(user);
                    }
                     userService.saveBatch(users);
        return true;
    }
    //导出可以
    //用户室内导出表格
    @GetMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Integer userid) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userMapper.findAll(userid);
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        自定义标题别名
        writer.setOnlyAlias(true);
        writer.addHeaderAlias("officeid", "所属科室");
        writer.addHeaderAlias("userid", "用户工号");
//        writer.addHeaderAlias("userjobid", "用户工号");
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("telephone", "手机号");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("position", "职称");
        writer.addHeaderAlias("MartialStatus", "军衔");
        writer.addHeaderAlias("permission", "单位");
        writer.write(list, true);

        for(int i=0;i<writer.getColumnCount();i++)
            writer.setColumnWidth(i,18);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    //相关图标数据统计导出表格
    @GetMapping("/exportStatistics")
    public void exportStatistics(HttpServletResponse response, @RequestParam Integer userid) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list;
        if(userid == 0){
           list = userMapper.findAlls();
        }else{
           list = userMapper.findAll(userid);
        }
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        自定义标题别名
        writer.setOnlyAlias(true);
        writer.addHeaderAlias("userid", "用户工号");
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("officeid", "所属室");
        writer.addHeaderAlias("projectmark", "项目积分和");
        writer.addHeaderAlias("mark", "研究室奖励积分");
        writer.addHeaderAlias("placeMark", "所领导奖励积分");
        writer.addHeaderAlias("totalMark", "积分总和");
        writer.addHeaderAlias("joincount", "参与项目个数");
        writer.addHeaderAlias("joinmark", "参与项目总积分");
        writer.addHeaderAlias("hostcount", "主持项目个数");
        writer.addHeaderAlias("hostmark", "主持项目总积分");
//        writer.autoSizeColumnAll();
        writer.write(list, true);
        //自动设置列宽
//        writer.autoSizeColumnAll();
//        SXSSFSheet sheet = (SXSSFSheet)writer.getSheet();
//        //上面需要强转SXSSFSheet  不然没有trackAllColumnsForAutoSizing方法
//        sheet.trackAllColumnsForAutoSizing();
        for(int i=0;i<writer.getColumnCount();i++)
        writer.setColumnWidth(i,18);
//        writer.setColumnWidth(1,180);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户项目积分统计", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        for(int i=0;i<ids.size();i++){
            int res=groupMapper.findcount(ids.get(i));
            if(res!=0){
                return false;
            }
        }
        return userService.removeByIds(ids);

    }
    @PostMapping //新增，赋初始密码
    public Result<?> save(@RequestBody User user){//把前台json转换为java对象
        if(user.getPassword() == null){
            user.setPassword("123456");
        }
        user.setTotalMark(0);
        user.setPlaceMark(0);
        user.setMark(0);
        user.setHostcount(0);
        user.setJoincount(0);
        user.setJoinmark(0);
        user.setHostmark(0);
        user.setRole(1);
        user.setProjectmark(0);
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping  //更新
    public Result<?> update(@RequestBody User user){//把前台json转换为java对象
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}") //通过id查询的接口
    public Result<?> delete(@PathVariable long id) {//把前台json转换为java对象

            //  删除之前判断此人是否有参与项目，直接在group表中搜索即可
            //如果有项目就提示不能删除，否则就提示删除成功
        int res=groupMapper.findcount(id);
        if(res != 0){
            return Result.error("-1", "此人还有项目，不能删除！！！");
        }
        userMapper.deleteById(id);


        return Result.success();

    }



    @GetMapping("/{id}") //通过id查询的接口
    public Result<?> getById(@PathVariable long id){//把前台json转换为java对象
       // System.out.println(id);
        return Result.success(userMapper.selectById(id));
    }

    @PostMapping("/register") //注册接口
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserjobid, user.getUserjobid()));
        if(res !=null){
            return Result.error("-1","用户名重复");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success(); //返回代码code为0
    }

        //新的加载页面

    //
    @GetMapping("/test")
    public Map<String, Object> findoffice(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "") String search,
                                          @RequestParam(defaultValue = "0") Integer officeid

    ) {
        pageNum = (pageNum -1) *pageSize;
        search = "%" + search + "%";
//        System.out.println("flag1");
        List<User> all,alls;
        alls=userMapper.findalls();
        //这是对全部数据进行刷新
        for(int i=0;i<alls.size();i++)
        {
//            System.out.println(alls.get(0).getUserid());

            int zhuchicount=userMapper.findsumhost(alls.get(i).getUserid());         //查找个人主持项目个数
            int suoyou=userMapper.findsumjoin(alls.get(i).getUserid());        //查找个人参与所有项目个数
            //个人项目积分和有问题不是每个人都有项目积分
//            System.out.println(userMapper.findpersontotal(alls.get(i).getUserid()));
            int zongjifen=0;//项目积分+奖励积分
            int jifen=0;//项目积分
            int zhuchijifen; //主持积分
//            int projectmark; //项目积分和
            int grouptotal =groupMapper.findcount(alls.get(i).getUserid());
            if(grouptotal ==0){
                zongjifen=alls.get(i).getMark()+alls.get(i).getPlaceMark();
            }else{
                zongjifen=alls.get(i).getMark()+alls.get(i).getPlaceMark();
                jifen=userMapper.findpersontotal(alls.get(i).getUserid());  //个人项目积分和

            }
             zongjifen+=jifen; //个人所有积分和
//            System.out.println("flag4");
            int jincanyu=suoyou-zhuchicount;//个人仅仅主持项目
            alls.get(i).setHostcount(zhuchicount);
            if(zhuchicount ==0 || grouptotal ==0){
                zhuchijifen=0;
            }else{
                zhuchijifen =userMapper.findzhuchi(alls.get(i).getUserid());
            }
            alls.get(i).setJoincount(jincanyu);
            alls.get(i).setTotalMark(zongjifen);
            //算出项目总分 为 jifen
            //算出主持的项目总分
            alls.get(i).setHostmark(zhuchijifen);
            // 光参与项目的总积分=算出项目总分-算出主持的项目总分
            alls.get(i).setJoinmark(jifen-zhuchijifen);
//            System.out.println("看看全所成员的总积分"+alls.size()+"此时积分情况"+jifen);
            alls.get(i).setProjectmark(jifen);
            //上面的只是取出来修改了但是并没有插进数据库中
            userMapper.updateById(alls.get(i));
        }
        //数据刷新之后再进行查询
        if(officeid !=0)
        {
            all = userMapper.findalluser(pageNum,pageSize,search,officeid);
        }
        else{
            all = userMapper.findalluser1(pageNum,pageSize,search);
        }
//        System.out.println("flag2");
        Integer total;
        if(officeid !=0)
        {
            total = userMapper.selectTotal(search,officeid);
        }
        else{
            total = userMapper.selectTotal1(search);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("data", all);
        res.put("total", total);
        return res;
    }
    @GetMapping //按用户名查找
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        //Page<User> userPage = userMapper.selectPage(new Page<>(pageNum,pageSize),Wrappers.<User>lambdaQuery().like(User::getNickName,search));
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getOfficeid, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}

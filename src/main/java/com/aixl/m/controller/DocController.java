package com.aixl.m.controller;


import com.aixl.m.model.aiDoc;
import com.aixl.m.service.aiDocService;
import com.aixl.m.utils.ReturnObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/docs")
public class DocController {

    @Autowired
    private aiDocService docService;


    /**
     * 创建一个医生账户
     * 参数为医生对象
     * 对象属性只包括id、pwd、type(可选)
     * @param string
     * @return
     */
    @RequestMapping(value = "/op/{json}",method = RequestMethod.POST)
    public ReturnObject<Object> addDoc(@PathVariable(value = "json") String string){
        aiDoc doc = JSON.parseObject(string,aiDoc.class);
       return docService.addDoc(doc);
    }

    /**
     * 设置医生信息
     * 参数为医生对象
     * 属性中不包含密码和类型
     * @param string
     * @return
     */
    @RequestMapping(value = "/op/{str}",method = RequestMethod.PUT)
    public ReturnObject<Object> setDocMsg(@PathVariable(value = "str") String string){
        return docService.setDocMsg(string);
    }

    /**
     * 删除一个医生账户
     * 参数为医生id号
     * @param string
     * @return
     */
    @RequestMapping(value = "/op/{str}",method = RequestMethod.DELETE)
    public ReturnObject<Object> removeDoc(@PathVariable(value = "str") String string){
        return docService.removeDoc(string);
    }


    /**
     * 重置医生密码
     * @param a
     * @return
     */
    @RequestMapping(value = "/password/{json}",method = RequestMethod.PUT)
    public ReturnObject<Object> reSetPassword(@PathVariable(value = "json")String a){
        aiDoc doc  = JSON.parseObject(a, aiDoc.class);
        return docService.reSetPw(doc);
    }
    /**
     * 设置医生密码
     * @param s     新密码
     * @param id    医生id
     * @return
     */
    @RequestMapping(value = "/password/{pwd}/{id}",method = RequestMethod.PUT)
    public ReturnObject<Object> setPassword(@PathVariable(value = "pwd") String s,
                                            @PathVariable(value = "id") String id){
        aiDoc doc= new aiDoc();
        doc.setAiDocPwd(s);
        doc.setAiDocId(id);


        return docService.setPwd(doc);
    }

    /**
     * 根据医生账号获取医生信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/docMsg/{id}",method = RequestMethod.GET)
    public ReturnObject<Object> getDocMsg(@PathVariable(value = "id") String id){
        return docService.getDocMsg(id);
    }

}

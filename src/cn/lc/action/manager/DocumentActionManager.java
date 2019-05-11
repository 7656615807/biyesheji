package cn.lc.action.manager;

import cn.lc.pojo.Doctype;
import cn.lc.pojo.Document;
import cn.lc.pojo.User;
import cn.lc.service.admin.IDoctypeServiceAdmin;
import cn.lc.service.admin.IDocumentServiceAdmin;
import cn.lc.service.manager.IDocumentServiceManager;
import cn.lc.util.action.AbstractAction;
import org.apache.struts2.convention.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lc
 * @date: 2019-04-06 11:16
 **/
@Repository
@InterceptorRef("managerStack")
@ParentPackage("root")
@Namespace(value="/")
@Results(value = {@Result(name = "document.list",location = "/pages/jsp/manager/manager_document_list.jsp"),
        @Result(name = "document.listByUser",location = "/pages/jsp/manager/manager_document_listByUser.jsp"),
        @Result(name = "document.insert",location = "/pages/jsp/manager/manager_document_insert.jsp"),
        @Result(name = "document.update",location = "/pages/jsp/manager/manager_document_update.jsp"),
        @Result(name = "updateVF",location = "/DocumentActionManager!list.action",type = "redirectAction"),
        @Result(name = "insertVF",location = "/DocumentActionManager!insertPre.action",type = "redirectAction")})
@Action("DocumentActionManager")
public class DocumentActionManager extends AbstractAction {
    public String updateRule = "document.title:string|";
    public String insertRule = "doctype.dtid:int|document.title:string";

    private Document document = new Document();

    public Document getDocument() {
        return document;
    }
    private Doctype doctype = new Doctype();

    public Doctype getDoctype() {
        return doctype;
    }

    @Resource
    private IDocumentServiceManager documentService;

    private File file ; //得到上传的文件
    private String fileContentType; //上传的文件类型
    private String oldFile;

    public void setOldFile(String oldFile) {
        this.oldFile = oldFile;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    private String ids;

    @Override
    public void setIds(String ids) {
        this.ids = ids;
    }

    public String insert(){
        User admin = (User) super.getSession().getAttribute("manager");
        this.document.setUser(admin);
        if (this.file == null) {
            document.setFile("");
        }else {
            document.setFile(super.createSingleFileName(this.fileContentType));
        }
        try {
            document.setDoctype(doctype);
            if (this.documentService.insert(document)) {
                String filePath = super.getApplication().getRealPath("/upload/document/") + document.getFile();
                if (this.file != null){
                    if (super.saveSingleFile(filePath, this.file)){
                        super.setMsgAndUrl("insert.success.msg", "manager_document.insertPre.action");
                    }else {
                        super.setMsgAndUrl("insert.failure.msg", "manager_document.insertPre.action");
                    }
                }
                super.setMsgAndUrl("insert.success.msg", "manager_document.insertPre.action");
            }else {
                super.setMsgAndUrl("insert.failure.msg","manager_document.insertPre.action");
            }
        }catch (Exception e){
            super.setMsgAndUrl("insert.failure.msg","manager_document.insertPre.action");
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String insertPre(){
        try {
            Map<String, Object> map = this.documentService.insertPre();
            super.getRequest().setAttribute("all",map.get("allDoctypes"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "document.insert";
    }

    public String delete(){
        Set<Integer> set = new HashSet<>();
        Set<String> files = new HashSet<>();
        String result[] = this.ids.split("\\,");
        for (int x = 0; x < result.length; x ++ ){
            String temp[] = result[x].split(":");
            set.add(Integer.parseInt(temp[0]));
            if (temp[1].length() > 0){
                files.add(temp[1]);
            }
        }
        User manager = (User) super.getSession().getAttribute("manager");
        if (manager == null){
            return "";
        }
        try {
            if (this.documentService.delete(set,manager.getUserid())) {
                if (files.size()>0){
                    String filePath = super.getApplication().getRealPath("/upload/document/") + document.getFile();
                    deleteFileBatch(filePath,files);
                }
                super.setMsgAndUrl("delete.success.msg", "manager_document.list.action");
            } else {
                super.setMsgAndUrl("delete.failure.msg", "manager_document.list.action");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public void updatePre(){
        try {
            Map<String, Object> map = this.documentService.updatePre(document.getDid());
            Document document = (Document) map.get("document");
            List<Doctype> docts = (List<Doctype>) map.get("allDoctypes");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("did",document.getDid());
            jsonObject.put("title",document.getTitle());
            jsonObject.put("content",document.getContent());
            jsonObject.put("file",document.getFile());
            jsonObject.put("dtid",document.getDoctype().getDtid());
            JSONArray array = new JSONArray();
            for (Doctype doct : docts){
                JSONObject temp = new JSONObject();
                temp.put("dtid",doct.getDtid());
                temp.put("title",doct.getTitle());
                array.put(temp);
            }
            jsonObject.put("doctypes",array);
            super.print(jsonObject);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String update(){
        if (this.file != null) {
            document.setFile(super.createSingleFileName(this.fileContentType));
        }else {
            document.setFile(this.oldFile);
        }
        User manager = (User) super.getSession().getAttribute("manager");
        if (manager == null){
            return "";
        }
        try {
            document.setDoctype(doctype);
            if (this.documentService.update(document,manager.getUserid())) {
                String filePath = super.getApplication().getRealPath("/upload/document/") + document.getFile();
                if (this.file != null){
                    if (super.saveSingleFile(filePath, this.file)){
                        super.setMsgAndUrl("update.success.msg", "manager_document.list.action");
                    }else {
                        super.setMsgAndUrl("update.failure.msg", "manager_document.list.action");
                    }
                }
                super.setMsgAndUrl("update.success.msg", "manager_document.list.action");
            }else {
                super.setMsgAndUrl("update.failure.msg","manager_document.list.action");
            }
        }catch (Exception e){
            super.setMsgAndUrl("update.failure.msg","manager_document.list.action");
            e.printStackTrace();
        }
        return "forward.page";
    }

    public String list(){
        try{
            Map<String, Object> map = this.documentService.list(super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("documentCount"),"manager.role.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("allDocuments"));
            super.getRequest().setAttribute("allDoctypes",map.get("allDoctypes"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "document.list";
    }

    public String listByUser(){
        User manager = (User) super.getSession().getAttribute("manager");
        if (manager == null){
            return "";
        }
        try{
            Map<String, Object> map = this.documentService.listByUser(manager.getUserid(),super.getCp(),super.getLs(),super.getCol(),super.getKw());
            super.handleSplit(map.get("documentCount"),"manager.role.split.url",null,null);
            super.getRequest().setAttribute("all",map.get("allDocuments"));
            super.getRequest().setAttribute("allDoctypes",map.get("allDoctypes"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "document.listByUser";
    }
    public void show(){
        try{
            Document document = this.documentService.show(this.document.getDid());
            if (document != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("did",document.getDid());
                jsonObject.put("title",document.getTitle());
                jsonObject.put("content",document.getContent());
                jsonObject.put("file",document.getFile());
                jsonObject.put("doct",document.getDoctype());
                jsonObject.put("user",document.getUser());
                jsonObject.put("pubDate",document.getPubdate());
                super.print(jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getDefaultColumn() {
        return "title";
    }

    @Override
    public String getColumnData() {
        return "title";
    }

    @Override
    public String getTypeName() {
        return "文档";
    }
}

package cn.lc.util.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Set;
import java.util.UUID;

/**
 * @description:
 * @author: lc
 * @date: 2019-01-14 08:13
 **/

public abstract class AbstractAction extends ActionSupport {
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    private Integer cp = 1;
    private Integer ls = 5;
    private String col;
    private String kw;

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public void setLs(Integer ls) {
        this.ls = ls;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public Integer getCp() {
        return cp;
    }

    public Integer getLs() {
        return ls;
    }

    public String getCol() {
        if (col == null || "".equals(col)){
            return getDefaultColumn();
        }
        return col;
    }



    /**
     * 用来取的默认的模糊查询列
     * @return
     */
    public abstract String getDefaultColumn();

    /**
     *  设置有可能进行模糊查询的字段
     * @return
     */
    public abstract String getColumnData();

    public String getKw() {
        if (kw == null){
            return "";
        }
        return kw;
    }

    /**
     * 根据文件的类型创建文件的名字
     * @param contentType
     * @return
     */
    public String createSingleFileName(String contentType){
        String fileExt = null;
        if ("image/bmp".equalsIgnoreCase(contentType)){
            fileExt = "bmp";
        }
        else if ("image/jpg".equalsIgnoreCase(contentType)){
            fileExt = "jpg";
        }
        else if ("image/jpeg".equalsIgnoreCase(contentType)){
            fileExt = "jpg";
        }
        else if ("image/gif".equalsIgnoreCase(contentType)){
            fileExt = "gif";
        }
        else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equalsIgnoreCase(contentType)){
            fileExt = "xlsx";
        }
        else if ("application/vnd.ms-excel".equalsIgnoreCase(contentType)){
            fileExt = "xls";
        }
        else if ("application/msword".equalsIgnoreCase(contentType)){
            fileExt = "doc";
        }
        else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equalsIgnoreCase(contentType)){
            fileExt = "docx";
        }
        else if ("text/plain".equalsIgnoreCase(contentType)){
            fileExt = "txt";
        }
        else if ("application/x-rar-compressed".equalsIgnoreCase(contentType)){
            fileExt = "rar";
        }
        else if ("application/zip".equalsIgnoreCase(contentType)){
            fileExt = "zip";
        }
        return UUID.randomUUID().toString() +"."+fileExt;
    }

    /**
     * 负责跳转路径读取
     * @param key
     * @return
     */
    public String getUrl(String key){
        return super.getText(key);
    }

    /**
     * 负责信息的操作
     * @param key
     * @return
     */
    public String getMsg(String key){
        return super.getText(key,new String[]{this.getTypeName()});
    }
    /**
    * 取得操作类型信息，主要用于跟新的提示，逼图如果公告则输出，公告信息
    */
    public abstract String getTypeName();

    /**
     * 取得HttpServletRequest
     * @return
     */
    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }

    /**
     * HttpServletResponse
     * @return
     */
    public HttpServletResponse getResponse(){
        return ServletActionContext.getResponse();
    }

    /**
     * 取得session对象
     * @return
     */
    public HttpSession getSession(){
        return this.getRequest().getSession();
    }

    /**
     * 取得Application对象
     * @return
     */
    public ServletContext getApplication(){
        return this.getRequest().getServletContext();
    }

    /**
     * 每个action的业务执行完毕之后传递给forward.jsp的信息
     * @param msgKey
     * @param urlKey
     */
    public void setMsgAndUrl(String msgKey,String urlKey){
        this.getRequest().setAttribute("msg",this.getMsg(msgKey));
        this.getRequest().setAttribute("url",this.getUrl(urlKey));
    }

    /**
     * 文件的保存操作
     * @param filePath  文件的路径
     * @param file  要保存的文件信息
     * @return  保存成功返回true 否则返回false
     */
    public boolean saveSingleFile(String filePath, File file){
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdirs();
        }
        boolean flag = false ;
        OutputStream outputStream = null;
        InputStream inputStream = null ;
        try {
            outputStream = new FileOutputStream(saveFile);
            inputStream = new FileInputStream(file);
            byte data[] =new byte[1024];
            int len = 0 ;
            while ((len = inputStream.read(data)) != -1){
                outputStream.write(data,0,len);
            }
            flag = true ;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 处理分页传递到组件的操作属性
     * @param allRecorders  当前所在的页面
     * @param urlKey    要进行分页处理的url，通过Pages.properties读取
     * @param paramName 参数名称
     * @param paramValue    参数内容
     */
    public void handleSplit(Object allRecorders, String urlKey, String paramName, String paramValue){
        this.getRequest().setAttribute("currentPage", this.getCp());
        this.getRequest().setAttribute("lineSize", this.getLs());
        this.getRequest().setAttribute("column", this.getCol());
        this.getRequest().setAttribute("keyWord", this.getKw());
        this.getRequest().setAttribute("url", this.getUrl(urlKey));
        this.getRequest().setAttribute("allRecorders", ((Integer)allRecorders+this.getLs()-1)/this.getLs());
        this.getRequest().setAttribute("columnData", this.getColumnData());
        this.getRequest().setAttribute("paramValue",paramName);
        this.getRequest().setAttribute("paramValue",paramValue);
    }

    /**
     * 用来回应操作
     */
    public void print(Object object){
        try {
            getResponse().getWriter().print(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 用来回应操作
     */
    public void printJson(Object object){
        try {
            getResponse().getWriter().print(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFile(String filePath){
        File dfile = new File(filePath);
        if (dfile.exists()){
            return dfile.delete();
        }
        return false;
    }
    public void deleteFileBatch(String filePath, Set<String> fileneme){
        for (String name : fileneme){
            deleteFile(filePath+name);
        }
    }
}

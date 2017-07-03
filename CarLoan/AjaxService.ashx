<%@ WebHandler Language="C#" Class="AjaxService" %>

using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using System.Reflection;
using System.IO;
using System.Security.Permissions;
using System.Text;
using System.Web.Script.Serialization;

using BLL;

using Spire.Doc;
using Spire.Doc.Documents;

public class AjaxService : IHttpHandler
{

    private HttpContext _context = null;
    public void ProcessRequest(HttpContext context)
    {

        _context = context;
        String methodName = context.Request["method"];
        Type type = this.GetType();
        System.Reflection.MethodInfo method = type.GetMethod(methodName);
        if (method == null) throw new Exception("method is null");

        try
        {
            BeforeInvoke(methodName);
            method.Invoke(this, null);
        }
        catch (Exception ex)
        {
            Hashtable result = new System.Collections.Hashtable();
            result["error"] = -1;
            result["message"] = ex.Message;
            JavaScriptSerializer jss = new JavaScriptSerializer();
            string json = jss.Serialize(result);
            context.Response.Clear();
            context.Response.Write(json);            
        }
        finally
        {
            AfterInvoke(methodName);
        }
    }

    public bool IsReusable
    {
        get
        {
            return false;
        }
    }
    
    //权限管理
    protected void BeforeInvoke(String methodName)
    {
        //Hashtable user = GetUser();
        //if (user.role == "admin" && methodName == "remove") throw .      
    }

    //日志管理
    protected void AfterInvoke(String methodName)
    {
    }

    protected void ResponseOk(string success, string message)
    {
        Hashtable result = new Hashtable();
        result["success"] = success;
        result["message"] = message;
        JavaScriptSerializer jss = new JavaScriptSerializer();
        string json = jss.Serialize(result);
        _context.Response.Write(json);
    }
    
    public void PageWork()
    {        
        string sql = _context.Request["sql"];
        int page = int.Parse(_context.Request["pageSize"]);
        int offset = int.Parse(_context.Request["offset"]);
        string if_desc = _context.Request["if_desc"];
        
        
        CommBll bll = new CommBll();
        string[] sql_a = sql.Split(new string[] { "from" }, StringSplitOptions.RemoveEmptyEntries);
        string sql2 = "select count(*) from " + sql_a[1];
        int all_num = bll.ExecuteScalar("select count(*) from " + sql_a[1]);
        string sql1 = "";
        if (sql.Contains("a.id") || sql.Contains("a.*"))
        {
            if(if_desc==null || if_desc.Length==0)
                sql1 = "select top " + page.ToString() + " * from (select ROW_NUMBER() over (order by a.id) row_num," +
                sql + ") as tmp_db where row_num>" + offset.ToString();
            else
                sql1 = "select top " + page.ToString() + " * from (select ROW_NUMBER() over (order by a.id desc) row_num," +
                sql + ") as tmp_db where row_num>" + offset.ToString();
        }
        else
        {
            if (if_desc == null || if_desc.Length == 0)            
                sql1 = "select top " + page.ToString() + " * from (select ROW_NUMBER() over (order by id) row_num," +
                sql + ") as tmp_db where row_num>" + offset.ToString();
            else
                sql1 = "select top " + page.ToString() + " * from (select ROW_NUMBER() over (order by id desc) row_num," +
                sql + ") as tmp_db where row_num>" + offset.ToString();
        }
        Hashtable result = bll.SelectHashTable(sql1);
        result["total"] = all_num;

        JavaScriptSerializer jss = new JavaScriptSerializer();
        string json = jss.Serialize(result);
        _context.Response.Write(json);
    }
    
    public void UserLogin()
    {
        string login_name = _context.Request["login_name"];
        string user_pass = _context.Request["user_pass"];

        CommBll bll = new CommBll();
        DataTable dt = bll.SelectTable("select * from t_user left join t_role on t_user.role_id = t_role.id where login_name='" + login_name + "' and user_pass='" + user_pass + "'");
        if (dt.Rows.Count > 0)
        {
            string a = dt.Rows[0]["role_function"].ToString();
            string b = dt.Rows[0]["id"].ToString();
            string c = dt.Rows[0]["user_name"].ToString();
            string d = dt.Rows[0]["role_id"].ToString();
            ResponseOk("YES", a + "," + b + "," + c + "," + d);
        }
        else
            ResponseOk("NO", "A");
    }

    public void SearchTable()
    {
        string json = null;
        Hashtable result = new CommBll().SelectHashTable("select * from " + _context.Request["table_name"]);
        JavaScriptSerializer jss = new JavaScriptSerializer();

        if (_context.Request["type"] == "combobox")
            json = jss.Serialize(result["rows"]);
        else
            json = jss.Serialize(result);
        _context.Response.Write(json);
    }

    public void UpdateTable()
    {
       CommBll bll = new CommBll();

        string if_exist_filed = _context.Request["if_exist_filed"];
        if (if_exist_filed != null)
        {
            DataTable dt = bll.SelectTable("select id from " + _context.Request["table_name"] + " where " + if_exist_filed + "='" + _context.Request.Form[if_exist_filed] + "'");
            if (dt.Rows.Count > 0)
            {
                if (dt.Rows[0]["id"].ToString() != _context.Request["id"])
                {
                    Hashtable result = new Hashtable();
                    result["isError"] = true;
                    result["message"] = "账号已存在,修改账号无效";
                    result["id"] = _context.Request["id"];
                    JavaScriptSerializer jss = new JavaScriptSerializer();
                    string json = jss.Serialize(result);
                    _context.Response.Clear();
                    _context.Response.Write(json);
                    return;
                }
            }
        }

        Hashtable args = new Hashtable();
        foreach (string s in _context.Request.Form.AllKeys)
        {
            args[s] = _context.Request.Form[s];
        }
        args["id"] = _context.Request["id"];
        bll.UpdateTableRow(args, _context.Request["table_name"]);
        ResponseOk("YES", "A");
    }

    public void InsertTable()
    {
       CommBll bll = new CommBll();

        string if_exist_filed = _context.Request["if_exist_filed"];
        if (if_exist_filed != null)
        {
            DataTable dt = bll.SelectTable("select id from " + _context.Request["table_name"] + " where " + if_exist_filed + "='" + _context.Request.Form[if_exist_filed] + "'");
            if (dt.Rows.Count > 0)
            {
                Hashtable result = new Hashtable();
                result["isError"] = true;
                result["message"] = "账号已存在";
                JavaScriptSerializer jss = new JavaScriptSerializer();
                string json = jss.Serialize(result);
                _context.Response.Clear();
                _context.Response.Write(json);
                return;
            }
        }

        Hashtable args = new Hashtable();
        foreach (string s in _context.Request.Form.AllKeys)
        {
            args[s] = _context.Request.Form[s];
        }
        bll.InsertTableRow(args, _context.Request["table_name"]);
        ResponseOk("YES", "A");
    }
    
    public void InsertTableId()
    {
        CommBll bll = new CommBll();
        Hashtable args = new Hashtable();
        foreach (string s in _context.Request.Form.AllKeys)
        {
            args[s] = _context.Request.Form[s];
        }
        bll.InsertTableRow(args, _context.Request["table_name"]);
        int tmp_id = bll.ExecuteScalar("SELECT IDENT_CURRENT('" + _context.Request["table_name"] + "')");
        ResponseOk("YES", tmp_id.ToString());
    }
    
    public void DeleteTable()
    {
        new CommBll().DeleteTable(_context.Request["id"], _context.Request["table_name"]);
        ResponseOk("YES", "A");
    }

    public void SearchTableBySQL()
    {
        string sql = _context.Request["sql"];
        Hashtable result = new CommBll().SelectHashTable(sql);
        string json = null;
        JavaScriptSerializer jss = new JavaScriptSerializer();
        if (_context.Request["type"] == "combobox")
            json = jss.Serialize(result["rows"]);
        else
            json = jss.Serialize(result);
        _context.Response.Write(json);
    }

    public void UpdateTableBySQL()
    {
        string sql = _context.Request["sql"];
        new CommBll().ExecuteSql(sql);
        ResponseOk("YES", "OK");
    }

    public bool CheckValidExt(string strExt)
    {
        //string AllowExt = "bmp|gif|jpeg|jpg|png";
        string AllowExt = "jpg";
        bool flag = false;
        string[] arrExt = AllowExt.Split('|');
        foreach (string filetype in arrExt)
        {
            if (filetype.ToLower() == strExt.ToLower().Replace(".", ""))
            {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /*
    public void DeletePhoto()
    {
        try
        {
            string fileName = System.Web.HttpContext.Current.Server.MapPath(_context.Request["file_name"]);

            if (File.Exists(fileName))
            {
                File.Delete(fileName);
                ResponseOk("YES", "OK");
            }
            else
            {
                ResponseOk("NO", "文件不存在");
            }
        }
        catch
        {
            ResponseOk("NO", "删除失败");
        }
    }*/
    /*
    public void UpLoadImage()
    {
        int FileMaxSize = 30000;
        HttpPostedFile fileUpload = _context.Request.Files[0];
        if (fileUpload != null)
        {
            try
            {
                string fileName = System.Web.HttpContext.Current.Server.MapPath(_context.Request["file_name"]);
                string fileExtension = fileUpload.FileName.Substring(fileUpload.FileName.LastIndexOf('.'));                
                if (fileUpload.ContentLength > FileMaxSize * 1024)
                {
                    ResponseOk("NO", "上传的文件超过最大限制");
                    return;
                }
                fileUpload.SaveAs(fileName);
                ResponseOk("YES", fileName);
            }
            catch (Exception)
            {
                ResponseOk("NO", "上传失败");
                _context.Response.Clear();
            }
        }
        else
        {            
            ResponseOk("NO", "上传失败");
        }

    }
    */

    public void GetCarPhotoName()
    {
        string case_id = _context.Request["case_id"];
        string file_path = _context.Server.MapPath("photo/car/");
        string[] tmp_files = Directory.GetFiles(file_path, "*.jpg");

        string tmp_name = "";
        foreach (string f1 in tmp_files)
        {
            if (f1.IndexOf(case_id + "_") >= 0)
            {
                string f2 = f1.Substring(f1.LastIndexOf('\\') + 1); 
                tmp_name += f2 + ",";
            }
        }
        tmp_name = tmp_name.TrimEnd(',');
        ResponseOk("YES", tmp_name);
    }

    public void GetDcyPhotoName()
    {
        string case_id = _context.Request["case_id"];
        string file_path = _context.Server.MapPath("photo/dcy/");
        string[] tmp_files = Directory.GetFiles(file_path, "*.jpg");

        string tmp_name = "";
        foreach (string f1 in tmp_files)
        {
            if (f1.IndexOf(case_id + "_") >= 0)
            {
                string f2 = f1.Substring(f1.LastIndexOf('\\') + 1);
                tmp_name += f2 + ",";
            }
        }
        tmp_name = tmp_name.TrimEnd(',');
        ResponseOk("YES", tmp_name);
    }
    
    public void bUpLoadImage()
    {        
        HttpPostedFile fileUpload = _context.Request.Files[0];
        if (fileUpload != null)
        {
            try
            {
                //string fileName = System.Web.HttpContext.Current.Server.MapPath(_context.Request["file_name"]);
                string fileName = _context.Server.MapPath(_context.Request["file_name"]);
                fileUpload.SaveAs(fileName);
                ResponseOk("YES", fileName);
            }
            catch (Exception)
            {
                ResponseOk("NO", "上传失败");
                _context.Response.Clear();
            }
        }
        else
        {            
            ResponseOk("NO", "上传失败");
        }

    }

    public void FileExisted()
    {
        string file_name = HttpUtility.UrlDecode(_context.Request["file_name"]);
        string path = _context.Server.MapPath(file_name);
        if (File.Exists(path))
        {
            ResponseOk("YES", "A");
        }
        else
        {
            ResponseOk("NO", "A");
        }
    }

    public void DeleteFile()
    {
        try
        {
            string fileName = _context.Server.MapPath(_context.Request["file_name"]);

            if (File.Exists(fileName))
            {
                File.Delete(fileName);
                ResponseOk("YES", "OK");
            }
            else
            {
                ResponseOk("NO", "文件不存在");
            }
        }
        catch
        {
            ResponseOk("NO", "删除失败");
        }
    }

    public void SaveFile()
    {
        string data = _context.Request["data"];
        string fileName = _context.Server.MapPath("./txt/111.txt");
        StreamWriter f = File.CreateText(fileName);
        f.Write(data);
        f.Close();
        ResponseOk("YES", "A");
    }

    public void PreviewReport()
    {
        string case_id = _context.Request["case_id"];
        string UploadDir = "doc/";
        string path = System.Web.HttpContext.Current.Server.MapPath(UploadDir);
        string doc_name = path + "car.doc";
        MakeReport mr = new MakeReport();
        Document doc = mr.GetDocument(case_id, doc_name);
        string html_name = path + "html\\" + case_id + ".html";
        doc.SaveToFile(html_name, FileFormat.Html);
        ResponseOk("YES", "A");
    }
}
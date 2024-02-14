package src.com.kk.furns.web;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.entity.Page;
import src.com.kk.furns.service.FurnService;
import src.com.kk.furns.service.impl.FurnServiceImpl;
import src.com.kk.furns.utils.DataUtils;
import src.com.kk.furns.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author KK
 * @version 1.0
 */
public class FurnServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 列出所有家居服务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Furn> furns = furnService.list();
        req.setAttribute("furns", furns);
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }

    /**
     * 添加一个家居服务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 校验数据
        String name = req.getParameter("name");
        String maker = req.getParameter("maker");
        try {
            Double.parseDouble(req.getParameter("price"));
            Integer.parseInt(req.getParameter("sales"));
            Integer.parseInt(req.getParameter("inventory"));
        } catch (Exception e) {
            req.setAttribute("msg", "数据格式异常！请重新检查");
            req.setAttribute("name", name);
            req.setAttribute("maker", maker);
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Integer sales = new Integer(req.getParameter("sales"));
        Integer inventory = new Integer(req.getParameter("inventory"));
        if ("".equals(name) || "".equals(maker)) {
            req.setAttribute("msg", "家居名或厂商不可为空！");
            req.setAttribute("name", name);
            req.setAttribute("maker", maker);
            req.setAttribute("price", price);
            req.setAttribute("sales", sales);
            req.setAttribute("inventory", inventory);
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }

        // 校验通过
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.add(furn);
        resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 删除家居服务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        furnService.deleteFurnById(id);
        req.setAttribute("pageNo", req.getParameter("pageNo"));
        resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        req.setAttribute("selectedFurn", furn);
        req.setAttribute("pageNo", req.getParameter("pageNo"));
        req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req, resp);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        if (null == furn) {
            return;
        }
        //1. 判断是不是文件表单(enctype="multipart/form-data")
        if (!ServletFileUpload.isMultipartContent(req)) {
            return;
        }
        //2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //3. 创建一个解析上传数据的工具对象
        ServletFileUpload servletFileUpload =
                new ServletFileUpload(diskFileItemFactory);
        //解决接收到文件名是中文乱码问题
        servletFileUpload.setHeaderEncoding("utf-8");

        //4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
        //   将其封装到 FileItem 文件项中
        //   老师的编程心得体会: 如果我们不知道一个对象是什么结构[1.输出 2.debug 3. 底层自动看到]
        try {
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : list) {
                //判断是不是一个文件
                if (fileItem.isFormField()) {//如果是 true 就是文本 input text
                    if ("name".equals(fileItem.getFieldName())) {
                        furn.setName(fileItem.getString("utf-8"));
                    } else if ("maker".equals(fileItem.getFieldName())) {
                        furn.setMaker(fileItem.getString("utf-8"));
                    } else if ("price".equals(fileItem.getFieldName())) {
                        furn.setPrice(new BigDecimal(fileItem.getString("utf-8")));
                    } else if ("sales".equals(fileItem.getFieldName())) {
                        furn.setSales(DataUtils.parseInt(fileItem.getString("utf-8"), 0));
                    } else if ("inventory".equals(fileItem.getFieldName())) {
                        furn.setInventory(DataUtils.parseInt(fileItem.getString("utf-8"), 0));
                    }
                } else {//是一个文件

                    //用一个方法
                    //获取上传的文件的名字
                    String name = fileItem.getName();
                    if ("".equals(name)) {
                        continue;
                    }
                    //1.指定一个目录 , 就是我们网站工作目录下
                    String filePath = "/" + DataUtils.FURN_IMG_DIRECTORY;
                    //2. 获取到完整目录 [io/servlet基础]
                    String fileRealPath =
                            req.getServletContext().getRealPath(filePath);

                    //3. 创建这个上传的目录
                    File fileRealPathDirectory = new File(fileRealPath + WebUtils.getYearMonthDay());
                    if (!fileRealPathDirectory.exists()) {//不存在，就创建
                        fileRealPathDirectory.mkdirs();//创建
                    }

                    //4. 将文件拷贝到 fileRealPathDirectory 目录
                    //   构建一个上传文件的完整路径 ：目录+文件名
                    //   对上传的文件名进行处理, 前面增加一个前缀，保证是唯一即可
                    name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
                    String fileFullPath = fileRealPathDirectory + "/" + name;
                    fileItem.write(new File(fileFullPath));
                    fileItem.getOutputStream().close();
//                    String prevPicPath = "../../../../../../.." + req.getContextPath() + "/" + furn.getPicture();
//                    System.out.println("prevPicPath " + prevPicPath);
                    furn.setPicture(DataUtils.FURN_IMG_DIRECTORY + WebUtils.getYearMonthDay() + "/" + name);
//                    System.out.println(new File(prevPicPath).delete());
                }
            }
            furnService.updateFurn(furn);
            req.setAttribute("pageNo", req.getParameter("pageNo"));
            req.getRequestDispatcher("/views/manage/update_ok.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furn> page = furnService.page(pageNo, pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }
}

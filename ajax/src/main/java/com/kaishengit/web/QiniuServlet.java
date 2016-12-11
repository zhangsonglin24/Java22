package com.kaishengit.web;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/qiniu")
public class QiniuServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ak = "Qdykxy_oSSH7uAKzHUV3zxO-JLt0vJrAAKSA0s_e";
        String sk = "nlCaHUgA2gjW0gyfe9MksawqA211IXZpWxHHFl_K";
        String bucketName = "zhang24";


        Auth auth = Auth.create(ak,sk);
        //计算上传文件的Token
        StringMap map = new StringMap();
        map.put("returnUrl","http://localhost:8080/qiniucallback");

        String token = auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("qiniu.jsp").forward(req,resp);


    }
}

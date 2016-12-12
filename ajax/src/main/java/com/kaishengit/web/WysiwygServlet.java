package com.kaishengit.web;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wysiwyg")
public class WysiwygServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "Qdykxy_oSSH7uAKzHUV3zxO-JLt0vJrAAKSA0s_e";
        String sk = "nlCaHUgA2gjW0gyfe9MksawqA211IXZpWxHHFl_K";
        String bucketName = "zhang24";

        Auth auth = Auth.create(ak,sk);

        String returnBody = "{\"success\":true,\"file_path\":\"http://oi0mgf04v.bkt.clouddn.com/${key}\"}";


        StringMap map = new StringMap();
        map.put("returnBody",returnBody);

        String token = auth.uploadToken(bucketName,null,3600,map);

        req.setAttribute("token",token);
        req.getRequestDispatcher("wysiwyg.jsp").forward(req,resp);

    }
}

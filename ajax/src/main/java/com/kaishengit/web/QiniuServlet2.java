package com.kaishengit.web;

import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/qiniu2")
public class QiniuServlet2 extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ak = "Qdykxy_oSSH7uAKzHUV3zxO-JLt0vJrAAKSA0s_e";
        String sk = "nlCaHUgA2gjW0gyfe9MksawqA211IXZpWxHHFl_K";
        String bucketName = "zhang24";

        Auth auth = Auth.create(ak,sk);

        String token = auth.uploadToken(bucketName);

        req.setAttribute("uid",1001);
        req.setAttribute("token",token);
        req.getRequestDispatcher("uploader_qiniu.jsp").forward(req,resp);

    }
}

package com.spring.web.mvc.captcha;

import com.spring.util.Constants;
import com.spring.web.util.CaptchaUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Viet.Pham on 8/3/2016.
 */
public class CaptchaGenServlet extends HttpServlet{
    public static final String FILE_TYPE = "jpeg";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    private void genCaptcha(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);

        String captchaStr = "";

        captchaStr = CaptchaUtils.generateCaptchaMethod2(6);

        try{
            int width = 100;
            int height = 40;

            Color bg = new Color(0, 255, 255);
            Color fg = new Color(0, 100, 0);

            Font font = new Font("Arial", Font.BOLD, 20);
            BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
            Graphics g = cpimg.createGraphics();

            g.setFont(font);
            g.setColor(bg);
            g.fillRect(0 , 0, width, height);
            g.setColor(fg);
            g.drawString(captchaStr, 10, 25);

            HttpSession session = request.getSession(true);
            session.setAttribute(Constants.CAPTCHA_CHALLENGE_KEY, captchaStr);

            OutputStream outputStream = response.getOutputStream();

            ImageIO.write(cpimg, FILE_TYPE, outputStream);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

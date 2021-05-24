package poly.servlet;

import org.apache.commons.beanutils.BeanUtils;
import poly.dao.UserDao;
import poly.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( {"/UserServlet", "/create", "/UserServlet/update", "/UserServlet/edit", "/UserServlet/delete", "/UserServlet/reset" })
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        User user = null;
        if(url.contains("delete")) {
            delete(request,response);
            user = new User();
            request.setAttribute("user", user);
        } else if(url.contains("edit")) {
            edit(request,response);
        } else if(url.contains("reset")) {
            user = new User();
            request.setAttribute("user", user);
        }


        request.setAttribute("user", user);
        findAll(request,response);
        request.getRequestDispatcher("/user.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (url.contains("insert")) {
            insert(request, response);
        }else if(url.contains("delete")) {
            delete(request,response);
            User user = new User();
            request.setAttribute("user", user);
        }else if(url.contains("update")) {
            update(request,response);
        } else if(url.contains("reset")) {
            User user = new User();
            request.setAttribute("user", user);
        }
        findAll(request,response);
        request.getRequestDispatcher("/user.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            UserDao dao = new UserDao();
            User user = dao.findById(userId);
            request.setAttribute("user", user);
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","error: "+e.getMessage());
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserDao dao = new UserDao();
            List<User> list = dao.findAll();
            if(!list.isEmpty())
                 request.setAttribute("user", list.get(0));
            else
                request.setAttribute("user", new User());
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","error: "+e.getMessage());
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            BeanUtils.populate(user,request.getParameterMap());
            UserDao dao = new UserDao();
            dao.insert(user);
            request.setAttribute("message","User inserted!!!: ");
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","error: "+e.getMessage());
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            BeanUtils.populate(user,request.getParameterMap());
            UserDao dao = new UserDao();
            dao.update(user);
            request.setAttribute("user", user);
            request.setAttribute("message","User upadate!!!: ");
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","error: "+e.getMessage());
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("userId");
            UserDao dao = new UserDao();
            dao.delete(userId);
            request.setAttribute("message","User deleted!!!: ");
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","error: "+e.getMessage());
        }
    }
}
